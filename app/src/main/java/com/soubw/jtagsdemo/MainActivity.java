package com.soubw.jtagsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.soubw.bean.JTagBean;
import com.soubw.jtags.JTag;
import com.soubw.jtags.JTagLayout;
import com.soubw.jtags.JPolyLineType;
import com.soubw.jtags.JTagType;
import com.soubw.jtags.OnJTagClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private com.soubw.jtags.JTagLayout jTagLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.jTagLayout = (JTagLayout) findViewById(R.id.jTagLayout);
        ArrayList<JTagBean> jTagBeans = new ArrayList<>();
        jTagBeans.add(new JTagBean(60,720,"one-piece", JTagType.NONE));

        JTagBean bean1 = new JTagBean(80,80,"拳头", JTagType.POLYLINE);
        bean1.setjPolyLineType(JPolyLineType.RIGHT_BOTTOM);
        jTagBeans.add(bean1);
        JTagBean bean2 = new JTagBean(550,600,"衣服上的标志", JTagType.POLYLINE);
        bean2.setjPolyLineType(JPolyLineType.LEFT_TOP);
        jTagBeans.add(bean2);
        jTagLayout.setJTags(jTagBeans);
        jTagLayout.setOnJTagsClickListener(new OnJTagClickListener() {
            @Override
            public void onJTagClick(JTag jTag) {
                Toast.makeText(MainActivity.this,jTag.getJTagBean().getJTagContent(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
