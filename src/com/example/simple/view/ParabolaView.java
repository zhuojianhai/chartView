package com.example.simple.view;

import utils.DisplayUtils;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class ParabolaView extends ImageView {
    Path path2 = new Path();
    Paint p = new Paint();
    private float x;
    private float y;
    private float mWidth;
    private float mBaseHeight;

    public ParabolaView(Context context) {
        super(context);

    }

    public ParabolaView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ParabolaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mWidth = DisplayUtils.getScreenWidth(context);
        mBaseHeight = DisplayUtils.getScreenHeight(context) / 2;
        p.setColor(Color.BLUE);
        p.setAntiAlias(true);
        p.setStrokeWidth(10);
        path2.moveTo(0, mBaseHeight);// ����Path�����
        path2.quadTo(250, 0, mWidth, mBaseHeight); // ���ñ��������ߵĿ��Ƶ�������յ�����
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            break;
        case MotionEvent.ACTION_MOVE:
            path2.reset();
            x = event.getX();
            y = event.getY();
            path2.moveTo(0, mBaseHeight);
            path2.quadTo(x, y, mWidth, mBaseHeight);
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.BLUE);
        p.setAntiAlias(true);
        p.setStrokeWidth(10);
        canvas.drawPath(path2, p);// ��������������
        p.setColor(Color.RED);
        p.setStrokeWidth(5);
        canvas.drawLine(0, mBaseHeight, x, y, p);
        canvas.drawLine(mWidth / 2, mBaseHeight, x, y, p);
        canvas.drawLine(0, mBaseHeight, mWidth, mBaseHeight, p);
        canvas.drawLine(mWidth, mBaseHeight, x, y, p);
    }

    /**
     * ���ݸ�����x�������Ӧ��y��
     * 
     * @param x
     * @return
     */
    private float convertX2Y(float x) {
        float y = x * x - 20 * x;
        return y;
    }
}
