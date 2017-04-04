package com.allebd.dispatchsystemadmin.ui.auth;

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
import com.allebd.dispatchsystemadmin.ui.request.ManageRequestsActivity;

/**
 * Created by CSISPC on 12/03/2017.
 */

public class AdminRegisterActivity extends AppCompatActivity {
    Context c;
    EditText etreg_adminuser;
    EditText etreg_adminpass;
    EditText etreg_adminname;
    EditText etreg_adminphone;
    EditText etreg_adminemail;
    EditText etreg_adminadd;
    TextView tvSignAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosp_reg);

        c = this;
        etreg_adminuser = (EditText) findViewById(R.id.reg_adminuser);
        etreg_adminpass = (EditText) findViewById(R.id.reg_adminpassword);
        etreg_adminname = (EditText) findViewById(R.id.reg_adminname);
        etreg_adminphone = (EditText) findViewById(R.id.reg_adminphone);
        etreg_adminemail = (EditText) findViewById(R.id.reg_adminemail);
        etreg_adminadd = (EditText) findViewById(R.id.reg_adminadd);
        tvSignAdmin = (TextView)findViewById(R.id.tvSignAdmin);
        final Button btnAdminRegister = (Button) findViewById(R.id.btn_adminregister);

        etreg_adminuser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnAdminRegister.setEnabled(!etreg_adminuser.getText().toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnAdminRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etreg_adminuser.getText().toString().isEmpty() && !etreg_adminpass.getText().toString().isEmpty())
                {
                    Intent MainActivityIntent = new Intent(AdminRegisterActivity.this,ManageRequestsActivity.class);
                    startActivity(MainActivityIntent);
                }else{
                    etreg_adminuser.setText("");
                    etreg_adminpass.setText("");
                    toast("Please enter a valid email and a password");
                }
            }
        });

        tvSignAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent(AdminRegisterActivity.this,AdministratorLoginActivity.class);
                startActivity(MainActivityIntent);
            }
        });
    }

    private void toast(String s){
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();}
}