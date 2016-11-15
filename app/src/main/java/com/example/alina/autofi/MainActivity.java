package com.example.alina.autofi;
//=========================================== Imports ============================================//
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
//=========================================== Main Class =========================================//
public class MainActivity extends AppCompatActivity {
    //=========================================== Declarations ===================================//
    public static final int JOB_ID = 1;
    //=========================================== On Create ======================================//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchFi();
        scheduleJob();
    }
    //=========================================== On Resume ======================================//
    @Override
    protected void onResume() {
        super.onResume();
        switchFi();
        scheduleJob();
        this.finishAffinity();
    }
    //=========================================== Switches Off Wifi ==============================//
    public boolean switchFi() {
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
        return false;
    }
    //=========================================== Schedule A Job =================================//
    private void scheduleJob() {
        ComponentName serviceName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, serviceName)
                .setMinimumLatency(3600000)
                .setOverrideDeadline(1800000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(jobInfo);
        if (result == JobScheduler.RESULT_SUCCESS) {
            Toast.makeText(this, "Wifi turned off successfully!", Toast.LENGTH_LONG).show();
        }
    }
}
