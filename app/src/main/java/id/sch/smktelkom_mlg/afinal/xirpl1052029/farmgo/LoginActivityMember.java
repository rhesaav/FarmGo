package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Xratch on 5/2/2018.
 */

public class LoginActivityMember extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private EditText pass;
    private EditText username;
    private ImageView imageView;
    private TextView signup;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_member);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), SignupActivityMember.class));
        }

        login = findViewById(R.id.login);
        pass = findViewById(R.id.pass);
        username = findViewById(R.id.username);
        imageView = findViewById(R.id.imageView);
        signup = findViewById(R.id.signup);

        progressDialog = new ProgressDialog(this);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    private void userLogin() {
        String password = pass.getText().toString().trim();
        String uname = username.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Harap isi password", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(uname)) {
            Toast.makeText(this, "Harap isi username", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(uname, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), SignupActivityMember.class));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == login) {
            userLogin();
        }
        if (view == signup) {
            startActivity(new Intent(this, SignupActivityMember.class));
        }
    }
}
