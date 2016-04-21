package org.freedom.androidpatterndemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.freedom.androidpatterndemo.mvc.WeatherActivity;
import org.freedom.androidpatterndemo.R;

/**
 * 应用主页，包含三种模式的入口:
 * <ul>
 *     <li>MVC</li>
 *     <li>MVP</li>
 *     <li>MVVM</li>
 * </ul>
 *
 * Created by wangsheng on 16/4/20.
 */
public class MainActivity extends AppCompatActivity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        lv = (ListView)findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new String[]{"MVC", "MVP", "MVVM"});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //go to MVC demo
                        forward(WeatherActivity.class);
                        break;
                    case 1:
                        //TODO go to MVP demo
                        break;
                    case 2:
                        //TODO go to MVVM demo
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void forward(Class<?> destCls) {
        Intent mIntent = new Intent();
        mIntent.setClass(this, destCls);
        startActivity(mIntent);
    }
}
