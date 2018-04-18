package com.example.deepak.transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class ShiftReport extends AppCompatActivity {
    String shiftCode,TotalAdultFare,TotalChildFare;
    Integer finalAdultFare=0,finalChildFare=0,AdultCount,ChildCount,TotalCount,Totalfare;
    TextView adultCount,childCount,adultAmount,childAmount,totalCount,totalAmount,date,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_report);
        adultCount=(TextView)findViewById(R.id.ticketadultcount);
        adultAmount=(TextView)findViewById(R.id.ticketadultamount);
        childCount=(TextView)findViewById(R.id.ticketchildcount);
        childAmount=(TextView)findViewById(R.id.ticketchildamount);
        totalCount=(TextView)findViewById(R.id.totalcount);
        totalAmount=(TextView)findViewById(R.id.totalamount);
        date=(TextView)findViewById(R.id.datevalue);
        time=(TextView)findViewById(R.id.timevalue);

        Date d = new Date();

        CharSequence current_date  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        String currentDateTimeString = DateFormat.getTimeFormat(this).format(new Date());

        Intent intent=getIntent();
        shiftCode=intent.getStringExtra("shiftCode");
        SQLController sqlController = new SQLController(ShiftReport.this);
        List<ShiftDetails> detailList=sqlController. getAdultShiftDetails(shiftCode);
        AdultCount = 0;
        for (ShiftDetails details : detailList) {

            TotalAdultFare=details.getTotalAdultFare();
            finalAdultFare+=Integer.parseInt(TotalAdultFare);
            Log.d("IndiAdult",""+TotalAdultFare);
            Log.d("totalAdult",""+finalAdultFare);
            AdultCount++;
        }

        List<ShiftDetails> detailList2=sqlController. getChildShiftDetails(shiftCode);
        ChildCount = 0;
        for (ShiftDetails details : detailList2) {

            TotalChildFare=details.getTotalChildFare();
            finalChildFare+=Integer.parseInt(TotalChildFare);
            Log.d("Indichild",""+TotalChildFare);
            Log.d("totalchild",""+finalChildFare);
            ChildCount++;
        }

        TotalCount = AdultCount+ChildCount;
        Totalfare = finalAdultFare+finalChildFare;

        adultCount.setText(AdultCount.toString());
        adultAmount.setText(finalAdultFare.toString());
        childCount.setText(ChildCount.toString());
        childAmount.setText(finalChildFare.toString());
        totalCount.setText(TotalCount.toString());
        totalAmount.setText(Totalfare.toString());
        date.setText(current_date);
        time.setText(currentDateTimeString);

    }

    @Override
    public void onBackPressed() {
    }
}
