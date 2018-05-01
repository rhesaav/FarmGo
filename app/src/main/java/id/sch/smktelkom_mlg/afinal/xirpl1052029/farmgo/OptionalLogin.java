package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ASUS on 4/30/2018.
 */

public class OptionalLogin extends AppCompatActivity {

    public void goToLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToDaftar(View view) {
        Intent intent = new Intent(this, TabActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optional_login);
    }

}

