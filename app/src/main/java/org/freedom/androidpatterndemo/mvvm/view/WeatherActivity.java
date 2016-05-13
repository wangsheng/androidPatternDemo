package org.freedom.androidpatterndemo.mvvm.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import android.databinding.DataBindingUtil;
import android.widget.Toast;

import org.freedom.androidpatterndemo.R;
import org.freedom.androidpatterndemo.databinding.ActivityWeatherMvvmBinding;
import org.freedom.androidpatterndemo.mvvm.model.Weather;
import org.freedom.androidpatterndemo.mvvm.viewmodel.WeatherViewModel;

import java.util.List;

/**
 * Created by wangsheng on 16/5/12.
 */
public class WeatherActivity extends AppCompatActivity implements WeatherViewModel.DataListener {
    private ActivityWeatherMvvmBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_mvvm);
        new WeatherViewModel(this).getWeatherList();
    }

    @Override
    public void onWeatherChanged(boolean occurError, List<Weather> data) {
        binding.llLoading.setVisibility(View.GONE);
        if (occurError) {
            Toast.makeText(WeatherActivity.this, "Get data failed!", Toast.LENGTH_LONG).show();
        } else {
            binding.lv.setAdapter(new WeatherAdapter(data));
            binding.lv.setVisibility(View.VISIBLE);
        }
    }
}
