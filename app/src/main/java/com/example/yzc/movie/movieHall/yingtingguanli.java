package com.example.yzc.movie.movieHall;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.yzc.movie.R;
import com.example.yzc.movie.Topbar;
import com.example.yzc.movie.main.frame;
import com.example.yzc.movie.main.guanliPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class yingtingguanli extends Activity  implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{

    private List<View>viewList;
    private ViewPager guanlipager;
    private PagerTabStrip tab;
    private List<String>titleList;

    private SimpleAdapter sim_adapter;
    private SimpleAdapter sim_adapter_second;
    private SimpleAdapter sim_adapter_third;
    private SimpleAdapter sim_adapter_fourth;
    private SimpleAdapter sim_adapter_fifth;


    private List<Map<String,Object>>dataList;
    private List<Map<String,Object>>dataList_second;
    private List<Map<String,Object>>dataList_third;
    private List<Map<String,Object>>dataList_fourth;
    private List<Map<String,Object>>dataList_fifth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yingtingguanli);

        ////////////////////////topbar
        Topbar topbar = (Topbar) findViewById(R.id.topbar_movieHall);
        topbar.setOnTopbarClickLister(new Topbar.topbarClickLister() {
            @Override
            public void leftClick() {
                Toast.makeText(yingtingguanli.this, "返回", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(yingtingguanli.this, frame.class);
                startActivity(intent);
            }

            @Override
            public void rightClick() {
                Toast.makeText(yingtingguanli.this, "更多", Toast.LENGTH_SHORT).show();
            }
        });

        //不显示左键、右键
        topbar.setLeftIsvisable(true);
        topbar.setRightIsvisable(true);
        ////////////////////////////////topbar


        viewList = new ArrayList<>();

        View yingting_first = View.inflate(this,R.layout.yingting_first,null);
        ListView listview_first = (ListView) yingting_first.findViewById(R.id.listview1);

        View yingting_second = View.inflate(this,R.layout.yingting_second,null);
        ListView listview_second = (ListView) yingting_second.findViewById(R.id.listview2);

        View yingting_third = View.inflate(this,R.layout.yingting_third,null);
        ListView listview_third = (ListView) yingting_third.findViewById(R.id.listview3);

        View yingting_fourth = View.inflate(this,R.layout.yingting_fourth,null);
        ListView listview_fourth = (ListView) yingting_fourth.findViewById(R.id.listview4);

        View yingting_fifth = View.inflate(this,R.layout.yingting_fifth,null);
        ListView listview_fifth = (ListView) yingting_fifth.findViewById(R.id.listview5);

        viewList.add(yingting_first);
        viewList.add(yingting_second);
        viewList.add(yingting_third);
        viewList.add(yingting_fourth);
        viewList.add(yingting_fifth);

        //为页卡ViewPager设置标题
        titleList = new ArrayList<String>();
        titleList.add("一号演出厅");
        titleList.add("二号演出厅");
        titleList.add("三号演出厅");
        titleList.add("四号演出厅");
        titleList.add("五号演出厅");
        //为PagerTabStrip设置一些属性
        tab = (PagerTabStrip) findViewById(R.id.tab_yingting);
   //     tab.setBackgroundColor(Color.BLUE);
        tab.setTextColor(Color.BLACK);
        tab.setDrawFullUnderline(false);
        tab.setTabIndicatorColor(Color.DKGRAY);

        //初始化ViewPager
        guanlipager = (ViewPager) findViewById(R.id.pager_yingting);
        //创建PageAdapter的适配器
        guanliPagerAdapter adapter = new guanliPagerAdapter(viewList,titleList);

        //ViewPager加载适配器
       guanlipager.setAdapter(adapter);

        dataList = new ArrayList<Map<String,Object>>();
        dataList_second = new ArrayList<Map<String,Object>>();
        dataList_third = new ArrayList<Map<String,Object>>();
        dataList_fourth = new ArrayList<Map<String,Object>>();
        dataList_fifth = new ArrayList<Map<String,Object>>();


        sim_adapter = new SimpleAdapter(this,getData(),R.layout.item_listview,new String[]{"tv11","tv12","tv2"},new int[]{R.id.lv_tv11,R.id.lv_tv12,R.id.lv_tv2});
        listview_first.setAdapter(sim_adapter);
        listview_first.setOnItemClickListener(this);
        listview_first.setOnScrollListener(this);

        sim_adapter_second = new SimpleAdapter(this,getData_second(),R.layout.item_listview,new String[]{"tv11","tv12","tv2"},new int[]{R.id.lv_tv11,R.id.lv_tv12,R.id.lv_tv2});
        listview_second.setAdapter(sim_adapter_second);
        listview_second.setOnItemClickListener(this);
        listview_second.setOnScrollListener(this);

        sim_adapter_third = new SimpleAdapter(this,getData_second(),R.layout.item_listview,new String[]{"tv11","tv12","tv2"},new int[]{R.id.lv_tv11,R.id.lv_tv12,R.id.lv_tv2});
        listview_third.setAdapter(sim_adapter_third);
        listview_third.setOnItemClickListener(this);
        listview_third.setOnScrollListener(this);

        sim_adapter_fourth = new SimpleAdapter(this,getData_second(),R.layout.item_listview,new String[]{"tv11","tv12","tv2"},new int[]{R.id.lv_tv11,R.id.lv_tv12,R.id.lv_tv2});
        listview_fourth.setAdapter(sim_adapter_fourth);
        listview_fourth.setOnItemClickListener(this);
        listview_fourth.setOnScrollListener(this);

        sim_adapter_fifth = new SimpleAdapter(this,getData_second(),R.layout.item_listview,new String[]{"tv11","tv12","tv2"},new int[]{R.id.lv_tv11,R.id.lv_tv12,R.id.lv_tv2});
        listview_fifth.setAdapter(sim_adapter_fifth);
        listview_fifth.setOnItemClickListener(this);
        listview_fifth.setOnScrollListener(this);
    }
    private List<Map<String,Object>> getData(){
        for (int i = 0;i<15;i++){
            Map<String,Object>map = new HashMap<String, Object>();
            map.put("tv11","01-01 8:00");
            map.put("tv12","01-01 9:00");
            map.put("tv2","人民的名义");
            dataList.add(map);
        }
        return dataList_second;
    }

    private List<Map<String,Object>> getData_second(){
        for (int i = 0;i<15;i++){
            Map<String,Object>map = new HashMap<String, Object>();
            map.put("tv11","01-01 8:00");
            map.put("tv12","01-01 9:00");
            map.put("tv2","人民的名义");
            dataList_second.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                Intent intent = new Intent(yingtingguanli.this, yingting_listview_first_revise.class);
                startActivity(intent);
                break;
            default:
                intent = new Intent(yingtingguanli.this, yingting_listview_first_revise.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i){
            case SCROLL_STATE_FLING://用户在手指离开屏幕之前，由于用力划了一下，视图仍往下划
                Map<String,Object>map = new HashMap<String, Object>();
                map.put("tv11","01-02 10:00");
                map.put("tv12","01-02 11:00");
                map.put("tv2","金刚狼3：殊死一战");
                dataList.add(map);
                sim_adapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE://视图已经停止滑动
                break;
            case SCROLL_STATE_TOUCH_SCROLL://手指没有离开屏幕视图正在滑动
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}


