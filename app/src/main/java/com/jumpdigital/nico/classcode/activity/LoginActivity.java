package com.jumpdigital.nico.classcode.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jumpdigital.nico.classcode.R;
import com.jumpdigital.nico.classcode.manager.DataManager;
import com.jumpdigital.nico.classcode.model.LoginResult;
import com.jumpdigital.nico.classcode.retrofit.Interface;

import com.jumpdigital.nico.classcode.helper.Helper;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText etStudentNumber;

    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etStudentNumber = findViewById(R.id.et_student_number);
        helper = new Helper(this);

        if(helper.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
        findViewById(R.id.btn_sign_in_ripple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLogin();
            }
        });
    }

    public void loadLogin() {
        HashMap<String, String> map = new HashMap<>();
        map.put("studentnum", etStudentNumber.getText().toString());
        DataManager.getmInstance(this).postLogin(map, new Interface.postLoginCallback() {
            @Override
            public void onResponse(boolean isSuccess, LoginResult response, String message) {
                if (isSuccess) {
                    helper.setLogin(true);
                    helper.setStudentName((
                            response.getData().getFirstname() + " "
                            + response.getData().getMiddlename() + " "
                            + response.getData().getLastname()).toString());
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Status: " + message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}