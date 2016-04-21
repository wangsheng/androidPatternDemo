package org.freedom.androidpatterndemo.mvc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.freedom.androidpatterndemo.Constants;
import org.freedom.androidpatterndemo.HttpEngine;
import org.freedom.androidpatterndemo.R;
import org.freedom.androidpatterndemo.mvc.model.Weather;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by wangsheng on 16/4/20.
 */
public class WeatherActivity extends AppCompatActivity {
    private ListView lv;
    private LinearLayout llLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_mvc);
        lv = (ListView) findViewById(R.id.lv);
        llLoading = (LinearLayout) findViewById(R.id.ll_loading);
        loadData();
    }

    private void loadData() {
        HttpEngine.get(Constants.API_WEATHER, new HttpEngine.JSONRequestCallback() {
            @Override
            public void onFailed(Call call, Exception e) {
                if (call.request().url().url().toString().equals(Constants.API_WEATHER)) {
                    Log.e(WeatherActivity.class.getSimpleName(), e.getMessage());
                    Toast.makeText(WeatherActivity.this, "Get data failed!", Toast.LENGTH_LONG).show();
                    llLoading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSuccess(Call call, JSONObject jsonResult) {
                if (call.request().url().url().toString().equals(Constants.API_WEATHER)) {
                    try {
                        JSONArray jsonArray = jsonResult.getJSONArray("results").getJSONObject(0).getJSONArray("daily");
                        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                        final List<Weather> data = gson.fromJson(jsonArray.toString(), new TypeToken<List<Weather>>(){}.getType());
                        WeatherAdapter adapter = new WeatherAdapter(WeatherActivity.this, data);
                        lv.setAdapter(adapter);
                        llLoading.setVisibility(View.GONE);
                        lv.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        Log.e(WeatherActivity.class.getSimpleName(), e.getMessage());
                        Toast.makeText(WeatherActivity.this, "Get data failed!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
