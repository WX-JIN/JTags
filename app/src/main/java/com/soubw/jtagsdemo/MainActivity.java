package com.soubw.jtagsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.soubw.jtags.bean.JTagBean;
import com.soubw.jtags.JTag;
import com.soubw.jtags.JTagLayout;
import com.soubw.jtags.JPolyLineType;
import com.soubw.jtags.JTagType;
import com.soubw.jtags.OnJTagClickListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private com.soubw.jtags.JTagLayout jTagLayout;
    private ArrayList<JTagBean> jTagBeans = new ArrayList<>();

    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.measure(0,0);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;//获取屏幕宽度
        screenHeight = dm.heightPixels - toolbar.getMeasuredHeight();//获取屏幕高度
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.none:
                        none();
                        break;
                    case R.id.guide:
                        guide();
                        break;
                    case R.id.showJTags:
                        showJTags();
                        break;
                    case R.id.hideJTags:
                        hideJTags();
                        break;
                    case R.id.addJTag:
                        addJTag();
                        break;
                }
                return false;
            }
        });
        this.jTagLayout = (JTagLayout) findViewById(R.id.jTagLayout);
        jTagLayout.setOnJTagsClickListener(new OnJTagClickListener() {
            @Override
            public void onJTagClick(JTag jTag) {
                Toast.makeText(MainActivity.this,jTag.getJTagBean().getJTagContent(),Toast.LENGTH_SHORT).show();
            }
        });
        none();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_item, menu);
        return true;
    }

    public void none(){
        jTagLayout.dismissGuide();
        jTagBeans.add(new JTagBean(screenWidth*5/7,screenHeight/2,"安卓机器人", JTagType.CIRCLE));
        jTagBeans.add(new JTagBean(60,40,"WX_JIN", JTagType.NONE));
        JTagBean bean1 = new JTagBean(screenWidth*3/7,screenHeight*4/15,"锤子", JTagType.POLYLINE);
        bean1.setjPolyLineType(JPolyLineType.LEFT_TOP);
        jTagBeans.add(bean1);
        JTagBean bean2 = new JTagBean(screenWidth*2/5,screenHeight*5/8,"透明的苹果", JTagType.POLYLINE);
        bean2.setjPolyLineType(JPolyLineType.RIGHT_BOTTOM);
        jTagBeans.add(bean2);
        jTagLayout.setJTags(jTagBeans);
    }

    private void guide(){
        jTagLayout.dismissGuide();

        jTagBeans.add( new JTagBean(JTagType.BG, R.drawable.guide_bg));
        JTagBean bean1 = new JTagBean(screenWidth/6,screenHeight/5,"第一步", JTagType.NONE);
        bean1.setJTagTextColor(Color.parseColor("#ff0000"));
        bean1.setJTagBg(R.drawable.step1);
        jTagBeans.add(bean1);
        JTagBean bean2 = new JTagBean(screenWidth/2,screenHeight*1/4,"点击", JTagType.NONE);
        bean2.setJTagTextColor(Color.parseColor("#00ff00"));
        bean2.setJTagBg(R.drawable.step2);
        jTagBeans.add(bean2);
        JTagBean bean3 = new JTagBean(screenWidth-150,screenHeight*2/3,"第三步", JTagType.NONE);
        bean3.setJTagTextColor(Color.parseColor("#0000ff"));
        bean3.setJTagBg(R.drawable.step3);
        jTagBeans.add(bean3);
        JTagBean bean4 = new JTagBean(screenWidth/4,screenHeight-200,"", JTagType.NONE);
        bean4.setJTagTextColor(Color.parseColor("#000000"));
        bean4.setJTagTextSize(24);
        bean4.setJTagBg(R.drawable.step4);
        jTagBeans.add(bean4);

        jTagLayout.setJTags(jTagBeans);

    }
    private void showJTags(){
        jTagLayout.showAllJTags();
    }
    private void hideJTags(){
        jTagLayout.hideAllJTags();
    }
    private void addJTag(){
        JTagBean bean = new JTagBean(new Random().nextInt(screenWidth),new Random().nextInt(screenHeight),"WX_JIN", JTagType.NONE);
        int type = new Random().nextInt(3);
        switch (type){
            case 0:
                bean.setJTagType(JTagType.CIRCLE);
                break;
            case 1:
                bean.setJTagType(JTagType.NONE);
                break;
            case 2:
                bean.setJTagType(JTagType.POLYLINE);
                break;
        }
        jTagLayout.addJTag(bean);
    }
}
