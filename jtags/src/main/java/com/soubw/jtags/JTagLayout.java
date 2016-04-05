package com.soubw.jtags;

import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.soubw.bean.JTagBean;

import java.util.ArrayList;

/**
 * Created by WX_JIN on 2016/4/2.
 */
public class JTagLayout extends RelativeLayout {

    private ArrayList<JTagBean> mJTagBeans;
    private ArrayList<JTag> mJTags;
    private boolean isShowJTags = false;

    private AnimatorSet mAnimatorSet;
    private boolean isAnimRunning = false;

    public JTagLayout(Context context) {
        this(context,null);
    }

    public JTagLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public JTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setClipChildren(false);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public JTagLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setClipChildren(false);
    }

    public synchronized void setJTags(ArrayList<JTagBean> jTagBeans){
        this.mJTagBeans = jTagBeans;
        if (mJTags != null && mJTags.size() > 0){
            this.removeAllViews();
            mJTags.clear();
            isShowJTags = false;
        }
        addTags();
    }

    public void setOnJTagsClickListener(final OnJTagClickListener onJTagClickListener){
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onJTagClickListener != null){
                    onJTagClickListener.onJTagClick((JTag) v.getTag());
                }
            }
        };
        for(int i=0;i < mJTags.size();i++){
            JTag jTag = mJTags.get(i);
            if(jTag.getContentView() != null){
                jTag.getContentView().setOnClickListener(onClickListener);
            }
        }

    }

    private void addTags(){
        if(mAnimatorSet == null){
            mAnimatorSet = new AnimatorSet();
        }
        int tagBeanCount = mJTagBeans.size();
        if(mJTags == null){
            mJTags = new ArrayList<>();
        }else{
            mJTags.clear();
        }
        for(int i = 0; i < tagBeanCount; i++){
            JTagBean bean = mJTagBeans.get(i);
            JTag jTag = new JTag(getContext());
            mJTags.add(jTag);
            jTag.setJTagBean(bean);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            int leftMargin = bean.getJTagX();
            int topMargin = bean.getJTagY();
            switch (bean.getJtagType()){
                case GUIDE:
                    break;
                case NONE:
                    leftMargin = bean.getJTagX() - 10;
                    topMargin = bean.getJTagY() - jTag.getContentViewHeight()*1/3;
                    break;
                case POLYLINE:
                    switch (bean.getjPolyLineType()){
                        case LEFT_TOP:
                            leftMargin = bean.getJTagX() - JTag.CONTENT_POS_X - jTag.getContentViewWidth();
                            topMargin = bean.getJTagY() - JTag.CONTENT_POS_Y - jTag.getContentViewHeight()/2;
                            break;
                        case LEFT_BOTTOM:
                            leftMargin = bean.getJTagX() - JTag.CONTENT_POS_X - jTag.getContentViewWidth();
                            topMargin = bean.getJTagY();
                            break;
                        case RIGHT_TOP:
                            leftMargin = bean.getJTagX();
                            topMargin = bean.getJTagY() - JTag.CONTENT_POS_Y - jTag.getContentViewHeight()/2;
                            break;
                        case RIGHT_BOTTOM:
                            leftMargin = bean.getJTagX();
                            topMargin = bean.getJTagY();
                            break;
                    }
                    break;
            }
            lp.setMargins(leftMargin,topMargin,0,0);
            this.addView(jTag,lp);
            jTag.reloadJTagPosition();
        }

    }


    public void startAllAnim(){
        if(isAnimRunning){
            return;
        }
        for(int i=0;i < mJTags.size();i++){
            JTag jTag = mJTags.get(i);
            jTag.startAnim();
        }
        isAnimRunning = true;
    }

    public void cancelAllAnim(){
        if (!isAnimRunning){
            return;
        }
        for(int i=0;i < mJTags.size();i++){
            JTag jTag = mJTags.get(i);
            jTag.cancelAnim();
        }
        isAnimRunning = false;
    }

}
