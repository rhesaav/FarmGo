package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginMemberActivity extends AppCompatActivity implements View.OnClickListener {


    private Button login;
    private EditText username;
    private EditText password;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_member);

        firebaseAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    private void userLogin() {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "ISI USERNAME BANG", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "ISI PASSWORD BANG", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Loginiin");
        progressDialog.show();

    }

    @Override
    public void onClick(View v) {
        if (v == login) {
            userLogin();
        }
    }
}
