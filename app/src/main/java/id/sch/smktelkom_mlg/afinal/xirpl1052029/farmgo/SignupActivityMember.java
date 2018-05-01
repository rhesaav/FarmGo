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

public class SignupActivityMember extends AppCompatActivity {


    DatabaseReference databaseMember;
    private EditText eNama, eNo, eKode, eUser, ePass;
    private Button Proses;
    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_member);

        eNama = findViewById(R.id.nama);
        eNo = findViewById(R.id.no_telp);
        eKode = findViewById(R.id.kode_perusahaan);
        eUser = findViewById(R.id.user_member);
        ePass = findViewById(R.id.pass_member);
        Proses = findViewById(R.id.daftar_member);
        databaseMember = FirebaseDatabase.getInstance().getReference("Member");

        Proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMember();
            }
        });

    }

    private void addMember() {

        String nama = eNama.getText().toString().trim();
        String no_telp = eNo.getText().toString().trim();
        String kode_perusahaan = eKode.getText().toString().trim();
        String user_member = eUser.getText().toString().trim();
        String pass_member = ePass.getText().toString().trim();

        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(this, "Isi Nama", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(no_telp)) {
            Toast.makeText(this, "Isi No Telepon", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(kode_perusahaan)) {
            Toast.makeText(this, "Isi Kode Perusahaan", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(user_member)) {
            Toast.makeText(this, "Isi Username", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass_member)) {
            Toast.makeText(this, "Isi Password", Toast.LENGTH_LONG).show();
        } else {
            String uid = databaseMember.push().getKey();

            Member member = new Member(uid, nama, no_telp, kode_perusahaan, user_member, pass_member);

            databaseMember.child(uid).setValue(member);

            Toast.makeText(this, "Data Telah Terkirim ", Toast.LENGTH_LONG).show();

        }
    }

}

