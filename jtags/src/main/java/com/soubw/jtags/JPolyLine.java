package com.soubw.jtags;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.soubw.jtags.bean.JTagBean;

/**
 * Created by WX_JIN on 2016/4/3.
 */
public class JPolyLine extends View {

    private Paint mPaint;
    private JTagBean mJTagBean;


    public JPolyLine(Context context) {
        this(context,null);
    }

    public JPolyLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public JPolyLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public JPolyLine(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    public void setPolyLine(JTagBean jTagBean){
        this.mJTagBean = jTagBean;
        this.invalidate();
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolyLine(canvas);
    }

    private void drawPolyLine(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#ffffff"));
        canvas.drawCircle(JTag.POINT_RADIUS,JTag.POINT_RADIUS, JTag.POINT_RADIUS,mPaint);
        if( mJTagBean != null && mJTagBean.getJTagType() == JTagType.POLYLINE){
            Path mPath = new Path();
            mPaint.setStyle(Paint.Style.STROKE);
            switch (mJTagBean.getjPolyLineType()){
                case LEFT_TOP:
                    mPath.moveTo(0,JTag.POINT_RADIUS);
                    mPath.lineTo(-JTag.LINE_LENGTH,JTag.POINT_RADIUS);
                    mPath.lineTo(-JTag.LINE_LENGTH*2,-JTag.LINE_LENGTH);
                    canvas.drawCircle(-JTag.LINE_LENGTH*2,-JTag.LINE_LENGTH, JTag.POINT_RADIUS/2,mPaint);
                    break;
                case LEFT_BOTTOM:
                    mPath.moveTo(0,JTag.POINT_RADIUS);
                    mPath.lineTo(-JTag.LINE_LENGTH,JTag.POINT_RADIUS);
                    mPath.lineTo(-JTag.LINE_LENGTH*2,JTag.LINE_LENGTH);
                    canvas.drawCircle(-JTag.LINE_LENGTH*2,JTag.LINE_LENGTH, JTag.POINT_RADIUS/2,mPaint);
                    break;
                case RIGHT_TOP:
                    mPath.moveTo(JTag.POINT_RADIUS*2,JTag.POINT_RADIUS);
                    mPath.lineTo(JTag.LINE_LENGTH,JTag.POINT_RADIUS);
                    mPath.lineTo(JTag.LINE_LENGTH*2,-JTag.LINE_LENGTH);
                    canvas.drawCircle(JTag.LINE_LENGTH*2,-JTag.LINE_LENGTH, JTag.POINT_RADIUS/2,mPaint);
                    break;
                case RIGHT_BOTTOM:
                    mPath.moveTo(JTag.POINT_RADIUS*2,JTag.POINT_RADIUS);
                    mPath.lineTo(JTag.LINE_LENGTH,JTag.POINT_RADIUS);
                    mPath.lineTo(JTag.LINE_LENGTH*2,JTag.LINE_LENGTH);
                    canvas.drawCircle(JTag.LINE_LENGTH*2,JTag.LINE_LENGTH, JTag.POINT_RADIUS/2,mPaint);
                    break;
            }
            canvas.drawPath(mPath,mPaint);
        }



    }
}
