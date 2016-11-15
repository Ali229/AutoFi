package com.example.alina.autofi;
//=========================================== Imports ============================================//
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.widget.Toast;
//=========================================== Job Class ==========================================//
public class MyJobService extends JobService {
    //=========================================== Start Job ======================================//
    @Override
    public boolean onStartJob(JobParameters params) {
        // Note: this is preformed on the main thread.
        switchFi();
        return true;
    }
    //=========================================== Stop Job =======================================//
    @Override
    public boolean onStopJob(JobParameters params) {
        // Note: return true to reschedule this job.
        Toast.makeText(this, "Wifi turned back on successfully!", Toast.LENGTH_LONG).show();
        return false;
    }
    //=========================================== Switches On Wifi ===============================//
    public void switchFi() {
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);
    }
}
