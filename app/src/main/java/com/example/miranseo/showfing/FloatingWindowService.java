package com.example.miranseo.showfing;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;


public class FloatingWindowService extends Service {
    private static final String TAG = "FloatingWindowService";
    private static final boolean DEBUG = true;
    private FloatingWindow mFloatingWindow;

    @Override
    public void onCreate() {
        super.onCreate();

}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int pos = intent.getIntExtra("position", -1);

        mFloatingWindow = new FloatingWindow(this, pos);
        mFloatingWindow.show();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFloatingWindow.hide();
        mFloatingWindow = null;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfiguration) {
        if (DEBUG) Log.i(TAG, "onConfigurationChanged(Configuration)");
        mFloatingWindow.onConfigurationChanged(newConfiguration);
    }
}


