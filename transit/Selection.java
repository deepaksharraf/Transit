package com.example.deepak.transit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Selection extends AppCompatActivity {

    private Spinner from, to, adult, child;
    private Button confirm, cancel;
    private TextView total, totalfare;
    Integer from_index = 0, to_index = 0, adult_index = 0, child_index = 0, childcount = 0, adultcount = 0, fareAdult = 0, fareChild = 0;
    String[] Stop = new String[20];
    String[] Stop_id = new String[20];
    Integer finalFare, total_count, fareTotal;
    String[] adult_count = new String[]{"0", "1", "2", "3", "4", "5"};
    String[] child_count = new String[]{"0", "1", "2", "3", "4", "5"};
    static Integer closeStation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        to = (Spinner) findViewById(R.id.toSpinner);
        from = (Spinner) findViewById(R.id.fromSpinner);
        adult = (Spinner) findViewById(R.id.adultSpinner);
        child = (Spinner) findViewById(R.id.childSpinner);
        confirm = (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);
        total = (TextView) findViewById(R.id.totalCountResult);
        totalfare = (TextView) findViewById(R.id.totalFareResult);
        adult = (Spinner) findViewById(R.id.adultSpinner);
        child = (Spinner) findViewById(R.id.childSpinner);
        confirm = (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);
        total = (TextView) findViewById(R.id.totalCountResult);
        totalfare = (TextView) findViewById(R.id.totalFareResult);
        setCloseStation(closeStation);
        fromspinner();
        tospinner(from_index);
        calculateIndex(from_index, to_index);
        setAdultSpinner();
        setChildSpinner();
        finalCount();

        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                from_index = from.getSelectedItemPosition();
                tospinner(from_index);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                to_index = to.getSelectedItemPosition();
                calculateIndex(from_index, to_index);
                finalCount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adult_index = adult.getSelectedItemPosition();
                finalCount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                child_index = child.getSelectedItemPosition();
                finalCount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemFrom = from.getSelectedItem().toString();
                String itemTo = to.getSelectedItem().toString();
                Intent intent = new Intent(Selection.this, Ticket.class);
                Intent intentIdle = getIntent();
                String shiftcode = intentIdle.getStringExtra("shiftCode");
                intent.putExtra("from", itemFrom);
                intent.putExtra("to", itemTo);
                intent.putExtra("totalfare", Integer.toString(fareTotal));
                intent.putExtra("childcount", Integer.toString(childcount));
                intent.putExtra("adultcount", Integer.toString(adultcount));
                intent.putExtra("fareAdult", Integer.toString(fareAdult));
                intent.putExtra("fareChild", Integer.toString(fareChild));
                intent.putExtra("shiftCode", shiftcode);
                Log.d("shiftcode", "" + shiftcode);
                if (total_count != 0) {
                    startActivity(intent);
                     /*  Ticket ticket=new Ticket();
                       ticket.func();*/

                } else {
                    Toast.makeText(Selection.this, "Fare Not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Selection.this, Idle.class);
                startActivity(intent);
            }
        });
    }

    void fromspinner() {
        String[] StopFixed = new String[20];
        SQLController sqlController = new SQLController(Selection.this);
        List<SelectBusStop> detailList = sqlController.getStop();
        Integer i = 0;
        ArrayList al = new ArrayList();
        for (SelectBusStop details : detailList) {

            Stop[i] = details.getStopName();
            Stop_id[i] = details.getStopId();
            i++;
            if (i == 20)
                break;
        }
        for (Integer j = 0; j <= closeStation; j++) {
            al.add(Stop[j]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, al);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from.setAdapter(adapter);
        from.setSelection(closeStation);
    }

    void tospinner(Integer from_Index) {
        from_Index++;
        if (from_Index < closeStation) {
            from_Index = closeStation + 1;
        }
        ArrayList al = new ArrayList();
        for (Integer i = from_Index; i < 20; i++) {
            al.add(Stop[i]);

        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, al);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to.setAdapter(adapter2);


    }

    void setAdultSpinner() {
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, adult_count);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adult.setAdapter(adapter2);
        adult.setSelection(1);

    }

    void setChildSpinner() {
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, child_count);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        child.setAdapter(adapter2);

    }

    void calculateIndex(Integer fromIndex, Integer toIndex) {
        Integer fromStopId, toStopId;
        String finalFromStop, finalToStop;

        fromStopId = fromIndex;
        toStopId = toIndex + fromIndex + 1;
        finalFromStop = Stop_id[fromStopId];
        finalToStop = Stop_id[toStopId];
        getFare(finalFromStop, finalToStop);

    }

    void getFare(String finalFromStop, String finalToStop) {
        String Fare;
        SQLController sqlController = new SQLController(Selection.this);
        List<SelectFare> detailList = sqlController.getFare(finalFromStop, finalToStop);
        Integer i = 0;
        for (SelectFare details : detailList) {

            Fare = details.getFare();
            finalFare = Integer.parseInt(Fare);
        }

    }

    void finalCount() {

        childcount = childCount();
        adultcount = adultCount();
        total_count = childcount + adultcount;
        total.setText(Integer.toString(total_count));
        finalFare(adultcount, childcount);
    }

    Integer adultCount() {
        return adult_index;
    }

    Integer childCount() {
        return child_index;
    }

    void finalFare(Integer adult, Integer child) {

        fareAdult = adultFare();
        fareChild = childFare();
        fareTotal = adult * fareAdult + child * fareChild;
        totalfare.setText(Integer.toString(fareTotal));
    }

    Integer adultFare() {
        return finalFare;
    }

    Integer childFare() {
        return finalFare / 2;
    }

    Integer setCloseStation(Integer closeCount) {
        closeStation = closeCount;
        Integer stationCount;
        stationCount = Stop.length;
        return stationCount;
    }

    @Override
    public void onBackPressed() {
    }
}

