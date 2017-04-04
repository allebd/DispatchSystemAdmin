package com.allebd.dispatchsystemadmin.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allebd.dispatchsystemadmin.DatabaseHelper;
import com.allebd.dispatchsystemadmin.R;

/**
 * Created by CSISPC on 10/03/2017.
 */

public class AdministratorLoginActivity extends AppCompatActivity {
    Context c;
    EditText etAdminUser;
    EditText etAdminPass;
    TextView tvAdminRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        etAdminUser = (EditText) findViewById(R.id.input_adminuser);
        etAdminPass = (EditText) findViewById(R.id.input_adminpassword);
        tvAdminRegister = (TextView) findViewById(R.id.tvAdminRegister);
        final Button btnadminLogin = (Button) findViewById(R.id.btn_adminlogin);


    }
}
