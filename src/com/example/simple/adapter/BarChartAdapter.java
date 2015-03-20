package com.example.simple.adapter;

import java.util.Random;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.simple.R;
import com.example.simple.view.BarChartView;

public class BarChartAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    public BarChartAdapter(Context context) {
        super();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_bar_chart, null);
            holder = new ViewHolder();
            /** �õ������ؼ��Ķ��� */
            holder.bcv = (BarChartView) convertView.findViewById(R.id.cv_chart);
            convertView.setTag(holder);// ��ViewHolder����
        } else {
            holder = (ViewHolder) convertView.getTag();// ȡ��ViewHolder����
        }
        holder.bcv.setColor(Math.abs(new Random().nextInt(255)));
        return convertView;
    }

    /** ��ſؼ� */
    public final class ViewHolder {
        public BarChartView bcv;
    }
}
