package com.hnvist.apptest1.ui.fragment;

import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hnvist.apptest1.R;
import com.hnvist.apptest1.pojo.HjzbViewHolder;
import com.hnvist.apptest1.pojo.StateSec;
import com.hnvist.apptest1.service.SQLEnvironmentService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HjzbFragment extends Fragment {
    private HjzbViewHolder airTemperature, airHumidity, light, soilTemperature, soilHumidity, co2;
    private SQLEnvironmentService db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("onCreate", "-----------》启动完成");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("onCreateView", "-----------》启动完成");
        return inflater.inflate(R.layout.fragment_hjzb, container, false);
    }

    @Override
    public void onStart() {
        Log.e("onStart", "-----------》启动完成");
        super.onStart();
        initView();
        initData();
    }


    private void initView() {
        db = new SQLEnvironmentService();

        airTemperature = new HjzbViewHolder();
        airTemperature.setBgClick((LinearLayout) getView().findViewById(R.id.hjzb_air_temperature_click));
        airTemperature.setState((TextView)getView().findViewById(R.id.hjzb_air_temperature_state));
        airTemperature.setNumber((TextView) getView().findViewById(R.id.hjzb_air_temperature_number));

        airHumidity = new HjzbViewHolder();
        airHumidity.setBgClick((LinearLayout) getView().findViewById(R.id.hjzb_air_humidity_click));
        airHumidity.setState((TextView) getView().findViewById(R.id.hjzb_air_humidity_state));
        airHumidity.setNumber((TextView) getView().findViewById(R.id.hjzb_air_humidity_number));

        light = new HjzbViewHolder();
        light.setBgClick((LinearLayout) getView().findViewById(R.id.hjzb_light_click));
        light.setState((TextView) getView().findViewById(R.id.hjzb_light_state));
        light.setNumber((TextView) getView().findViewById(R.id.hjzb_light_number));

        soilTemperature = new HjzbViewHolder();
        soilTemperature.setBgClick((LinearLayout) getView().findViewById(R.id.hjzb_soil_temperature_click));
        soilTemperature.setState((TextView) getView().findViewById(R.id.hjzb_soil_temperature_state));
        soilTemperature.setNumber((TextView) getView().findViewById(R.id.hjzb_soil_temperature_number));

        soilHumidity = new HjzbViewHolder();
        soilHumidity.setBgClick((LinearLayout) getView().findViewById(R.id.hjzb_soil_humidity_click));
        soilHumidity.setState((TextView) getView().findViewById(R.id.hjzb_soil_humidity_state));
        soilHumidity.setNumber((TextView) getView().findViewById(R.id.hjzb_soil_humidity_number));

        co2 = new HjzbViewHolder();
        co2.setBgClick((LinearLayout) getView().findViewById(R.id.hjzb_co2_click));
        co2.setState((TextView) getView().findViewById(R.id.hjzb_co2_state));
        co2.setNumber((TextView) getView().findViewById(R.id.hjzb_co2_number));

    }

    private void initData() {
        new MyThread(airTemperature, 60, "airTemperature").start();
        new MyThread(airHumidity, 69, "airHumidity").start();
        new MyThread(light, 69, "light").start();
        new MyThread(soilTemperature, 69, "soilTemperature").start();
        new MyThread(soilHumidity, 69, "soilHumidity").start();
        new MyThread(co2, 69, "co2").start();
    }

    private int getR(){
        return (int)(Math.random() * 100);
    }

    class MyThread extends Thread{
        private HjzbViewHolder viewHolder;
        private int high;
        private String table;

        public MyThread(HjzbViewHolder viewHolder, int high, String table) {
            this.viewHolder = viewHolder;
            this.high = high;
            this.table = table;
        }

        @Override
        public void run() {
            while (true){
                int a = getR();
                viewHolder.getNumber().post(()->{
                    viewHolder.getNumber().setText(String.valueOf(a));
                    if (a > 0 && a <= high){
                        viewHolder.getBgClick().setBackgroundColor(getResources().getColor(R.color.green));
                        viewHolder.getState().setText(getResources().getString(R.string.normal));
                    } else {
                        viewHolder.getBgClick().setBackgroundColor(getResources().getColor(R.color.red));
                        viewHolder.getState().setText(getResources().getString(R.string.warning));
                    }
                });
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(new Date());
                StateSec stateSec = new StateSec(a, time);
                db.add(table, stateSec);
                //db.list(table);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}