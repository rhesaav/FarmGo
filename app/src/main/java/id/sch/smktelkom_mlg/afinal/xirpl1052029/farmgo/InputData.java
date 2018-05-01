package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by haniivn on 4/29/2018.
 */

public class InputData extends AppCompatActivity {

    DatabaseReference databaseCabang1;
    private EditText eUmur, eJayam, eJmati, eJtelur, eBtelur, eBmakan, eTanggal;
    private Button bttnKirim;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        eTanggal = findViewById(R.id.etanggal);
        eUmur = findViewById(R.id.umur);
        eJayam = findViewById(R.id.jayam);
        eJmati = findViewById(R.id.jmati);
        eJtelur = findViewById(R.id.jtelur);
        eBtelur = findViewById(R.id.btelur);
        eBmakan = findViewById(R.id.bpakan);
        bttnKirim = findViewById(R.id.kirim);
        databaseCabang1 = FirebaseDatabase.getInstance().getReference("Cabang1");

        bttnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    private void addData() {

        String etanggal = eTanggal.getText().toString().trim();
        String umur = eUmur.getText().toString().trim();
        String jayam = eJayam.getText().toString().trim();
        String jtelur = eJtelur.getText().toString().trim();
        String jmati = eJmati.getText().toString().trim();
        String btelur = eBtelur.getText().toString().trim();
        String bmakan = eBmakan.getText().toString().trim();

        if (TextUtils.isEmpty(etanggal)) {
            Toast.makeText(this, "Isi Tanggal", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(umur)) {
            Toast.makeText(this, "Isi Umur", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(jayam)) {
            Toast.makeText(this, "Isi Jumlah Ayam", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(jtelur)) {
            Toast.makeText(this, "Isi Jumlah Telur", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(jmati)) {
            Toast.makeText(this, "Isi Ayam Mati", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(btelur)) {
            Toast.makeText(this, "Isi Berat Telur", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(bmakan)) {
            Toast.makeText(this, "Isi Berat Pakan", Toast.LENGTH_LONG).show();
        } else {
            String uid = databaseCabang1.push().getKey();

            Data data = new Data(uid, etanggal, umur, jayam, jtelur, jmati, btelur, bmakan);

            databaseCabang1.child(uid).setValue(data);

            Toast.makeText(this, "Data Telah Terkirim ", Toast.LENGTH_LONG).show();

        }

    }

}
