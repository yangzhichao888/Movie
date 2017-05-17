package com.example.yzc.movie.film;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.yzc.movie.R;
import com.example.yzc.movie.Topbar;
import com.example.yzc.movie.employee.employee_add;
import com.example.yzc.movie.main.frame;
import com.example.yzc.movie.movieHall.yingtingguanli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yzc on 2017/4/16.
 */

public class movie extends Activity implements AdapterView.OnItemClickListener{
    private GridView gridView_yingpian;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;
    private String[] iconName = { "人民的名义","人民的名义","人民的名义","人民的名义","人民的名义","人民的名义","人民的名义","人民的名义","人民的名义","人民的名义","人民的名义","人民的名义"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.movie);

        gridView_yingpian = (GridView) findViewById(R.id.gridView_yingpian);
        dataList=new ArrayList<Map<String,Object>>();
        adapter=new SimpleAdapter(this, getData(), R.layout.item, new String[]{"pic","name"}, new int[]{R.id.pic,R.id.id});
        gridView_yingpian.setAdapter(adapter);
        gridView_yingpian.setOnItemClickListener(this);

        /////////////////////////topbar
        Topbar topbar = (Topbar) findViewById(R.id.topbar_movie);
        topbar.setOnTopbarClickLister(new Topbar.topbarClickLister() {
            @Override
            public void leftClick() {
                Toast.makeText(movie.this, "返回", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(movie.this, frame.class);
                startActivity(intent);
            }

            @Override
            public void rightClick() {
                Toast.makeText(movie.this, "更多", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(movie.this, employee_add.class);
                startActivity(intent);
            }
        });
        /////////////////////////topbar
    }
    private List<Map<String, Object>> getData() {

        int[] drawable = { R.mipmap.movie,R.mipmap.movie,R.mipmap.movie,R.mipmap.movie,
                R.mipmap.movie,R.mipmap.movie,R.mipmap.movie,R.mipmap.movie,R.mipmap.movie,R.mipmap.movie,R.mipmap.movie,R.mipmap.movie};
        for (int i = 0; i < drawable.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", drawable[i]);
            map.put("name", iconName[i]);
            dataList.add(map);
        }
        Log.i("Main", "size="+dataList.size());
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,"我是" + iconName[i],Toast.LENGTH_SHORT).show();
        switch (i){
            case 0:
                Intent intent = new Intent(movie.this, yingtingguanli.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(movie.this, movie.class);
                startActivity(intent);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;

        }
    }
}
