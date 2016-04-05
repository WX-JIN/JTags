package com.soubw.jtags;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.soubw.bean.JTagBean;
import com.soubw.jtaglayout.R;

/**
 * Created by WX_JIN on 2016/4/2.
 */
public class JTag extends FrameLayout{


    private JTagBean mJTagBean;

    private TextView mContentView;
    private int mContentViewWidth;
    private int mContentViewHeight;

    private ImageView mPointView;
    public final static int POINT_RADIUS = 5;
    public final static int LINE_LENGTH = POINT_RADIUS*12;
    public static int CONTENT_POS_X = LINE_LENGTH*2;
    public static int CONTENT_POS_Y = LINE_LENGTH;


    private JPolyLine mJPolyLine;

    private AnimatorSet mAnimatorSet;


    public JTag(Context context) {
        this(context,null);
    }

    public JTag(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public JTag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setClipChildren(false);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public JTag(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setClipChildren(false);
    }

    public void setJTagBean(JTagBean jTagBean){
        this.mJTagBean = jTagBean;
        addJTag();
    }

    private void addJTag() {
        addContentView();
        addPointView();

    }

    private void addPointView() {
        mPointView = new ImageView(this.getContext());
        MarginLayoutParams mlp =  new MarginLayoutParams(POINT_RADIUS*2,POINT_RADIUS*2);
        mPointView.setBackgroundResource(R.drawable.jtag_circle);
        this.addView(mPointView, mlp);
        addAnim();
    }

    public void addAnim(){
        mAnimatorSet = new AnimatorSet();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mPointView, "Alpha", 1, 0);
        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnimator.setRepeatMode(ValueAnimator.RESTART);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mPointView, "scaleX", 1, 3f);
        scaleXAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleXAnimator.setRepeatMode(ValueAnimator.RESTART);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mPointView, "scaleY", 1, 3f);
        scaleYAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleYAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimatorSet.playTogether(alphaAnimator,
                scaleXAnimator,
                scaleYAnimator);
        mAnimatorSet.setDuration(1800);
        mAnimatorSet.start();
    }

    public void startAnim(){
        if(mAnimatorSet.isRunning()){
            return;
        }
        mAnimatorSet.start();
    }

    public void cancelAnim(){
        if (!mAnimatorSet.isRunning()){
            return;
        }
        mAnimatorSet.cancel();
    }


    private void addContentView(){
        mContentView = new TextView(this.getContext());
        mContentView.setSingleLine();
        mContentView.setTextColor(mJTagBean.getJTagTextColor());
        mContentView.setTextSize(mJTagBean.getJTagTextSize());
        mContentView.setText(String.format("    %s    ",mJTagBean.getJTagContent()));
        MarginLayoutParams mlp = new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        mContentView.setGravity(Gravity.CENTER);
        mContentView.setBackgroundResource(mJTagBean.getjTagBg());
        mContentView.setTag(this);
        mContentView.measure(0, 0);
        mContentViewWidth = mContentView.getMeasuredWidth();
        mContentViewHeight = mContentView.getMeasuredHeight();
        this.addView(mContentView, mlp);

    }

    public int getContentViewWidth(){
        return mContentViewWidth;
    }

    public int getContentViewHeight(){
        return mContentViewHeight;
    }

    public View getContentView(){
        return mContentView;
    }

    public JTagBean getJTagBean(){
        return mJTagBean;
    }

    public void reloadJTagPosition(){
        mJPolyLine = new JPolyLine(this.getContext());
        this.addView(mJPolyLine);
        mJPolyLine.setPolyLine(mJTagBean);
        MarginLayoutParams pointLp = (MarginLayoutParams) mPointView.getLayoutParams();
        MarginLayoutParams polyLineLp = (MarginLayoutParams) mJPolyLine.getLayoutParams();
        MarginLayoutParams contentLp = (MarginLayoutParams) mContentView.getLayoutParams();
        int leftMargin = 0;
        int topMargin = 0;
        switch (mJTagBean.getJtagType()){
            case GUIDE:
                break;
            case NONE:
                leftMargin = 10;
                topMargin = mContentViewHeight*1/3;
                break;
            case POLYLINE:
                switch (mJTagBean.getjPolyLineType()){
                    case LEFT_TOP:
                        leftMargin = JTag.CONTENT_POS_X + mContentViewWidth;
                        topMargin = JTag.CONTENT_POS_Y + mContentViewHeight/2;
                        break;
                    case LEFT_BOTTOM:
                        leftMargin = JTag.CONTENT_POS_X + mContentViewWidth;
                        contentLp.topMargin = JTag.CONTENT_POS_Y - mContentViewHeight/2;
                        break;
                    case RIGHT_TOP:
                        topMargin = JTag.CONTENT_POS_Y + mContentViewHeight/2;
                        contentLp.leftMargin = JTag.CONTENT_POS_X;
                        break;
                    case RIGHT_BOTTOM:
                        contentLp.leftMargin = JTag.CONTENT_POS_X;
                        contentLp.topMargin = JTag.CONTENT_POS_Y - mContentViewHeight/2;
                        break;
                }
                break;
        }
        pointLp.leftMargin = leftMargin;
        pointLp.topMargin = topMargin;
        polyLineLp.leftMargin = leftMargin;
        polyLineLp.topMargin = topMargin;
    }


}
