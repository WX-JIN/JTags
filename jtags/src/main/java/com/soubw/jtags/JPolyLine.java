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

import com.soubw.bean.JTagBean;

/**
 * Created by WX_JIN on 2016/4/3.
 */
public class JPolyLine extends View {

    private Paint mPaint;
    private JTagBean mJTagBean;
    private static int POLYLINE_WIDTH = 50;
    private static int POLYLINE_HEIGHT = 50;


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
        mPaint.setStrokeCap(Paint.Cap.BUTT);//当画笔样式为STROKE空心或者FILL_AND_STROKE两者时，设置
        //笔刷图形样式，ROUND圆形,SQUARE方形画笔结束后里面没有马上停止画，BUTT方形画笔结束后里面停止画
        mPaint.setStrokeJoin(Paint.Join.MITER);//BEVEL图形接触为横线，ROUND为圆形接触，MITER直角接触
        mPaint.setStrokeWidth(3);//当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度
        mPaint.setAntiAlias(true);//设置是否使用抗锯齿，很消耗资源，绘制速度变慢。
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolyLine(canvas);
    }

    private void drawPolyLine(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);//画笔样式，FILL实心，STROKE空心，FILL_AND_STROKE两者
        mPaint.setColor(Color.parseColor("#ffffff"));//设置画笔颜色
        canvas.drawCircle(JTag.POINT_RADIUS,JTag.POINT_RADIUS, JTag.POINT_RADIUS,mPaint);// 绘制圆，参数一是中心点的x轴，参数二是中心点的y轴，参数三是半径，参数四是paint对象；

        if( mJTagBean != null && mJTagBean.getJtagType() == JTagType.POLYLINE){
            Path mPath = new Path();
            mPaint.setStyle(Paint.Style.STROKE);//画笔样式，FILL实心，STROKE空心，FILL_AND_STROKE两者
            mPaint.setColor(Color.parseColor("#000000"));//设置画笔颜色
            switch (mJTagBean.getjPolyLineType()){
                case LEFT_TOP:
                    mPath.moveTo(0,JTag.POINT_RADIUS);
                    mPath.lineTo(-JTag.LINE_LENGTH,JTag.POINT_RADIUS);
                    mPath.lineTo(-JTag.LINE_LENGTH*2,-JTag.LINE_LENGTH);
                    break;
                case LEFT_BOTTOM:
                    mPath.moveTo(0,JTag.POINT_RADIUS);
                    mPath.lineTo(-JTag.LINE_LENGTH,JTag.POINT_RADIUS);
                    mPath.lineTo(-JTag.LINE_LENGTH*2,JTag.LINE_LENGTH);
                    break;
                case RIGHT_TOP:
                    mPath.moveTo(JTag.POINT_RADIUS*2,JTag.POINT_RADIUS);
                    mPath.lineTo(JTag.LINE_LENGTH,JTag.POINT_RADIUS);
                    mPath.lineTo(JTag.LINE_LENGTH*2,-JTag.LINE_LENGTH);
                    break;
                case RIGHT_BOTTOM:
                    mPath.moveTo(JTag.POINT_RADIUS*2,JTag.POINT_RADIUS);
                    mPath.lineTo(JTag.LINE_LENGTH,JTag.POINT_RADIUS);
                    mPath.lineTo(JTag.LINE_LENGTH*2,JTag.LINE_LENGTH);
                    break;
            }
            canvas.drawPath(mPath,mPaint);
        }



    }
}
