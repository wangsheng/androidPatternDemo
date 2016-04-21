package org.freedom.androidpatterndevdemo.mvc.model;

/**
 * 天气实体类
 *
 * Created by wangsheng on 16/4/20.
 */
public class Weather {
    private String date;//日期
    private String textDay;//白天天气
    private String textNight;//夜晚天气
    private String high;//最高气温
    private String low;//最低气温

    public String getDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(date);
        sb.append("\t");
        sb.append("白天:");
        sb.append(textDay);
        sb.append(", ");
        sb.append("夜晚:");
        sb.append(textNight);
        sb.append(", ");
        sb.append("最高气温:");
        sb.append(high);
        sb.append(", ");
        sb.append("最低气温:");
        sb.append(low);
        return sb.toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", textDay='" + textDay + '\'' +
                ", textNight='" + textNight + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                '}';
    }
}
