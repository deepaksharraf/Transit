package com.example.deepak.transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MasterConfig extends AppCompatActivity {

    private EditText masterIP,operator,port,apn,pto,device;
    public static String masterDownloadIP,operatorIP,portNo,apnNo,ptoID,deviceID;
    int masterLength = 15, portLength = 4,deviceLength = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_config);

        masterIP = (EditText)findViewById(R.id.masterIp);
        operator = (EditText)findViewById(R.id.operatorIp);
        port = (EditText)findViewById(R.id.portnumber);
        apn = (EditText)findViewById(R.id.apn);
        pto = (EditText)findViewById(R.id.pto);
        device = (EditText)findViewById(R.id.deviceid);

        Button submit = (Button) findViewById(R.id.submitconfig);

        masterIP.setFilters(new InputFilter[] {new InputFilter.LengthFilter(masterLength)});
        operator.setFilters(new InputFilter[] {new InputFilter.LengthFilter(masterLength)});
        port.setFilters(new InputFilter[] {new InputFilter.LengthFilter(portLength)});
        pto.setFilters(new InputFilter[] {new InputFilter.LengthFilter(portLength)});
        device.setFilters(new InputFilter[] {new InputFilter.LengthFilter(deviceLength)});

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                masterDownloadIP = masterIP.getText().toString();
                operatorIP = operator.getText().toString();
                portNo = port.getText().toString();
                apnNo = apn.getText().toString();
                ptoID = pto.getText().toString();
                deviceID = device.getText().toString();

                if (masterDownloadIP.equals("")) {
                    Toast.makeText(MasterConfig.this, "PLease Fill In Details", Toast.LENGTH_SHORT).show();
                } else if (operatorIP.equals("")) {
                    Toast.makeText(MasterConfig.this, "PLease Fill In Details", Toast.LENGTH_SHORT).show();
                } else if (portNo.equals("")) {
                    Toast.makeText(MasterConfig.this, "PLease Fill In Details", Toast.LENGTH_SHORT).show();
                } else if (ptoID.equals("")) {
                    Toast.makeText(MasterConfig.this, "PLease Fill In Details", Toast.LENGTH_SHORT).show();
                } else if(deviceID.equals("")) {
                    Toast.makeText(MasterConfig.this, "PLease Fill In Details", Toast.LENGTH_SHORT).show();
                }

                else if (!Utility.validateIP(masterDownloadIP)) {
                    Toast.makeText(MasterConfig.this, "Enter Valid Master IP", Toast.LENGTH_SHORT).show();
                } else if (!Utility.validateIP(operatorIP)) {
                    Toast.makeText(MasterConfig.this, "Enter Valid Operator IP", Toast.LENGTH_SHORT).show();
                } else if (!Utility.validatePort(portNo)) {
                    Toast.makeText(MasterConfig.this, "Enter Valid Port No", Toast.LENGTH_SHORT).show();
                } else if (!Utility.validatePTO(ptoID)) {
                    Toast.makeText(MasterConfig.this, "Enter Valid PTO ID", Toast.LENGTH_SHORT).show();
                } else if (!Utility.validateDeviceID(deviceID)) {
                    Toast.makeText(MasterConfig.this, "Enter Valid Device ID", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MasterConfig.this, MasterDownload.class);
                    startActivity(intent);
                }
            }
        });
    }
}