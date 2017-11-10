package android.anative.com.mockupapp;

import com.facebook.stetho.Stetho;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class MyApplication extends com.activeandroid.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
