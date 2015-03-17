package com.example.simple;

import java.util.Random;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ChartViewAct  extends ActionBarActivity implements OnClickListener {
    private RelativeLayout ll_full;
    private ChartView cv_chart;
    private Button bt_change;
    private Random random;
    private int x1;
    private int x2;
    private int x3;
    private int x4;
    private int x5;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ll_full = (RelativeLayout) findViewById(R.id.ll_full);
        cv_chart = (ChartView) findViewById(R.id.cv_chart);
        bt_change = (Button) findViewById(R.id.bt_change);
        bt_change.setOnClickListener(this);
        random = new Random();

        cv_chart.setTime("13.12", "14.01", "14.02", "14.03", "14.04");
        cv_chart.setNum(220, 152, 113, 156, 250);
        cv_chart.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
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
                /** 修改第一列的数据 */
                x1 = judgeHeight(offsetY);
            } else if (offsetX < 50 + ChartView.width * 3 / 4 / 2) {
                /** 修改第二列的数据 */
                x2 = judgeHeight(offsetY);
            } else if (offsetX < 50 + ChartView.width * 5 / 4 / 2) {
                /** 修改第三列的数据 */
                x3 = judgeHeight(offsetY);
            } else if (offsetX < 50 + ChartView.width * 7 / 4 / 2) {
                /** 修改第四列的数据 */
                x4 = judgeHeight(offsetY);
            } else {
                /** 修改第五列的数据 */
                x5 = judgeHeight(offsetY);
            }
            cv_chart.setNum(x1, x2, x3, x4, x5);
            cv_chart.invalidate();
            break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 根据得到的高度判断
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
        switch (v.getId()) {
        case R.id.bt_change:
            x1 = random.nextInt(300);
            x2 = random.nextInt(300);
            x3 = random.nextInt(300);
            x4 = random.nextInt(300);
            x5 = random.nextInt(300);
            cv_chart.setNum(x1, x2, x3, x4, x5);
            cv_chart.invalidate();
            break;
        }
    }
}
