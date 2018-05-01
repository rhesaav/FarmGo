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

public class SignupActivityOwner extends AppCompatActivity {

    DatabaseReference databaseOwner;
    private EditText rName, rTelp, rUsername, rPassword, rNamaperusahaan, rAlamat;
    private Button rKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_owner);

        rName = findViewById(R.id.nama_owner);
        rTelp = findViewById(R.id.no_telp_owner);
        rUsername = findViewById(R.id.user_owner);
        rPassword = findViewById(R.id.pass_owner);
        rNamaperusahaan = findViewById(R.id.namaperusahaan);
        rKirim = findViewById(R.id.proses_owner);
        databaseOwner = FirebaseDatabase.getInstance().getReference("Owner");

        rKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOwner();
            }
        });

    }

    private void addOwner() {
        String name = rName.getText().toString().trim();
        String telp = rTelp.getText().toString().trim();
        String username = rUsername.getText().toString().trim();
        String password = rPassword.getText().toString().trim();
        String namaperusahaan = rNamaperusahaan.getText().toString().trim();
        String alamat = rAlamat.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Isi Nama", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(telp)) {
            Toast.makeText(this, "Isi No Telepon", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Isi Username", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Isi Password", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(namaperusahaan)) {
            Toast.makeText(this, "Isi Nama Perusahaan", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(alamat)) {
            Toast.makeText(this, "Isi Nama Alamat", Toast.LENGTH_LONG).show();
        } else {
            String uid = databaseOwner.push().getKey();

            Owner owner = new Owner(uid, name, telp, username, password, namaperusahaan, alamat);

            databaseOwner.child(uid).setValue(owner);

            Toast.makeText(this, "Data Telah Terkirim ", Toast.LENGTH_LONG).show();

        }
    }
}