package com.example.simple.view;

import java.util.ArrayList;
import java.util.List;

import utils.ChartBean;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.simple.R;

public class ChartView extends ImageView {

    /**
     * ��Ҫ����һ��ChartBeanList����������������ʾ��ÿ�������Ϣ������ʱ���ͼ۸�
     * 
     */

    public static int height = 300;
    public static double width = 600;

    private int heightReal;// ͼ��ʵ�ʵĸ߶�
    private int heightText = 70;// �ײ���ʾʱ�������
    private int heightTop = 0;// ͼ������Ŀհ���

    private int multi = 1;// ����������ֱַ��ʵĻ����ģ�Ϊ����
    private int radi = 5 * multi;// Բ��İ뾶

    private int widthReal;// ͼ��ʵ�ʵĿ��
    private int widthText = 50 * multi;// ͼ�������ʾ�۸������
    private int widthHead = 50;// �۸������ұߣ�ͼ����ߵĿհ���
    private int widthFoot = 25 * multi;
    private int miles = 5;// �����ĺ�����
    private int px = 10;// �����ĺ�����
    private boolean hasAnim = false;

    private double x1 = 0;
    private double x2 = 0;
    private double x3 = 0;
    private double x4 = 0;
    private double x5 = 0;

    private PointF p1 = new PointF();
    private PointF p2 = new PointF();
    private PointF p3 = new PointF();
    private PointF p4 = new PointF();
    private PointF p5 = new PointF();
    private float p2x;
    private float p3x;
    private float p4x;
    private float p5x;
    private float p2y;
    private float p3y;
    private float p4y;
    private float p5y;
    boolean p2Init = true;
    boolean isFirstRun = true;
    boolean x1Complete = true;
    boolean x2Complete = false;
    boolean x3Complete = false;
    boolean x4Complete = false;
    boolean x5Complete = false;
    private double min = 0d;
    private double max = 100000d;
    private PointF pic;
    private List<ChartBean> mChartBeanList = new ArrayList<ChartBean>();
    private double price;
    private String unit = "Ԫ/��";
    private MyHandler handler;
    private Paint p;
    private float left;
    private float top;
    private float right;
    private float bottom;
    private int textWidth;

    public ChartView(Context context) {
        super(context);
        init();
    }

