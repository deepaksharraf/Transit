package com.example.deepak.transit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*import com.imagpay.Settings;
import com.imagpay.mpos.MposHandler;
import com.imagpay.qpboc.ApplePayHandler;*/

public class Setup extends AppCompatActivity {
    Button setup;
    TextView first,second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        first = (TextView)findViewById(R.id.textView1);
        first.setText("Welcome To Transit ");

        second = (TextView)findViewById(R.id.textView);
        second.setText("SETUP REQUIRED");

        setup=(Button) findViewById(R.id.btn_Confirm);
        setup.setText("SETUP");

        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setup.this,MasterConfig.class);
                startActivity(intent);
            }
        });
    }

}
