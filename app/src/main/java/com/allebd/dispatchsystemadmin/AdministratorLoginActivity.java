package com.allebd.dispatchsystemadmin;

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

/**
 * Created by CSISPC on 10/03/2017.
 */

public class AdministratorLoginActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Context c;
    EditText etAdminUser;
    EditText etAdminPass;
    TextView tvAdminRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        myDb = new DatabaseHelper(this);

        c = this;
        etAdminUser = (EditText) findViewById(R.id.input_adminuser);
        etAdminPass = (EditText) findViewById(R.id.input_adminpassword);
        tvAdminRegister = (TextView) findViewById(R.id.tvAdminRegister);
        final Button btnadminLogin = (Button) findViewById(R.id.btn_adminlogin);

        etAdminUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnadminLogin.setEnabled(!etAdminUser.getText().toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnadminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etAdminUser.getText().toString().isEmpty() && !etAdminPass.getText().toString().isEmpty())
                {
                    Intent MainActivityIntent = new Intent(AdministratorLoginActivity.this,MainActivity.class);
                    startActivity(MainActivityIntent);
                }else{
                    etAdminUser.setText("");
                    etAdminPass.setText("");
                    toast("Please enter a valid email and a password");
                }
            }
        });

        tvAdminRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent(AdministratorLoginActivity.this,AdminRegisterActivity.class);
                startActivity(MainActivityIntent);
            }
        });
    }

    private void toast(String s){
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();}
}
