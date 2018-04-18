package com.example.deepak.transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class Idle extends AppCompatActivity {
    Button menuList,ticket;

    TextView employeeID,routeNo,currentShift,currentStage;
    String deviceId,Shift,Route,Stage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SQLController sqlController = new SQLController(Idle.this);
        List<SelectedLocationInfo> detailList=sqlController.getAllData();
        Integer i=0;
        for(SelectedLocationInfo details:detailList){
            deviceId=details.getDeviceId();
            Shift=details.getShift();
           Route=details.getRoute();
           Stage=details.getStage();
            Log.d("shift",""+Shift);
            Log.d("route",""+Route);
            System.out.println("\n");
            i++;
            if(i==1)
            {
                break;
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle);
        menuList = (Button) findViewById(R.id.menu);
        ticket = (Button) findViewById(R.id.tkt);

        employeeID = (TextView) findViewById(R.id.view_12);
        routeNo = (TextView) findViewById(R.id.view_22);
        currentShift = (TextView) findViewById(R.id.view_32);
        currentStage = (TextView) findViewById(R.id.view_42);


        employeeID.setText(deviceId);
        routeNo.setText(Route);
        currentShift.setText(Shift);
        currentStage.setText(Stage);

        menuList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Idle.this,Menu.class);
                intent.putExtra("shiftCode",Shift);

                startActivity(intent);
            }
        });


        ticket.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Idle.this,Selection.class);
                intent.putExtra("shiftCode",Shift);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
    }
}
