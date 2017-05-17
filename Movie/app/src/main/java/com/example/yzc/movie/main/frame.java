package com.example.yzc.movie.main;

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
import com.example.yzc.movie.film.yingpianguanli;
import com.example.yzc.movie.login;
import com.example.yzc.movie.movieHall.yingtingguanli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yzc on 2017/4/14.
 */

public class frame extends Activity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;
    private String[] iconName = { "影厅", "影片", "座位", "员工", "统计报表", "3D眼镜", "票务", "会员","查询","设置","收到的反馈"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.frame);
        gridView=(GridView) findViewById(R.id.gridView);
        dataList=new ArrayList<Map<String,Object>>();
        adapter=new SimpleAdapter(this, getData(), R.layout.item, new String[]{"pic","name"}, new int[]{R.id.pic,R.id.name});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        ////////////////////////topbar
        Topbar topbar = (Topbar) findViewById(R.id.topbar_frame);
        topbar.setOnTopbarClickLister(new Topbar.topbarClickLister() {
            @Override
            public void leftClick() {
                Toast.makeText(frame.this, "返回", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(frame.this, login.class);
                startActivity(intent);
            }

            @Override
            public void rightClick() {
                Toast.makeText(frame.this, "更多", Toast.LENGTH_SHORT).show();

            }
        });

        //不显示左键、右键
        topbar.setLeftIsvisable(true);
        topbar.setRightIsvisable(false);
        ////////////////////////////////topbar
    }

    private List<Map<String, Object>> getData() {

        int[] drawable = { R.mipmap.cinema, R.mipmap.movie, R.mipmap.seata,
                            R.mipmap.person, R.mipmap.report,R.mipmap.glasses,
                            R.mipmap.ticket, R.mipmap.vip,R.mipmap.search,
                            R.mipmap.setting,R.mipmap.feedback};

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
                Intent intent = new Intent(frame.this, yingtingguanli.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(frame.this, yingpianguanli.class);
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
