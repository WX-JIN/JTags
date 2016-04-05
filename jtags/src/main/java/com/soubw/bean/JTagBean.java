package com.soubw.bean;

import android.graphics.Color;

import com.soubw.jtaglayout.R;
import com.soubw.jtags.JPolyLineType;
import com.soubw.jtags.JTagType;

/**
 * Created by WX_JIN on 2016/4/2.
 * 标签实体类
 */
public class JTagBean {

    private int jTagX;
    private int jTagY;
    private int jTagTextColor = Color.parseColor("#ffffff");
    private int jTagTextSize = 14;
    private String jTagContent;
    private int jTagBg = R.drawable.jtag_bg;
    private JTagType jtagType = JTagType.NONE;
    private JPolyLineType jPolyLineType = JPolyLineType.RIGHT_TOP;

    public JTagBean(int jTagX, int jTagY, String jTagContent, JTagType jtagType) {
        this.jTagX = jTagX;
        this.jTagY = jTagY;
        this.jTagContent = jTagContent;
        this.jtagType = jtagType;
    }

    public JTagBean(int jTagX, int jTagY, int jTagTextColor, int jTagTextSize, String jTagContent, int jTagBg, JTagType jtagType, JPolyLineType jPolyLineType) {
        this.jTagX = jTagX;
        this.jTagY = jTagY;
        this.jTagTextColor = jTagTextColor;
        this.jTagTextSize = jTagTextSize;
        this.jTagContent = jTagContent;
        this.jTagBg = jTagBg;
        this.jtagType = jtagType;
        this.jPolyLineType = jPolyLineType;
    }

    public int getJTagX() {
        return jTagX;
    }

    public void setTagX(int jTagX) {
        this.jTagX = jTagX;
    }

    public int getJTagY() {
        return jTagY;
    }

    public void setJTagY(int jTagY) {
        this.jTagY = jTagY;
    }

    public int getJTagTextColor() {
        return jTagTextColor;
    }

    public void setJTagTextColor(int jTagTextColor) {
        this.jTagTextColor = jTagTextColor;
    }

    public int getJTagTextSize() {
        return jTagTextSize;
    }

    public void setJTagTextSize(int jTagTextSize) {
        this.jTagTextSize = jTagTextSize;
    }

    public String getJTagContent() {
        return jTagContent;
    }

    public void setJTagContent(String jTagContent) {
        this.jTagContent = jTagContent;
    }

    public int getjTagBg() {
        return jTagBg;
    }

    public void setjTagBg(int jTagBg) {
        this.jTagBg = jTagBg;
    }

    public JTagType getJtagType() {
        return jtagType;
    }

    public void setJtagType(JTagType jtagType) {
        this.jtagType = jtagType;
    }

    public JPolyLineType getjPolyLineType() {
        return jPolyLineType;
    }

    public void setjPolyLineType(JPolyLineType jPolyLineType) {
        this.jPolyLineType = jPolyLineType;
    }
}