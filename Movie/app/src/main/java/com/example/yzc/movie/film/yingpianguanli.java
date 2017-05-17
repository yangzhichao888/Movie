package com.example.yzc.movie.film;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yzc.movie.R;
import com.example.yzc.movie.movieHall.yingting_listview_first_revise;
import com.example.yzc.movie.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yzc on 2017/4/16.
 */

public class yingpianguanli extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>>dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yingpianguanli);
        listView = (ListView) findViewById(R.id.listview_yingpian);

        dataList = new ArrayList<Map<String, Object>>();
        adapter = new SimpleAdapter(this,getData(),R.layout.item_listview_yingpian,new String[]{"pic","name","resume"},new int[]{R.id.imageView,R.id.movie_name,R.id.movie_resume});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }
    private List<Map<String,Object>> getData(){
        for (int i = 0;i<20;i++){
            Map<String,Object>map = new HashMap<String, Object>();
            map.put("pic",R.mipmap.movie);
            map.put("name","人民的名义");
            map.put("resume","该剧以检察官侯亮平的调查行动为叙事主线，讲述了当代检察官维护公平正义和法制统一、查办贪腐案件的故事，于2017年3月28日在湖南卫视“金鹰独播剧场”播出。");
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                Intent intent = new Intent(yingpianguanli.this, register.class);
                startActivity(intent);
                break;
            default:
                intent = new Intent(yingpianguanli.this, yingting_listview_first_revise.class);
                startActivity(intent);
                break;
        }
    }
}
