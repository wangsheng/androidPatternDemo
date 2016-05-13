package org.freedom.androidpatterndemo.mvvm.view;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.freedom.androidpatterndemo.R;
import org.freedom.androidpatterndemo.databinding.WeatherItemBinding;
import org.freedom.androidpatterndemo.mvvm.model.Weather;

import java.util.List;

/**
 * Created by wangsheng on 16/4/20.
 */
public class WeatherAdapter extends BaseAdapter {
    private List<Weather> data;

    public WeatherAdapter(List<Weather> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Weather getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder((WeatherItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                    R.layout.weather_item, viewGroup, false));
            convertView = holder.binding.tvWeather;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.binding.setWeather(getItem(position));
        return convertView;
    }

    class ViewHolder {
        WeatherItemBinding binding;
        public ViewHolder(WeatherItemBinding binding) {
            this.binding = binding;
        }
    }
}
