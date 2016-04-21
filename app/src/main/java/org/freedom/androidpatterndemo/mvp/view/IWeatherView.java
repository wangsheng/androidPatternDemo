package org.freedom.androidpatterndemo.mvp.view;

import org.freedom.androidpatterndemo.mvc.model.Weather;

import java.util.List;

/**
 * 天气视图接口
 *
 * Created by wangsheng on 16/4/21.
 */
public interface IWeatherView {
    /**
     * 刷新天气列表
     * @param data
     */
    void refreshWeatherList(List<Weather> data);

    /**
     * 显示错误信息
     * @param e
     */
    void showErrorInfo(Exception e);
}
