package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class SignupActivityOwner extends AppCompatActivity {

    public static final int RequestSignInCode = 7;
    public FirebaseAuth firebaseAuth;
    public GoogleApiClient googleApiClient;
    com.google.android.gms.common.SignInButton signInButton;

    DatabaseReference databaseOwner;
    private EditText rName, rTelp, rUsername, rPassword, rNamaperusahaan, rAlamat, rKodeMember;
    private Button rKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment_owner);

        rKodeMember = findViewById(R.id.valueViewC);
        rName = findViewById(R.id.nama_owner);
        rTelp = findViewById(R.id.no_telp_owner);
        rUsername = findViewById(R.id.user_owner);
        rPassword = findViewById(R.id.pass_owner);
        rNamaperusahaan = findViewById(R.id.namaperusahaan);
        rAlamat = findViewById(R.id.alamat_perusahaan);
        rKirim = findViewById(R.id.proses_owner);
        databaseOwner = FirebaseDatabase.getInstance().getReference("Owner");


        rKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOwner();
            }
        });

        signInButton = findViewById(R.id.signInButtonOwner);
        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(SignupActivityOwner.this)
                .enableAutoManage(SignupActivityOwner.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
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

        Toast.makeText(SignupActivityOwner.this, " " + authCredential.getProvider(), Toast.LENGTH_LONG).show();

        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(SignupActivityOwner.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> AuthResultTask) {

                        Intent i = new Intent(SignupActivityOwner.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                });
    }

    @Override
    public void startActivityFromFragment(@NonNull Fragment fragment, Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityFromFragment(fragment, intent, requestCode, options);
    }

    private void addOwner() {

        String kodemember = rKodeMember.getText().toString().trim();
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
        } else if (TextUtils.isEmpty(kodemember)) {
            Toast.makeText(this, "Isi Kode", Toast.LENGTH_LONG).show();
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

            Owner owner = new Owner(uid, name, telp, username, password, namaperusahaan, alamat, kodemember);

            databaseOwner.child(uid).setValue(owner);

            Toast.makeText(this, "Data Telah Terkirim ", Toast.LENGTH_LONG).show();

        }
    }
}