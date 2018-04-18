package com.example.deepak.transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText UserId,UserPass;
    Button btnLogin,btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLController sqlController = new SQLController(Login.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
        UserId=(EditText)findViewById(R.id.et_Login_username);
        UserPass=(EditText)findViewById(R.id.et_Login_password);
        btnLogin=(Button) findViewById(R.id.btn_login);

        String un= Preferences.getLoginUserName(this);
        String pw= Preferences.getLoginPassword(this);

        if(un==null&&pw==null){
            Toast.makeText(this, "Please Fill the Details", Toast.LENGTH_SHORT).show();

        }
        else
        if(!TextUtils.isEmpty(un) && !TextUtils.isEmpty(pw)) {
            UserId.setText(un);
            UserPass.setText(pw);
            Toast.makeText(this, "Your Details are Found in our DataBase", Toast.LENGTH_SHORT).show();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLController sqlController = new SQLController(Login.this);
                boolean statues = sqlController.validateLoginUser(UserId.getText().toString(), UserPass.getText().toString());
                if (statues) {
                    Toast.makeText(Login.this, "Successful Login", Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(Login.this, RouteConfiguration.class);
                    startActivity(i1);
                }
                else {
                    Toast.makeText(Login.this, "Your Details are not found in our DataBase Please Verify", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
