package com.example.alina.autofi;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * Created by AliNa on 11/15/2016.
 */

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        // Note: this is preformed on the main thread.
        switchFi();
        return true;
    }

    // Stopping jobs if our job requires change.

    @Override
    public boolean onStopJob(JobParameters params) {
        // Note: return true to reschedule this job.
        Toast.makeText(this, "Wifi turned back on!", Toast.LENGTH_LONG).show();
        return false;
    }

    public void switchFi(){
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);
    }

}
