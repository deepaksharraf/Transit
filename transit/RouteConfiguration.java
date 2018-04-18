package com.example.deepak.transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RouteConfiguration extends AppCompatActivity {
    Button proceed;
    EditText shift,route;
    public static String routeCode,shiftNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_configuration);

        route=(EditText) findViewById(R.id.route);
        shift=(EditText) findViewById(R.id.shift);
        proceed=(Button)findViewById(R.id.submitrouteconfig);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                routeCode = route.getText().toString();
                shiftNumber = shift.getText().toString();
                if(routeCode.equals("75A")&&(shiftNumber.equals("1")||shiftNumber.equals("2"))) {
                    SQLController sqlController = new SQLController(RouteConfiguration.this);
                    sqlController.addSelectedLocationInfo(0);
                    Intent intent = new Intent(RouteConfiguration.this, Idle.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(RouteConfiguration.this, "Please Enter Correct Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

