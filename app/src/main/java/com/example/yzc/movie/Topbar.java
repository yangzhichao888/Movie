package com.example.yzc.movie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by yzc on 2017/4/21.
 */

public class Topbar extends RelativeLayout {
    private Button leftButton,rightButton;
    private TextView tvTitle;

    private int     leftTextColor;
    private Drawable leftBackground;
    private String    leftText;

    private int     rightTextColor;
    private Drawable rightBackground;
    private String    rightText;

    private float titleTextSize;
    private int titleTextColor;
    private String title;

    //把控件放到布局里
    private LayoutParams leftParams,rightParams,titleParams;

    /*************************************************
     * 接口回调
     * 1.创建接口
     * 2.暴露方法给调用者
     * 3.
     **************************************************/
    //1.创建接口
    public interface topbarClickLister{
        public void leftClick();
        public void rightClick();

    }
    //2.暴露方法给调用者
    private topbarClickLister listener;//变量映射接口
    public void setOnTopbarClickLister(topbarClickLister listener){
        this.listener = listener;
    }
    /*************************************************
     * 接口回调
     **************************************************/
    public Topbar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //通过TypedArray  获取 我们在xml文件中自定义的 属性值
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.Topbar);
        
        leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor,0);
        leftBackground  = ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText = ta.getString(R.styleable.Topbar_leftText);

        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor,0);
        rightBackground  = ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText = ta.getString(R.styleable.Topbar_rightText);

        titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize,0);
        titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor,0);
        title = ta.getString(R.styleable.Topbar_title);

        //回收1.避免浪费资源2.避免出现错误
        ta.recycle();

        //实例化Button,Textview
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        //把属性值赋给控件
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);//居中显示文字

        //setBackgroundColor(0x33CCCC);

        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);


        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL,TRUE);

        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);

        //加入
        addView(leftButton,leftParams);
        addView(rightButton,rightParams);
        addView(tvTitle,titleParams);

        //最后设置点击事件
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.leftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.rightClick();
            }
        });
    }
    //拓展    是否显示左键、右键
    public void setLeftIsvisable(boolean flag){
        if (flag){
            leftButton.setVisibility(View.VISIBLE);
        }else {
            leftButton.setVisibility(View.GONE);
        }
    }
    public void setRightIsvisable(boolean flag){
        if (flag){
            rightButton.setVisibility(View.VISIBLE);
        }else {
            rightButton.setVisibility(View.GONE);
        }
    }
}
