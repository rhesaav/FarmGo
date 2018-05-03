package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivityMember extends AppCompatActivity {

    public static final int RequestSignInCode = 7;
    public FirebaseAuth firebaseAuth;
    public GoogleApiClient googleApiClient;

    DatabaseReference databaseMember;
    com.google.android.gms.common.SignInButton signInButton;
    private EditText eNama, eNo, eUser, ePass;
    private Button Proses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_member);



        eNama = findViewById(R.id.nama_owner);
        eNo = findViewById(R.id.no_telp_member);
        eUser = findViewById(R.id.user_owner);
        ePass = findViewById(R.id.pass_owner);
        Proses = findViewById(R.id.proses_member);
        databaseMember = FirebaseDatabase.getInstance().getReference("Member");

        Proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMember();
            }
        });


        Button signInButton = findViewById(R.id.signInButtonMember);

        firebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(SignupActivityMember.this)
                .enableAutoManage(SignupActivityMember.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserSignInMethod();

            }
        });


    }

    public void UserSignInMethod() {

        Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);

        startActivityForResult(AuthIntent, RequestSignInCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestSignInCode) {

            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (googleSignInResult.isSuccess()) {

                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();

                FirebaseUserAuth(googleSignInAccount);
            }

        }
    }


    public void FirebaseUserAuth(GoogleSignInAccount googleSignInAccount) {

        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);

        Toast.makeText(SignupActivityMember.this, " " + authCredential.getProvider(), Toast.LENGTH_LONG).show();

        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(SignupActivityMember.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> AuthResultTask) {

                        Intent i = new Intent(SignupActivityMember.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                });
    }

    public void addMember() {

        String nama = eNama.getText().toString().trim();
        String no_telp = eNo.getText().toString().trim();
        String user_member = eUser.getText().toString().trim();
        String pass_member = ePass.getText().toString().trim();

        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(this, "Isi Nama", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(no_telp)) {
            Toast.makeText(this, "Isi No Telepon", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(user_member)) {
            Toast.makeText(this, "Isi Username", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass_member)) {
            Toast.makeText(this, "Isi Password", Toast.LENGTH_LONG).show();
        } else {

            String uid = databaseMember.push().getKey();

            Member member = new Member(uid, nama, no_telp, user_member, pass_member);

            databaseMember.child(uid).setValue(member);

            Toast.makeText(this, "Data Telah Terkirim ", Toast.LENGTH_LONG).show();

        }
    }
}

