package com.soubw.jtags.bean;

import android.graphics.Color;

import com.soubw.jtaglayout.R;
import com.soubw.jtags.JPolyLineType;
import com.soubw.jtags.JTagType;

/**
 * Created by WX_JIN on 2016/4/2.
 */
public class JTagBean {

    private int jTagX;
    private int jTagY;
    private int jTagTextColor = Color.parseColor("#ffffff");
    private int jTagTextSize = 14;
    private String jTagContent;
    private int jTagBg = R.drawable.jtag_bg;
    private JTagType jTagType = JTagType.CIRCLE;
    private JPolyLineType jPolyLineType = JPolyLineType.RIGHT_TOP;

    public JTagBean(JTagType jtagType, int jTagBg) {
        this.jTagType = jtagType;
        this.jTagBg = jTagBg;
    }

    public JTagBean(int jTagX, int jTagY, String jTagContent, JTagType jTagType) {
        this.jTagX = jTagX;
        this.jTagY = jTagY;
        this.jTagContent = jTagContent;
        this.jTagType = jTagType;
    }

    public JTagBean(int jTagX, int jTagY, int jTagTextColor, int jTagTextSize, String jTagContent, int jTagBg, JTagType jTagType, JPolyLineType jPolyLineType) {
        this.jTagX = jTagX;
        this.jTagY = jTagY;
        this.jTagTextColor = jTagTextColor;
        this.jTagTextSize = jTagTextSize;
        this.jTagContent = jTagContent;
        this.jTagBg = jTagBg;
        this.jTagType = jTagType;
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

    public int getJTagBg() {
        return jTagBg;
    }

    public void setJTagBg(int jTagBg) {
        this.jTagBg = jTagBg;
    }

    public JTagType getJTagType() {
        return jTagType;
    }

    public void setJTagType(JTagType jTagType) {
        this.jTagType = jTagType;
    }

    public JPolyLineType getjPolyLineType() {
        return jPolyLineType;
    }

    public void setjPolyLineType(JPolyLineType jPolyLineType) {
        this.jPolyLineType = jPolyLineType;
    }
}