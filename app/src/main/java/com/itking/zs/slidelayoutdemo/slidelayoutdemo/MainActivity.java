package com.itking.zs.slidelayoutdemo.slidelayoutdemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;



public class MainActivity extends AppCompatActivity {

  AppBarLayout appbar ;
  ImageView iv_top,ivReturnTop;
  LinearLayout fl_layout;
  TextView tv_title;
  Toolbar toob;
  GoTopNestedScrollView go_top_scrollview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        appbar =findViewById(R.id.appbar);
//        iv_top=findViewById(R.id.iv_top);
        ivReturnTop=findViewById(R.id.ivReturnTop);
        toob= findViewById(R.id.toob);
        fl_layout =findViewById(R.id.fl_layout);
        tv_title =findViewById(R.id.tv_title);
        go_top_scrollview = findViewById(R.id.go_top_scrollview);
        initView();//初始化视图
        showImg();//显示网络图片
    }


    /**
     * 初始化视图
     */
    private void initView() {


        StatusBarUtils.setStatusTransparent(this);//透明状态栏

        //滑动偏移监听事件
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int toolbarHeight = appBarLayout.getTotalScrollRange();
                int dy = Math.abs(verticalOffset);
                if (dy <= toolbarHeight) {
                    float scale = (float) dy / toolbarHeight;
                    float alpha = scale * 255;
                    fl_layout.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));//渐变背景透明度
                    tv_title.setTextColor(Color.argb((int) alpha,255,0,0));//渐变文字颜色透明度
                    if (Build.VERSION.SDK_INT >= 21) {
                        getWindow().setStatusBarColor(Color.argb((int) alpha, 255, 255, 255));

                    }
                    toob.setTitle("我是标题");
                    toob.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                }
            }
        });

        //设置点击置顶的ImageView
        go_top_scrollview.setImageViewOnClickGoToFirst(ivReturnTop);

        //ScrollView滑动超过屏幕高度则显示置顶按钮,不设置的话就会使用自定义View中的默认高度
        DisplayMetrics metric = new DisplayMetrics();//获取屏幕高度
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        go_top_scrollview.setScreenHeight(metric.heightPixels);//设置高度
    }

    /**
     * 使用Glide加载显示网络图片 记得加网络权限和http地址url访问许可
     */
    private void showImg() {
        Glide.with(this)
                .load("http://gank.io/images/2c924db2a1b84c5d8fdb9f8c5f6d1b71")
                .into((ImageView)findViewById(R.id.iv_one));
        Glide.with(this)
                .load("http://gank.io/images/92989b6a707b44dfb1c734e8d53d39a2")
                .into((ImageView)findViewById(R.id.iv_two));
        Glide.with(this)
                .load("http://gank.io/images/4817628a6762410895f814079a6690a1")
                .into((ImageView)findViewById(R.id.iv_three));
        Glide.with(this)
                .load("http://gank.io/images/f9523ebe24a34edfaedf2dd0df8e2b99")
                .into((ImageView)findViewById(R.id.iv_four));
        Glide.with(this)
                .load("http://gank.io/images/4002b1fd18544802b80193fad27eaa62")
                .into((ImageView)findViewById(R.id.iv_five));
    }
}
