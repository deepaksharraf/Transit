package com.example.deepak.transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    Button closeStation,shiftReport,logOut;
    static Integer closeCount=1;
    String shiftCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent=getIntent();
        shiftCode=intent.getStringExtra("shiftCode");

        closeStation=findViewById(R.id.btn_closestop);
        shiftReport=findViewById(R.id.btn_shiftReport);
        logOut=findViewById(R.id.btn_logOut);

        closeStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Selection selection=new Selection();
                Integer stationCount=selection.setCloseStation(closeCount);
                Toast.makeText(Menu.this, "Station Closed", Toast.LENGTH_SHORT).show();
                closeCount++;
                if(closeCount==stationCount)
                {    Toast.makeText(Menu.this, "Last Stop Reached", Toast.LENGTH_SHORT).show();
                    closeCount=0;
                }
            }
        });

        shiftReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,ShiftReport.class);
                intent.putExtra("shiftCode",shiftCode);
                startActivity(intent);

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,Login.class);
                startActivity(intent);
            }
        });

    }

}

