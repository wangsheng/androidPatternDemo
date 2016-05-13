package org.freedom.androidpatterndemo.mvvm.viewmodel;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.freedom.androidpatterndemo.Constants;
import org.freedom.androidpatterndemo.HttpEngine;
import org.freedom.androidpatterndemo.mvvm.model.Weather;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * View model for WeatherActivity
 *
 * 从维基百科文章中可以看到：
 *
 * view model是一个抽象的view，它对外暴露公有的属性和命令。
 *
 * Created by wangsheng on 16/5/12.
 */
public class WeatherViewModel {

    private DataListener dataListener;

    public WeatherViewModel(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    /**
     * 通过网络请求获取天气列表数据
     */
    public void getWeatherList() {
        HttpEngine.get(Constants.API_WEATHER, new HttpEngine.JSONRequestCallback() {
            @Override
            public void onFailed(Call call, Exception e) {
                if (call.request().url().url().toString().equals(Constants.API_WEATHER)) {
                    if (dataListener != null) {
                        dataListener.onWeatherChanged(true, null);
                    }
                }
            }

            @Override
            public void onSuccess(Call call, JSONObject jsonResult) {
                if (call.request().url().url().toString().equals(Constants.API_WEATHER)) {
                    try {
                        JSONArray jsonArray = jsonResult.getJSONArray("results").getJSONObject(0).getJSONArray("daily");
                        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                        List<Weather> data = gson.fromJson(jsonArray.toString(), new TypeToken<List<Weather>>(){}.getType());
                        if (dataListener != null) {
                            dataListener.onWeatherChanged(false, data);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        if (dataListener != null) {
                            dataListener.onWeatherChanged(true, null);
                        }
                    }
                }
            }
        });
    }

    public interface DataListener {
        void onWeatherChanged(boolean occurError, List<Weather> data);
    }
}
