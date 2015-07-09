package com.example.simple;

import java.util.ArrayList;
import java.util.Random;

import utils.ChartBean;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.simple.view.ChartView;

public class ChartViewAct extends ActionBarActivity implements OnClickListener {
    private RelativeLayout ll_full;
    private ChartView cv_chart;
    private Button bt_change;
    private Random random;
    private int x1;
    private int x2;
    private int x3;
    private int x4;
    private int x5;
    private ArrayList<ChartBean> list;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ll_full = (RelativeLayout) findViewById(R.id.ll_full);
        cv_chart = (ChartView) findViewById(R.id.cv_chart);
//        bt_change = (Button) findViewById(R.id.bt_change);
//        bt_change.setOnClickListener(this);
        random = new Random();

        list = new ArrayList<ChartBean>();

        for (int i = 0; i < 5; i++) {
            ChartBean chartBean = new ChartBean(15 + (i * 3) % 9, "05.05");
            list.add(chartBean);
        }
        cv_chart.setData(list, "Ԫ/��", true);
        cv_chart.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * �˴�������޸ĸ߶ȵģ�û���õ�
         */
        int action = event.getAction();

        switch (action) {
        case MotionEvent.ACTION_MOVE:
            break;
        case MotionEvent.ACTION_DOWN:
            break;
        case MotionEvent.ACTION_UP:
            float rawX = event.getRawX();
            float rawY = event.getRawY();
            float x = event.getX();
            float y = event.getY();
            System.out.println("X--" + x);
            System.out.println("y" + y);
            int[] location = new int[2];
            cv_chart.getLocationOnScreen(location);
            float offsetY = y - location[1];
            float offsetX = x - 50;
            System.out.println("x--" + location[0]);
            System.out.println("y--" + location[1]);
            System.out.println("offsetX--" + offsetX);
            System.out.println("offsetY--" + offsetY);
            judgeHeight(offsetY);
            if (offsetX < 50 + ChartView.width / 4 / 2) {
                /** �޸ĵ�һ�е����� */
                x1 = judgeHeight(offsetY);
            } else if (offsetX < 50 + ChartView.width * 3 / 4 / 2) {
                /** �޸ĵڶ��е����� */
                x2 = judgeHeight(offsetY);
            } else if (offsetX < 50 + ChartView.width * 5 / 4 / 2) {
                /** �޸ĵ����е����� */
                x3 = judgeHeight(offsetY);
            } else if (offsetX < 50 + ChartView.width * 7 / 4 / 2) {
                /** �޸ĵ����е����� */
                x4 = judgeHeight(offsetY);
            } else {
                /** �޸ĵ����е����� */
                x5 = judgeHeight(offsetY);
            }
            cv_chart.invalidate();
            break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * ���ݵõ��ĸ߶��ж�
     * 
     * @param offsetY
     * @return
     */
    private int judgeHeight(float offsetY) {
        if (offsetY < 0) {
            return 0;
        } else if (offsetY > 0 && offsetY < ChartView.height) {
            return (int) offsetY;
        } else {
            return ChartView.height;
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        
    }

}
