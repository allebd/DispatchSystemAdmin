package com.allebd.dispatchsystemadmin.ui.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.allebd.dispatchsystemadmin.R;
import com.allebd.dispatchsystemadmin.ui.request.DashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {
    private EditText etAdminUser;
    private EditText etAdminPass;
    private Button btnLogin;
    private ProgressDialog progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        etAdminUser = (EditText) findViewById(R.id.input_adminuser);
        etAdminPass = (EditText) findViewById(R.id.input_adminpassword);
        Button tvAdminRegister = (Button) findViewById(R.id.tvRegister);
        btnLogin = (Button) findViewById(R.id.btn_login);

        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Signing you in");
        btnLogin.setOnClickListener(this);
        tvAdminRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tvRegister:
                switchActivity(RegisterActivity.class);
                break;
        }
    }

    private void login() {
        String emailText = etAdminUser.getText().toString().trim();
        String passwordText = etAdminPass.getText().toString().trim();
        if (!validate(emailText, passwordText)) return;
        progressBar.show();
        btnLogin.setEnabled(false);
        auth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(this, this);
    }

    private boolean validate(String email, String password) {

        boolean state = true;

        if (TextUtils.isEmpty(email)) {
            Snackbar.make(findViewById(R.id.activity_login), "Enter email address!", Snackbar.LENGTH_SHORT).show();
            etAdminUser.setError("Enter email address");
            state = false;
        } else etAdminUser.setError(null);


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(findViewById(R.id.activity_login), "Enter a valid email address!", Snackbar.LENGTH_SHORT).show();
            etAdminUser.setError("Enter a valid email address!");
            state = false;
        } else etAdminUser.setError(null);


        if (TextUtils.isEmpty(password)) {
            Snackbar.make(findViewById(R.id.activity_login), "Enter password", Snackbar.LENGTH_SHORT).show();
            etAdminPass.setError("Enter password");
            state = false;
        } else etAdminPass.setError(null);


        if (password.length() < 5) {
            Snackbar.make(findViewById(R.id.activity_login), "Password too short", Snackbar.LENGTH_SHORT).show();
            etAdminPass.setError("Password too short");
            state = false;
        } else etAdminPass.setError(null);

        return state;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        progressBar.dismiss();
        if (!task.isSuccessful()) {
            Snackbar.make(findViewById(R.id.activity_login), "Authentication failed", Snackbar.LENGTH_SHORT).show();
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(true);
            switchActivity(DashboardActivity.class);
        }
    }

    private void switchActivity(Class classFile) {
        Intent intent = new Intent(this, classFile);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
