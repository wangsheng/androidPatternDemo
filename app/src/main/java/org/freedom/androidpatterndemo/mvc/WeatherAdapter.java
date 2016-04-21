package org.freedom.androidpatterndemo.mvc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.freedom.androidpatterndemo.mvc.model.Weather;

import java.util.List;

/**
 * Created by wangsheng on 16/4/20.
 */
public class WeatherAdapter extends BaseAdapter {
    private List<Weather> data;
    private LayoutInflater mInflater;

    public WeatherAdapter(Context ctx, List<Weather> data) {
        this.data = data;
        this.mInflater = LayoutInflater.from(ctx);
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
            holder = new ViewHolder();
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, null);
            holder.tv = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(getItem(position).getDisplay());
        return convertView;
    }

    class ViewHolder {
        TextView tv;
    }
}
