package com.example.simple.view;

import com.example.simple.R;
import com.example.simple.R.styleable;

import utils.DisplayUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * ��view�Ǻ�����״ͼ��view��ʵ��������������
 * 
 * 1������ĳ��ȱ�ʾ��С
 * 
 * 2�������Խ����ɫԽ�Խ����ɫԽǳ
 * 
 * @author YocnZhao
 * 
 */
public class BarChartView extends View {

    /** ��״���ĸ߶� */
    private int chartHeight = 0;
    /** ��״���ĳ��� */
    private int chartWidth = 255;
    /** ��״������ɫֵ */
    private int chartColor = 0xff0000ff;

    public BarChartView(Context context) {
        super(context);
    }

    public BarChartView(Context context, int width) {
        super(context);
        chartWidth = width;
        chartColor = Color.argb(255, 255, 255, 0);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BarChartView);
        chartHeight = a.getInt(R.styleable.BarChartView_bar_height, 20);
        chartWidth = a.getResourceId(R.styleable.BarChartView_bar_width, 10);
        a.recycle();
        System.out.println("chartHeight--" + chartHeight);
        System.out.println("chartWidth--" + chartWidth);
        chartHeight = DisplayUtils.Dp2Px(context, chartHeight);
        System.out.println("chartHeight--" + DisplayUtils.Dp2Px(context, chartHeight));
        System.out.println("chartWidth--" + DisplayUtils.Dp2Px(context, chartHeight));
        
    }

    /**
     * �ı�view����ɫֵ
     * 
     * @param color
     *            0~255֮�䣬Խ���ʾ��ɫԽ��
     */
    public void setColor(int color) {
        chartWidth = color;
        /** ����һ��color����ø�����RGB��intֵ */
        chartColor = Color.argb(255, 255, 255 - color, 0);
    }

    public void setHeight(int height) {
        if (chartHeight != 0) {
            chartHeight = height;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(chartColor);
        p.setAntiAlias(true);
        p.setStrokeWidth(10);

        Bitmap b = Bitmap.createBitmap(chartWidth, chartHeight, Bitmap.Config.ARGB_4444);

        // canvas.drawCircle(50, x1, 10, p);
        canvas.drawRect(0, 0, chartWidth, chartHeight, p);
        super.onDraw(canvas);
    }

}
