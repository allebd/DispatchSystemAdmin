package com.allebd.dispatchsystemadmin.ui.auth;

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
import android.widget.Toast;

import com.allebd.dispatchsystemadmin.DispatchAdminApp;
import com.allebd.dispatchsystemadmin.R;
import com.allebd.dispatchsystemadmin.data.DataManager;
import com.allebd.dispatchsystemadmin.data.models.Hospital;
import com.allebd.dispatchsystemadmin.ui.request.DashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {

    @Inject
    public DataManager.Operations dataManager;
    private Button btnSignAdmin, btnAdminRegister;
    private EditText adminPasswordEditText;
    private EditText adminNameEditText;
    private EditText adminAddressEditText;
    private EditText adminPhoneEditText;
    private EditText adminEmailEditText;
    private FirebaseAuth auth;
    private Hospital hospital;
    private String email, password, name, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ((DispatchAdminApp) getApplication()).getAppComponent().inject(this);

        auth = FirebaseAuth.getInstance();
        adminPasswordEditText = (EditText) findViewById(R.id.reg_adminpassword);
        adminNameEditText = (EditText) findViewById(R.id.reg_adminname);
        adminAddressEditText = (EditText) findViewById(R.id.reg_adminaddress);
        adminPhoneEditText = (EditText) findViewById(R.id.reg_adminphone);
        adminEmailEditText = (EditText) findViewById(R.id.reg_adminemail);
        btnSignAdmin = (Button) findViewById(R.id.btnSignAdmin);
        btnAdminRegister = (Button) findViewById(R.id.btn_adminregister);
        hospital = new Hospital();
        btnSignAdmin.setOnClickListener(this);
        btnAdminRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_adminregister:
                register();
                break;
            case R.id.btnSignAdmin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void register() {
        email = adminEmailEditText.getText().toString().trim();
        password = adminPasswordEditText.getText().toString().trim();
        name = adminNameEditText.getText().toString().trim();
        phone = adminPhoneEditText.getText().toString().trim();
        address = adminAddressEditText.getText().toString().trim();

        if (!validateFormFields()) return;
        updateHospitalObject();
        enableButtons(false);
        auth.createUserWithEmailAndPassword(email, password);
    }

    private void updateHospitalObject() {
        hospital.setName(name);
        hospital.setNumber(phone);
        hospital.setAddress(address);
        hospital.setAmbulanceNumber(0L);
    }

    private void enableButtons(boolean state) {
        btnAdminRegister.setEnabled(state);
        btnSignAdmin.setEnabled(state);
    }

    private boolean validateFormFields() {
        boolean state = true;
        if (TextUtils.isEmpty(name)) {
            Snackbar.make(findViewById(R.id.activity_register), "Enter Hospital Name!", Snackbar.LENGTH_SHORT).show();
            adminNameEditText.setError("Enter Hospital Name!");
            state = false;
        } else adminNameEditText.setError(null);

        if (TextUtils.isEmpty(address)) {
            Snackbar.make(findViewById(R.id.activity_register), "Enter Hospital Address!", Snackbar.LENGTH_SHORT).show();
            adminAddressEditText.setError("Enter Hospital Address!");
            state = false;
        } else adminAddressEditText.setError(null);

        if (TextUtils.isEmpty(phone)) {
            Snackbar.make(findViewById(R.id.activity_register), "Enter phone number!", Snackbar.LENGTH_SHORT).show();
            adminPhoneEditText.setError("Enter phone number!");
            state = false;
        } else adminPhoneEditText.setError(null);

        if (TextUtils.isEmpty(email)) {
            Snackbar.make(findViewById(R.id.activity_register), "Enter email address!", Snackbar.LENGTH_SHORT).show();
            adminEmailEditText.setError("Enter email address!");
            state = false;
        } else adminEmailEditText.setError(null);

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(findViewById(R.id.activity_register), "Enter a valid email address!", Snackbar.LENGTH_SHORT).show();
            adminEmailEditText.setError("Enter a valid email address!");
            state = false;
        } else adminEmailEditText.setError(null);

        if (TextUtils.isEmpty(password)) {
            Snackbar.make(findViewById(R.id.activity_register), "Enter password", Snackbar.LENGTH_SHORT).show();
            adminPasswordEditText.setError("Enter password");
            state = false;
        } else adminPasswordEditText.setError(null);

        if (password.length() < 5) {
            Snackbar.make(findViewById(R.id.activity_register), "Password too short, enter minimum 5 characters!", Snackbar.LENGTH_SHORT).show();
            adminPasswordEditText.setError("Password too short");
            state = false;
        } else adminPasswordEditText.setError(null);

        return state;
    }


    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        enableButtons(true);
        if (!task.isSuccessful()) {
            Toast.makeText(this, "There was an error registering you", Toast.LENGTH_SHORT).show();
            return;
        }
        String uid = task.getResult().getUser().getUid();
        dataManager.storeUserInfo(hospital, uid);
        startActivity(new Intent(this, DashboardActivity.class));
    }
}