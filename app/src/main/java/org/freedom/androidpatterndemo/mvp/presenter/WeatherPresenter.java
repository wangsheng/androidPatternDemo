package org.freedom.androidpatterndemo.mvp.presenter;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.freedom.androidpatterndemo.Constants;
import org.freedom.androidpatterndemo.HttpEngine;
import org.freedom.androidpatterndemo.mvc.model.Weather;
import org.freedom.androidpatterndemo.mvp.view.IWeatherView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by wangsheng on 16/4/21.
 */
public class WeatherPresenter {
    private IWeatherView mWeatherView;

    public WeatherPresenter(@NonNull IWeatherView view) {
        this.mWeatherView = view;
    }

    /**
     * 通过网络请求获取天气列表数据
     */
    public void getWeatherList() {
        HttpEngine.get(Constants.API_WEATHER, new HttpEngine.JSONRequestCallback() {
            @Override
            public void onFailed(Call call, Exception e) {
                if (call.request().url().url().toString().equals(Constants.API_WEATHER)) {
                    mWeatherView.showErrorInfo(e);
                }
            }

            @Override
            public void onSuccess(Call call, JSONObject jsonResult) {
                if (call.request().url().url().toString().equals(Constants.API_WEATHER)) {
                    try {
                        JSONArray jsonArray = jsonResult.getJSONArray("results").getJSONObject(0).getJSONArray("daily");
                        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                        List<Weather> data = gson.fromJson(jsonArray.toString(), new TypeToken<List<Weather>>(){}.getType());
                        mWeatherView.refreshWeatherList(data);
                    } catch (JSONException e) {
                        mWeatherView.showErrorInfo(e);
                    }
                }
            }
        });
    }
}
