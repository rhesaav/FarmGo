package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by ASUS on 5/3/2018.
 */

public class AuthCodeMember extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
