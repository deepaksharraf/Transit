package com.example.deepak.transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*import com.imagpay.Settings;
import com.imagpay.mpos.MposHandler;*/

import java.util.Date;
import java.util.List;



public class Ticket extends AppCompatActivity {

    private TextView  ticket_number,date,time,from,to,total_fare,adult,child;
    private Button Sms,home;
    private StringBuilder ticketNo=new StringBuilder();
    String currentDateTimeString;
    CharSequence current_date;
    String from_station,to_station,fare_total,adultfare,adultcount,childcount,childfare,shiftcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        from = (TextView)findViewById(R.id.fromResult);
        to = (TextView)findViewById(R.id.toResult);
        total_fare=(TextView)findViewById(R.id.totalResult);
        home=(Button)findViewById(R.id.home);
        Sms=(Button)findViewById(R.id.sms);
        adult=(TextView)findViewById(R.id.adultcountResult);
        child=(TextView)findViewById(R.id.childcountResult);

        Date d = new Date();

         current_date  = DateFormat.format("MMMM d, yyyy ", d.getTime());
         currentDateTimeString = DateFormat.getTimeFormat(this).format(new Date());

        Intent intent=getIntent();
         from_station=intent.getStringExtra("from");
         to_station=intent.getStringExtra("to");
         fare_total=intent.getStringExtra("totalfare");
         adultcount=intent.getStringExtra("adultcount");
         childcount=intent.getStringExtra("childcount");
         adultfare=intent.getStringExtra("fareAdult");
         childfare=intent.getStringExtra("fareChild");
         shiftcode=intent.getStringExtra("shiftCode");


         from.setText(from_station);
         to.setText(to_station);
         total_fare.setText(fare_total);
         adult.setText(adultcount);
         child.setText(childcount);



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Ticket.this,Idle.class);
                startActivity(intent);

            }
        });
        Sms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              /*  print();*/


                SQLController sqlController = new SQLController(Ticket.this);
                List<String > detailList = sqlController.updateTransaction(current_date,currentDateTimeString,from_station,to_station,adultfare,childfare,adultcount,childcount,shiftcode);
                StringBuilder ticketNoDetails=new StringBuilder();
                for (String id : detailList){

                    ticketNoDetails.append(id);

                }
                ticketNo=ticketNoDetails;
                ticket_number.setText(ticketNo);
                for(int i=0;i<Integer.parseInt(adultcount);i++){
                    String context = "TRAVEL TICKET";
                    //printTicket(adultfare,context);
                }
                for(int i=0;i<Integer.parseInt(childcount);i++){
                    String context = "CHILD TICKET";
                 //   printTicket(childfare,context);
                }
            }
        });
    }
  /*  void printTicket(String fare,String context)
    {
        PrinterManager printer = new PrinterManager();

        String context2 = "HAPPY JOURNEY";

        printer.open();
        printer.setupPage(384,-1);

        printer.drawTextEx(context, 102, 7,180,42, "arial", 34, 0, 0, 0);
        printer.drawLine(0, 51, 384, 51, 8);
        printer.drawTextEx(current_date.toString(), 8, 58,126,31, "arial", 24, 0, 0, 0);
        printer.drawTextEx(currentDateTimeString, 268, 58,108,31, "arial", 24, 0, 0, 0);
        printer.drawLine(0, 89, 384, 89, 8);
        printer.drawTextEx(ticketNo.toString(), 8, 96,-1,31, "arial", 24, 0, 0, 0);
        printer.drawTextEx(from_station, 8, 134,-1,31, "arial", 24, 0, 0, 0);
        printer.drawTextEx(to_station, 8, 172,-1,31, "arial", 24, 0, 0, 0);
        printer.drawTextEx(fare, 8, 210,-1,31, "arial", 24, 0, 0, 0);
        printer.drawLine(0, 251, 384, 251, 8);
        printer.drawTextEx(context, 100, 260,-1,31, "arial", 34, 0, 0, 0);

        int ret=printer.printPage(0);
        Log.i("debug", "ret:" + ret);
    }
*/
    @Override
    public void onBackPressed() {
    }

}
