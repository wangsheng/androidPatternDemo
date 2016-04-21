package org.freedom.androidpatterndemo.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import org.freedom.androidpatterndemo.R;
import org.freedom.androidpatterndemo.mvc.WeatherAdapter;
import org.freedom.androidpatterndemo.mvc.model.Weather;
import org.freedom.androidpatterndemo.mvp.presenter.WeatherPresenter;

import java.util.List;


/**
 * Created by wangsheng on 16/4/21.
 */
public class WeatherActivity extends AppCompatActivity implements IWeatherView{
    private ListView lv;
    private LinearLayout llLoading;
    private WeatherPresenter mWeatherPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        lv = (ListView) findViewById(R.id.lv);
        llLoading = (LinearLayout) findViewById(R.id.ll_loading);
        mWeatherPresenter = new WeatherPresenter(this);
        mWeatherPresenter.getWeatherList();
    }

    @Override
    public void refreshWeatherList(List<Weather> data) {
        WeatherAdapter adapter = new WeatherAdapter(WeatherActivity.this, data);
        lv.setAdapter(adapter);
        llLoading.setVisibility(View.GONE);
        lv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorInfo(Exception e) {
        Log.e(WeatherActivity.class.getSimpleName(), e.getMessage());
        Toast.makeText(WeatherActivity.this, "Get data failed!", Toast.LENGTH_LONG).show();
        llLoading.setVisibility(View.GONE);
    }
}
