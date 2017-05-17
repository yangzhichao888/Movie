package com.example.yzc.movie.employee;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzc.movie.DatabaseHelper;
import com.example.yzc.movie.R;
import com.example.yzc.movie.Topbar;
import com.example.yzc.movie.main.frame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yzc on 2017/5/6.
 */
public class employee extends Activity implements AdapterView.OnItemClickListener{
    private GridView gv_employee;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;

    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.employee);
        gv_employee = (GridView) findViewById(R.id.gridView_employee);
        dataList=new ArrayList<Map<String,Object>>();
        adapter=new SimpleAdapter(this, getData(), R.layout.item, new String[]{"pic","id"}, new int[]{R.id.pic,R.id.id});
        gv_employee.setAdapter(adapter);
        gv_employee.setOnItemClickListener(this);
        ////////////////////////////contextMenu
        this.registerForContextMenu(gv_employee);

        ////////////////////////////contextMenu


        /////////////////////////topbar
        Topbar topbar = (Topbar) findViewById(R.id.topbar_employee);
        topbar.setOnTopbarClickLister(new Topbar.topbarClickLister() {
            @Override
            public void leftClick() {
                Intent intent = new Intent(employee.this, frame.class);
                startActivity(intent);
            }

            @Override
            public void rightClick() {
                Intent intent = new Intent(employee.this, employee_add.class);
                startActivity(intent);
                finish();
            }
        });
        /////////////////////////topbar

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //设置menu显示内容
        menu.setHeaderTitle("员工操作");
        menu.setHeaderIcon(R.mipmap.person);
//        menu.add(1,1,1,"添加");
//        menu.add(1,2,1,"修改");
//        menu.add(1,3,1,"删除");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_operate,menu);
    }


    public boolean onContextItemSelected(MenuItem item ) {
        switch (item.getItemId()){
            case R.id.context_menu_add:
                Intent intent = new Intent(employee.this, employee_add.class);
                startActivity(intent);
                finish();
                break;
            case R.id.context_menu_revise:
                ContextMenu.ContextMenuInfo info = item.getMenuInfo();
                AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) info;
                //int position = contextMenuInfo.position; //这个就是你的item的Position
                View targetView = ((AdapterView.AdapterContextMenuInfo) info).targetView;

                intent = new Intent(employee.this, employee_revise.class);
                TextView tv_id = (TextView) targetView.findViewById(R.id.id);
                String str = (String) tv_id.getText();
                Bundle bundle = new Bundle();
                bundle.putString("message", str);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.context_menu_del:
                ContextMenu.ContextMenuInfo info1 = item.getMenuInfo();
                AdapterView.AdapterContextMenuInfo contextMenuInfo1 = (AdapterView.AdapterContextMenuInfo) info1;
                View targetView1 = ((AdapterView.AdapterContextMenuInfo) info1).targetView;
                TextView tv_id1 = (TextView) targetView1.findViewById(R.id.id);
                String id = (String) tv_id1.getText();
                helper = new DatabaseHelper(employee.this,"movie.db");
                SQLiteDatabase db = helper.getReadableDatabase();
                db.delete("employee","_id = ?",new String[]{id});
                db.close();
                intent = new Intent(employee.this, frame.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private List<Map<String, Object>> getData() {

        helper = new DatabaseHelper(employee.this,"movie.db");
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c =db.rawQuery("select * from employee",null);

        int[] drawable = { R.mipmap.person,R.mipmap.person,R.mipmap.person,
                R.mipmap.person, R.mipmap.person,R.mipmap.person,
                R.mipmap.person,R.mipmap.person,R.mipmap.person,R.mipmap.person};
//        for (int i = 0; i < c.getCount(); i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("pic", drawable[i]);
//            map.put("name", iconName[i]);
//            dataList.add(map);
//        }
        if (c != null){
            c.moveToFirst();
            while (c.moveToNext()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("pic", R.mipmap.person);
           //     map.put("name", c.getString(c.getColumnIndex("employee_name")));
                map.put("id",c.getInt(c.getColumnIndex("_id")));
                dataList.add(map);
            }
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(employee.this, employee_revise.class);
        TextView tv_id = (TextView)view.findViewById(R.id.id);
        String str = (String) tv_id.getText();
        Bundle bundle = new Bundle();
        bundle.putString("message", str);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
