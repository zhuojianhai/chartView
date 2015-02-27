package com.example.simple;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ChartView extends ImageView {

    public static int height = 300;
    private int picHeight = 350;
    public static int width = 600;
    private int x1 = 0;
    private int x2 = 0;
    private int x3 = 0;
    private int x4 = 0;
    private int x5 = 0;
    private String time0 = "";
    private String time1 = "";
    private String time2 = "";
    private String time3 = "";
    private String time4 = "";
    private int money1 = 0;
    private int money2 = 0;
    private int money3 = 0;
    private int money4 = 0;

    public ChartView(Context context) {
        super(context);

    }

    public ChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param x1
     * @param x2
     * @param x3
     * @param x4
     */
    public void setNum(int x1, int x2, int x3, int x4, int x5) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
    }

    /**
     * 
     * @param time1
     * @param time2
     * @param time3
     * @param time4
     */
    public void setTime(String time0,String time1, String time2, String time3, String time4) {
        this.time0 = time0;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
    }

    /**
     * 
     * @param money1
     * @param money2
     * @param money3
     * @param money4
     */
    public void setMoney(int money1, int money2, int money3, int money4) {
        this.money1 = money1;
        this.money2 = money2;
        this.money3 = money3;
        this.money4 = money4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub

        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setAntiAlias(true);
        p.setStrokeWidth(10);

        Bitmap b = Bitmap.createBitmap(width, picHeight, Bitmap.Config.ARGB_4444);

        canvas.drawCircle(50, x1, 10, p);
        canvas.drawCircle((width - 50) / 4 + 50, x2, 10, p);
        canvas.drawCircle((width - 50) / 2 + 50, x3, 10, p);
        canvas.drawCircle((width - 50) * 3 / 4 + 50, x4, 10, p);
        canvas.drawCircle(width, x5, 10, p);

        canvas.drawLine(50, x1, (width - 50) / 4 + 50, x2, p);
        canvas.drawLine((width - 50) / 4 + 50, x2, (width - 50) / 2 + 50, x3, p);
        canvas.drawLine((width - 50) / 2 + 50, x3, (width - 50) * 3 / 4 + 50, x4, p);
        canvas.drawLine((width - 50) * 3 / 4 + 50, x4, width, x5, p);

        p.setStrokeWidth(5);
        canvas.drawLine(50, 0, 50, height, p);
        canvas.drawLine(50, height, width, height, p);

        p.setTextSize(20);
        canvas.drawText("100", 13, height / 5, p);
        canvas.drawLine(50, height / 5, 52, height / 5, p);
        canvas.drawText("75", 13, height * 2 / 5, p);
        canvas.drawLine(50, height * 2 / 5, 52, height * 2 / 5, p);
        canvas.drawText("50", 13, height * 3 / 5, p);
        canvas.drawLine(50, height * 3 / 5, 52, height * 3 / 5, p);
        canvas.drawText("25", 13, height * 4 / 5, p);
        canvas.drawLine(50, height * 4 / 5, 52, height * 4 / 5, p);
        canvas.drawText("0", 13, height, p);

        canvas.drawText(time0, 25, height + 20, p);
        canvas.drawText(time1, 25 + (width - 50) / 4, height + 20, p);
        canvas.drawText(time2, 25 + (width - 50) * 2 / 4, height + 20, p);
        canvas.drawText(time3, 25 + (width - 50) * 3 / 4, height + 20, p);
        canvas.drawText(time4, 25 + (width - 50), height + 20, p);

        p.setStrokeWidth(2);
        p.setStyle(Style.FILL);
        canvas.drawLine(50, 0, width, 0, p);
        canvas.drawLine(50, height * 4 / 5, width, height * 4 / 5, p);
        canvas.drawLine(50, height * 3 / 5, width, height * 3 / 5, p);
        canvas.drawLine(50, height * 2 / 5, width, height * 2 / 5, p);
        canvas.drawLine(50, height * 1 / 5, width, height * 1 / 5, p);

        canvas.drawLine(50 + (width - 50) / 4, 0, 50 + (width - 50) / 4, height, p);
        canvas.drawLine(50 + (width - 50) * 2 / 4, 0, 50 + (width - 50) * 2 / 4, height, p);
        canvas.drawLine(50 + (width - 50) * 3 / 4, 0, 50 + (width - 50) * 3 / 4, height, p);
        canvas.drawLine(50 + (width - 50), 0, 50 + (width - 50), height, p);
    }

}
