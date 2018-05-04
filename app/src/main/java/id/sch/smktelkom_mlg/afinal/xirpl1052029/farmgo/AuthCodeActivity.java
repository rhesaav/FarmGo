package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ASUS on 5/3/2018.
 */

public class AuthCodeActivity extends AppCompatActivity {
    private TextView mValueView;
    private TextView mValueViewC;
    private Firebase mRef;
    private ArrayList<String> mCode = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_register);

        mValueView = findViewById(R.id.valueView);
        mValueViewC = findViewById(R.id.valueViewC);
        getCode();

    }

    private void getCode() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference("MemberCode");
        dbRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    try {
                        JSONObject jsonObject = new JSONObject(dataSnapshot1.getValue().toString());
                        mCode.add(jsonObject.getString("code"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                getAcak();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getAcak() {
        Random random = new Random();
        int acak = random.nextInt(mCode.size());
        mValueView.setText(mCode.get(acak));
    }
}
