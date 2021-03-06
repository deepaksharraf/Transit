package com.example.deepak.transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MasterDownload extends AppCompatActivity {

    Button setup;
    TextView first,second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        first = (TextView)findViewById(R.id.textView1);
        first.setText("MASTER DOWNLOAD ");

        second = (TextView)findViewById(R.id.textView);
        second.setText("MASTER DOWNLOAD REQUIRED");

        setup=(Button) findViewById(R.id.btn_Confirm);
        setup.setText("PROCEED");

        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLController sqlController = new SQLController(MasterDownload.this);
                sqlController.addSelectedLocationInfo(1);
                Intent intent = new Intent(MasterDownload.this,OperatorDownload.class);
                startActivity(intent);
            }
        });
    }
}

