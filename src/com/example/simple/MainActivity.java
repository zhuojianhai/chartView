package com.example.simple;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    private Button bt_cv;
    private Button bt_bcv;
    private Button bt_parabola;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_cv = (Button) findViewById(R.id.bt_cv);
        bt_bcv = (Button) findViewById(R.id.bt_bcv);
        bt_parabola = (Button) findViewById(R.id.bt_parabola);
        bt_cv.setOnClickListener(this);
        bt_bcv.setOnClickListener(this);
        bt_parabola.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.bt_cv:
            Intent i1 = new Intent(MainActivity.this, ChartViewAct.class);
            startActivity(i1);
            break;
        case R.id.bt_bcv:
            Intent i2 = new Intent(MainActivity.this, BarChartViewAct.class);
            startActivity(i2);
            break;
        case R.id.bt_parabola:
            Intent i3 = new Intent(MainActivity.this, ParabolaViewAct.class);
            startActivity(i3);
            break;
        }
    }

}
