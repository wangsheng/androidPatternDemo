package org.freedom.androidpatterndemo;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Http引擎封装
 *
 * Created by wangsheng on 16/4/20.
 */
public class HttpEngine {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    /**
     * Get请求异步线程访问网络
     * @param url 请求地址
     * @param mJsonRequestCallback 回调对象
     */
    public static void get(final String url, final @NonNull JSONRequestCallback mJsonRequestCallback){
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                Log.e(HttpEngine.class.getSimpleName(), String.format("===>get url:%s, failed:%s", url, e.getMessage()));
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mJsonRequestCallback.onFailed(call, e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                String result = response.body().string();
                response.body().close();
                Log.i(HttpEngine.class.getSimpleName(), String.format("===>get url:%s, success, result:%s", url, result));
                try {
                    final JSONObject jsonResult = new JSONObject(result);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mJsonRequestCallback.onSuccess(call, jsonResult);
                        }
                    });
                } catch (final JSONException e) {
                    e.printStackTrace();
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mJsonRequestCallback.onFailed(call, e);
                        }
                    });
                }

            }
        });
    }

    /**
     * JSONObject请求回调接口
     */
    public interface JSONRequestCallback {
        /**
         * 失败回调
         * @param call
         * @param e
         */
        void onFailed(Call call, Exception e);

        /**
         * 成功失败
         * @param call
         * @param jsonResult
         */
        void onSuccess(Call call, JSONObject jsonResult);
    }
}
