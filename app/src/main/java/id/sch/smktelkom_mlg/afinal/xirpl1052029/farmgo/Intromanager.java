package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by ASUS on 4/28/2018.
 */

public class Intromanager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public Intromanager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("first", 0);
        editor = pref.edit();
    }

    public void setFirst(Boolean isFirst) {
        editor.putBoolean("check", isFirst);
        editor.commit();
    }

    public boolean Check() {
        return pref.getBoolean("check", true);
    }
}