    public ChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        handler = new MyHandler();

    }

    public void setData(List<ChartBean> list, String unit, boolean hasAnim) {
        this.mChartBeanList = list;
        this.hasAnim = hasAnim;
        if ("".equals(unit)) {
            this.unit = unit;
        }
        // 4,6,2,3,5
        try {
            x1 = list.get(0).getPrice();
            x2 = list.get(1).getPrice();
            x3 = list.get(2).getPrice();
            x4 = list.get(3).getPrice();
            x5 = list.get(4).getPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
        max = list.get(0).getPrice();
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i).getPrice()) {
                max = list.get(i).getPrice();
            }
        }
    }

    public void setMaxMin(double max, double min) {
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.GRAY);
        p.setTypeface(Typeface.DEFAULT);
        Path path = new Path();

        width = canvas.getWidth();
        height = canvas.getHeight();
        multi = (int) Math.ceil(width / 560);
        int width = canvas.getWidth();
        widthText = 50 * multi;// ͼ�������ʾ�۸������
        widthFoot = 25 * multi;
        radi = 5 * multi;

        heightTop = 0;
        heightReal = height - heightText - heightTop;
        heightTop = heightReal / 4;
        heightReal = height - heightText - heightTop;
        widthReal = ((int) width - widthText - widthHead - widthFoot);

        /** ����ߵļ۸����� */
        p.setTextSize(17 * multi);
        canvas.drawText(String.format("%.2f", (min + (max - min))), widthText - 35 * multi, heightTop, p);
        canvas.drawText(String.format("%.2f", (min + (max - min) * 2 / 3)), widthText - 35 * multi, heightTop
                + heightReal / 3, p);
        canvas.drawText(String.format("%.2f", (min + (max - min) / 3)), widthText - 35 * multi, heightTop + heightReal
                * 2 / 3, p);
        canvas.drawText(unit, widthText - 35 * multi - 5, heightTop + heightReal + 5, p);
        /** �����µ��������� */
        try {
            switch (mChartBeanList.size()) {
            case 5:
                canvas.drawText(mChartBeanList.get(4).getDate() + "", widthText + widthReal, 50 + heightTop
                        + heightReal, p);
            case 4:
                canvas.drawText(mChartBeanList.get(3).getDate() + "", widthText + widthReal * 3 / 4, 50 + heightTop
                        + heightReal, p);
            case 3:
                canvas.drawText(mChartBeanList.get(2).getDate() + "", widthText + widthReal / 2, 50 + heightTop
                        + heightReal, p);
            case 2:
                canvas.drawText(mChartBeanList.get(1).getDate() + "", widthText + widthReal / 4, 50 + heightTop
                        + heightReal, p);
            case 1:
                canvas.drawText(mChartBeanList.get(0).getDate() + "", widthText, 50 + heightTop + heightReal, p);
            case 0:
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        p1.x = widthText + widthHead;
        p1.y = (float) (heightTop + getHeight(x1));
        p2.x = widthText + widthHead + widthReal / 4;
        p2.y = (float) (heightTop + getHeight(x2));
        p3.x = widthText + widthHead + widthReal / 2;
        p3.y = (float) (heightTop + getHeight(x3));
        p4.x = widthText + widthHead + widthReal * 3 / 4;
        p4.y = (float) (heightTop + getHeight(x4));
        p5.x = widthText + widthHead + widthReal;
        p5.y = (float) (heightTop + getHeight(x5));
        if (p2Init) {
            /** ��ʼ��pp2 */
            p2x = p1.x;
            p2y = p1.y;
            p2Init = false;
        }

        /** ����µĴ��� */
        p.setColor(Color.GRAY);
        p.setStrokeWidth(2);
        canvas.drawLine(widthText, heightTop + heightReal, (float) width, heightTop + heightReal, p);

        /** ���������� */
        p.setColor(Color.GRAY);
        p.setStrokeWidth(1);
        p.setStyle(Paint.Style.STROKE);
        PathEffect effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
        p.setPathEffect(effects);
        path.moveTo(widthText, heightTop + heightReal * 2 / 3);
        path.lineTo(width, heightTop + heightReal * 2 / 3);
        canvas.drawPath(path, p);
        path.moveTo(widthText, heightTop + heightReal / 3);
        path.lineTo(width, heightTop + heightReal / 3);
        canvas.drawPath(path, p);
        path.moveTo(widthText, heightTop);
        path.lineTo(width, heightTop);
        canvas.drawPath(path, p);
        p.setStyle(Paint.Style.FILL);
        p.setColor(getResources().getColor(R.color.green_chart));
        p.setStrokeWidth(3 * multi);
        /** ������������������ */
        try {
            switch (mChartBeanList.size()) {
            case 0:
                break;
            case 1:
                canvas.drawCircle(p1.x, p1.y, radi, p);
                if (pic == null) {
                    pic = new PointF();
                    pic = p1;
                    price = x1;
                }
                break;
            case 2:
                drawLine(canvas, p);
                canvas.drawCircle(p1.x, p1.y, radi, p);
                canvas.drawCircle(p2.x, p2.y, radi, p);
                if (pic == null) {
                    pic = new PointF();
                    pic = p2;
                    price = x2;
                }
                break;
            case 3:
                drawLine(canvas, p);
                canvas.drawCircle(p1.x, p1.y, radi, p);
                canvas.drawCircle(p2.x, p2.y, radi, p);
                canvas.drawCircle(p3.x, p3.y, radi, p);
                if (pic == null) {
                    pic = new PointF();
                    pic = p3;
                    price = x3;
                }
                break;
            case 4:
                drawLine(canvas, p);
                canvas.drawCircle(p1.x, p1.y, radi, p);
                canvas.drawCircle(p2.x, p2.y, radi, p);
                canvas.drawCircle(p3.x, p3.y, radi, p);
                canvas.drawCircle(p4.x, p4.y, radi, p);
                if (pic == null) {
                    pic = new PointF();
                    pic = p4;
                    price = x4;
                }
                break;
            case 5:
                drawLine(canvas, p);
                canvas.drawCircle(p1.x, p1.y, radi, p);
                canvas.drawCircle(p2.x, p2.y, radi, p);
                canvas.drawCircle(p3.x, p3.y, radi, p);
                canvas.drawCircle(p4.x, p4.y, radi, p);
                canvas.drawCircle(p5.x, p5.y, radi, p);
                if (pic == null) {
                    pic = new PointF();
                    pic = p5;
                    price = x5;
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** ��Բ�Ǿ��� */
        if (pic != null) {
            // p = new Paint();
            p.setAntiAlias(true);// ���û��ʵľ��Ч��
            p.setPathEffect(new PathEffect());
            p.setColor(getResources().getColor(R.color.green_chart_dark));
            textWidth = getTextWidth(p, String.format("%.2f", price));
            left = pic.x - textWidth / 2;
            top = pic.y - 35 * multi;
            right = pic.x + textWidth / 2 + 5;
            bottom = pic.y - 15 * multi;
            RectF oval3 = new RectF(left, top, right, bottom);// ���ø��µĳ�����
            canvas.drawRoundRect(oval3, 10, 10, p);// �ڶ���������x�뾶��������������y�뾶
            path.moveTo(pic.x, pic.y - 5 * multi);
            path.lineTo((left + right) / 2 - 5 * multi, bottom);
            path.lineTo((left + right) / 2 + 5 * multi, bottom);
            path.lineTo(pic.x, pic.y - 5 * multi);
            canvas.drawPath(path, p);
            p.setColor(Color.WHITE);
            p.setTextSize(14 * multi);
            canvas.drawText(String.format("%.2f", price), pic.x - textWidth / 2 + 5, pic.y - 20 * multi, p);
        }

        if (hasAnim) {
            handler.sendEmptyMessageDelayed(0, miles);
        }
    }

    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
        case MotionEvent.ACTION_MOVE:
            reFreshPic(event);
            break;
        case MotionEvent.ACTION_DOWN:
            reFreshPic(event);
            break;
        case MotionEvent.ACTION_UP:
            reFreshPic(event);
            break;
        }

        return super.onTouchEvent(event);
    }

    private void reFreshPic(MotionEvent event) {
        float x = event.getX();
        int[] location = new int[2];
        this.getLocationOnScreen(location);
        float offsetX = x - widthText - widthHead;
        if (offsetX < widthReal / 8) {
            pic = p1;
            price = x1;
        } else if (offsetX > widthReal / 8 && offsetX < widthReal * 3 / 8) {
            pic = p2;
            price = x2;
        } else if (offsetX > widthReal * 3 / 8 && offsetX < widthReal * 5 / 8) {
            pic = p3;
            price = x3;
        } else if (offsetX > widthReal * 5 / 8 && offsetX < widthReal * 7 / 8) {
            pic = p4;
            price = x4;
        } else if (offsetX > widthReal * 7 / 8) {
            pic = p5;
            price = x5;
        }
        this.invalidate();
    }

    /**
     * ���ݴ���ļ۸�õ���Ҫչʾ��view�е�Y����
     * 
     * max�����ֵ min:��Сֵ height��ͼ��ĸ߶�
     * 
     * @param x
     * @return
     */
    public double getHeight(double x) {
        if (max - min == 0) {
            return 0;
        } else {
            return (max - x) / (max - min) * heightReal;
        }
    }

    public void drawLine(Canvas canvas, Paint p) {
        switch (mChartBeanList.size()) {
        case 0:
            break;
        case 1:
            break;
        case 2:
            if (hasAnim) {
                if (x1Complete) {
                    canvas.drawLine(p1.x, p1.y, p2x, p2y, p);
                }
            } else {
                canvas.drawLine(p1.x, p1.y, p2.x, p2.y, p);
            }
            break;
        case 3:
            if (hasAnim) {
                if (x1Complete) {
                    canvas.drawLine(p1.x, p1.y, p2x, p2y, p);
                }
                if (x2Complete) {
                    canvas.drawLine(p2x, p2y, p3x, p3y, p);
                }
            } else {
                canvas.drawLine(p1.x, p1.y, p2.x, p2.y, p);
                canvas.drawLine(p2.x, p2.y, p3.x, p3.y, p);
            }
            break;
        case 4:
            if (hasAnim) {
                if (x1Complete) {
                    canvas.drawLine(p1.x, p1.y, p2x, p2y, p);
                }
                if (x2Complete) {
                    canvas.drawLine(p2x, p2y, p3x, p3y, p);
                }
                if (x3Complete) {
                    canvas.drawLine(p3x, p3y, p4x, p4y, p);
                }
            } else {
                canvas.drawLine(p1.x, p1.y, p2.x, p2.y, p);
                canvas.drawLine(p2.x, p2.y, p3.x, p3.y, p);
                canvas.drawLine(p3.x, p3.y, p4.x, p4.y, p);
            }
            break;
        case 5:
            if (hasAnim) {
                if (x1Complete) {
                    canvas.drawLine(p1.x, p1.y, p2x, p2y, p);
                }
                if (x2Complete) {
                    canvas.drawLine(p2x, p2y, p3x, p3y, p);
                }
                if (x3Complete) {
                    canvas.drawLine(p3x, p3y, p4x, p4y, p);
                }
                if (x4Complete) {
                    canvas.drawLine(p4x, p4y, p5x, p5y, p);
                }
            } else {
                canvas.drawLine(p1.x, p1.y, p2.x, p2.y, p);
                canvas.drawLine(p2.x, p2.y, p3.x, p3.y, p);
                canvas.drawLine(p3.x, p3.y, p4.x, p4.y, p);
                canvas.drawLine(p4.x, p4.y, p5.x, p5.y, p);
            }
            break;
        }
    }

    class MyHandler extends Handler {
        public MyHandler() {
        }

        public MyHandler(Looper L) {
            super(L);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (p2x < p2.x) {
                p2x = p2x + px;
                p2y = p2y + (p2.y - p1.y) / (p2.x - p1.x) * px;
                // pp2.x++;
                // pp2.y = pp2.y + (p2.y - p1.y) / (p2.x - p1.x) * 1;
                x1Complete = true;
                ChartView.this.invalidate();
                p3x = p2x;
                System.out.println("p2y--" + p2y);
                p3y = p2y;
            } else if (p3x < p3.x) {
                p3x = p3x + px;
                p3y = p3y + (p3.y - p2.y) / (p3.x - p2.x) * px;
                System.out.println("2");
                // pp3.x = pp3.x + 1;
                // pp3.y = pp3.y + (p3.y - p2.y) / (p3.x - p2.x) * 1;
                ChartView.this.invalidate();
                x2Complete = true;
                p4x = p3x;
                p4y = p3y;
            } else if (p4x < p4.x) {
                System.out.println("3");
                p4x = p4x + px;
                p4y = p4y + (p4.y - p3.y) / (p4.x - p3.x) * px;
                // pp4.x = pp4.x + 1;
                // pp4.y = pp4.y + (p4.y - p3.y) / (p4.x - p3.x) * 1;
                ChartView.this.invalidate();
                x3Complete = true;
                p5x = p4x;
                p5y = p4y;
            } else if (p5x < p5.x) {
                p5x = p5x + px;
                System.out.println("4");
                p5y = p5y + (p5.y - p4.y) / (p5.x - p4.x) * px;
                // pp5.x = pp5.x + 1;
                // pp5.y = pp5.y + (p5.y - p4.y) / (p5.x - p4.x) * 1;
                ChartView.this.invalidate();
                x4Complete = true;
            } else {
                System.out.println("return");
                return;
            }
        }
    }
}
