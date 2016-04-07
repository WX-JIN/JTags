package com.soubw.jtags;

import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.soubw.jtags.bean.JTagBean;

import java.util.ArrayList;

/**
 * Created by WX_JIN on 2016/4/2.
 */
public class JTagLayout extends RelativeLayout {

    private ArrayList<JTagBean> mJTagBeans;
    private ArrayList<JTag> mJTags;

    private boolean isHaveBg = false;

    private AnimatorSet mAnimatorSet;
    private boolean isAnimRunning = false;

    private OnJTagClickListener onJTagClickListener;

    private View mBgView;

    private int tempNextIndex = 0;

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

    public void setJTags(ArrayList<JTagBean> jTagBeans){
        this.mJTagBeans = jTagBeans;
        if (mJTags != null && mJTags.size() > 0){
            this.removeAllViews();
            mJTags.clear();
        }
        initTagLayout();
    }

    public void addJTag(JTagBean bean){
        if(mAnimatorSet == null){
            mAnimatorSet = new AnimatorSet();
        }
        if(mJTags == null){
            mJTags = new ArrayList<>();
        }else{
            initJTag(bean);
        }
    }

    private void initTagLayout(){
        if(mAnimatorSet == null){
            mAnimatorSet = new AnimatorSet();
        }
        if(mJTags == null){
            mJTags = new ArrayList<>();
        }else{
            mJTags.clear();
        }
        int tagBeanCount = mJTagBeans.size();
        for(int i = 0; i < tagBeanCount; i++){
            initJTag(mJTagBeans.get(i));
        }
        if(isHaveBg){
            nextStep();
        }
    }

    private synchronized void initJTag(JTagBean bean){
        if(bean.getJTagType() == JTagType.BG){
            if(mBgView == null){
                mBgView = new View(getContext());
                try {
                    mBgView.setBackgroundResource(bean.getJTagBg());
                }catch (Exception e){
                    mBgView.setBackgroundColor(bean.getJTagBg());
                }
                this.addView(mBgView,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
                isHaveBg = true;
        }
        }else{
            JTag jTag = new JTag(getContext());
            mJTags.add(jTag);
            jTag.setJTagBean(bean);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            int leftMargin = bean.getJTagX();
            int topMargin = bean.getJTagY();
            switch (bean.getJTagType()){
                case NONE:
                    leftMargin = bean.getJTagX() - jTag.getContentViewWidth()*1/3;
                    topMargin = bean.getJTagY() - jTag.getContentViewHeight()*1/3;
                    break;
                case CIRCLE:
                    leftMargin = bean.getJTagX() - jTag.getContentViewWidth()*1/3;
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
            if(jTag.getContentView() != null){
                jTag.getContentView().setOnClickListener(onClickListener);
            }
        }
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onJTagClickListener != null){
                onJTagClickListener.onJTagClick((JTag) v.getTag());
                if(isHaveBg){
                    nextStep();
                }
            }
        }
    };

    public void setOnJTagsClickListener(final OnJTagClickListener onJTagClickListener){
        this.onJTagClickListener = onJTagClickListener;

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

    public void showAllJTags(){
        for(int i=0;i < mJTags.size();i++){
            JTag jTag = mJTags.get(i);
            jTag.showJTag();
        }
    }

    public void hideAllJTags(){
        for(int i=0;i < mJTags.size();i++){
            JTag jTag = mJTags.get(i);
            jTag.hideJTag();
        }
    }

    public void nextStep(){
        if(mJTags == null || mJTags.isEmpty()){
            return;
        }
        hideAllJTags();
        if (tempNextIndex < mJTags.size()){
            JTag jTag = mJTags.get(tempNextIndex);
            jTag.showJTag();
            tempNextIndex++;
            return;
        }else{
            dismissGuide();
        }
    }

    public void dismissGuide(){
        if(mBgView != null){
            this.removeView(mBgView);
            mBgView = null;
            isHaveBg =false;
        }
        if (mJTags != null && mJTags.size() > 0){
            this.removeAllViews();
            mJTags.clear();
            mJTagBeans.clear();
        }
        tempNextIndex = 0;
    }

}
