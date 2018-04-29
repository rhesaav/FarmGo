package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by haniivn on 4/29/2018.
 */

public class InputData extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseCabang1;
    private EditText eUmur, eJayam, eJmati, eJtelur, eBtelur, eBmakan;
    private Button bttnKirim;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        databaseCabang1 = database.getReference("cabang1").child("kandang1");
        eUmur = findViewById(R.id.umur);
        eJayam = findViewById(R.id.jayam);
        eJmati = findViewById(R.id.jmati);
        eJtelur = findViewById(R.id.jtelur);
        eBtelur = findViewById(R.id.btelur);
        eBmakan = findViewById(R.id.bmakan);
        bttnKirim = findViewById(R.id.kirim);


        bttnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(eUmur.getText().toString()) && !isEmpty(eJayam.getText().toString()) && !isEmpty(eJmati.getText().toString())
                        && !isEmpty(eJtelur.getText().toString()) && !isEmpty(eBtelur.getText().toString()) && !isEmpty(eBmakan.getText().toString()))
                    ;
                else
                    Snackbar.make(findViewById(R.id.kirim), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        eUmur.getWindowToken(), 0);
            }
        });

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    //    private void Kirim(Barang barang) {
//        /**
//         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
//         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
//         * ketika data berhasil ditambahkan
//         */
//        database.child("barang").push().setValue(barang).addOnSuccessListener(this, new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                etNama.setText("");
//                etMerk.setText("");
//                etHarga.setText("");
//                Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    public static Intent getActIntent(Activity activity) {
//        // kode untuk pengambilan Intent
//        return new Intent(activity, FirebaseDBCreateActivity.class);
//    }
    public void loadData() {
        data = new Data();
        databaseCabang1 = FirebaseDatabase.getInstance().getReference("cabang1").child("kandang1");
        databaseCabang1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Data data = snapshot.getValue(Data.class);
                }
                eUmur.setText(data.getUmur());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
