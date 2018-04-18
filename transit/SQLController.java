package com.example.deepak.transit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLController extends DataBaseHandler {
    private Context mContext;
    private SQLiteDatabase database;
    private String[] columns = new String[]{Table_UserDetails.COLUMN_NAME, Table_UserDetails.COLUMN_PASSWORD};
    private String[] columns1 = new String[]{Table_SelectedLocationInfo.COLUMN_DEVICEID,Table_SelectedLocationInfo.COLUMN_CURRENTSHIFT,Table_SelectedLocationInfo.COLUMN_CURRENTSTAGE,Table_SelectedLocationInfo.COLUMN_ROUTENUMBER};
    private String[] columns2 = new String[]{Table_StopTable.COLUMN_STOPNAME,Table_StopTable.COLUMN_STOPID};
    private String[] columns3 = new String[]{Table_FareTable.COLUMN_FARE};
    private String[] columns4 = new String[]{Table_Transaction_Log.COLUMN_TOTALFARE};
    static private Integer Flag=0;

    public SQLController(Context c) {
        super(c);
        if(Flag==0) {
            addContact();
            addStop();
            addFare();
            mContext = c;
            Flag=1;
        }
    }

    public void open() throws SQLException {
        database = getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            database.close();
        }
    }
    public long addContact() {
        open();
        long statues = -1;
        String user_name[]=new String[5];
        user_name[0]="AGC17074";user_name[1]="AGC17075";user_name[2]="AGC17076";user_name[3]="1";
        String password[]=new String[5];
        password[0]="435257";password[1]="435258";password[2]="435259";password[3]="1";

        Integer i=0;
        for(i=0;i<4;i++) {
            try {
                ContentValues contentValue = new ContentValues();
                contentValue.put(Table_UserDetails.COLUMN_NAME, user_name[i]);
                contentValue.put(Table_UserDetails.COLUMN_PASSWORD, password[i]);
                statues = database.insert(Table_UserDetails.TABLE_NAME, null, contentValue);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
        close();
        return statues;

    }
    public long addSelectedLocationInfo(Integer master) {
        long statues = -1;
        if(master==1) {
            open();

            try {
                ContentValues contentValue = new ContentValues();
                contentValue.put(Table_SelectedLocationInfo.COLUMN_DEVICEID, MasterConfig.deviceID);
                contentValue.put(Table_SelectedLocationInfo.COLUMN_CURRENTSTAGE, "1");
                contentValue.put(Table_SelectedLocationInfo.COLUMN_MASTERIP, MasterConfig.masterDownloadIP);
                contentValue.put(Table_SelectedLocationInfo.COLUMN_AFCSIP, MasterConfig.operatorIP);
                contentValue.put(Table_SelectedLocationInfo.COLUMN_PORT, MasterConfig.portNo);
                contentValue.put(Table_SelectedLocationInfo.COLUMN_APN, MasterConfig.apnNo);
                contentValue.put(Table_SelectedLocationInfo.COLUMN_PTO, MasterConfig.ptoID);
                Log.d("content value", "" + contentValue);
                statues = database.insert(Table_SelectedLocationInfo.TABLE_NAME, null, contentValue);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
        else if(master==0)
        {
            open();

            try {
                String where="DeviceId=?";
                String [] whereClause=new String[]{MasterConfig.deviceID};
                Log.d("content value", "" + whereClause);
                ContentValues contentValue = new ContentValues();
                contentValue.put(Table_SelectedLocationInfo.COLUMN_ROUTENUMBER, RouteConfiguration.routeCode);
                contentValue.put(Table_SelectedLocationInfo.COLUMN_CURRENTSHIFT, RouteConfiguration.shiftNumber);
                Log.d("content value", "" + contentValue);
                statues = database.update(Table_SelectedLocationInfo.TABLE_NAME,contentValue,where,whereClause);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }

        }
        return statues;
    }

    public long addStop() {
        open();
        long statues = -1;
        String stop_name[]=new String[20];
        stop_name[0]="Kempegowda Bus Station";stop_name[1]="Maharanis College";stop_name[2]="KR Circle";stop_name[3]="St Marthas Hospital";stop_name[4]="Corporation (Towards HAL MG Road)";stop_name[5]="St Joseph Boys High School Malya Hospital";stop_name[6]="Richmond Circle";stop_name[7]="St Joseph College";stop_name[8]="Mayohall";stop_name[9]="Hosmat Hospital";
        stop_name[10]="Military Accounts Office";stop_name[11]="Commando Hospital";stop_name[12]="Domlur (Towards HAL)";stop_name[13]="Domlur Flyover (Towards HAL)";stop_name[14]="Kodihalli";stop_name[15]="NAL Manipal Hospital";stop_name[16]="Murugeshpalya";stop_name[17]="Konena Agrahara";stop_name[18]="HAL Main Gate (Towards Marathahalli Bridge";stop_name[19]="Helicopter Division";
        String route_no="75A";
        Integer stop_id=1001;
        Integer seq_no=1;
        Integer i=0;
        for(i=0;i<20;i++) {
            try {
                ContentValues contentValue = new ContentValues();
                contentValue.put(Table_StopTable.COLUMN_ROUTENUMBER, route_no);
                contentValue.put(Table_StopTable.COLUMN_STOPID, Integer.toString(stop_id + i));
                contentValue.put(Table_StopTable.COLUMN_STOPNAME, stop_name[i]);
                contentValue.put(Table_StopTable.COLUMN_SEQUENCENUMBER, Integer.toString(seq_no + i));
                statues = database.insert(Table_StopTable.TABLE_NAME, null, contentValue);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
        close();
        return statues;
    }
    public long addFare() {
        open();
        long statues = -1;
        String route_no="75A";
        Integer stop_id=1001;
        Integer stop_count=20;
        Integer fare_start=4;
        Integer fare_inc_counter=0;
        Integer i=0,j=0;
        for(i=1;i<stop_count+1;i++) {
            for(j=0;j<(stop_count-i);j++) {
                fare_inc_counter++;
                if(fare_inc_counter==5)
                {
                    fare_inc_counter=1;
                    fare_start=(fare_start+4);
                }
                try {

                    ContentValues contentValue = new ContentValues();
                    contentValue.put(Table_FareTable.COLUMN_ROUTENUMBER, route_no);
                    contentValue.put(Table_FareTable.COLUMN_FROM, Integer.toString(stop_id+i-1));
                    contentValue.put(Table_FareTable.COLUMN_TO,Integer.toString(1000+i+j+1) );
                    contentValue.put(Table_FareTable.COLUMN_FARE, Integer.toString(fare_start));
                    statues = database.insert(Table_FareTable.TABLE_NAME, null, contentValue);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }
            fare_inc_counter=0;
            fare_start=4;
        }
        close();
        return statues;
    }
    public List<SelectedLocationInfo> getAllData(){
        List<SelectedLocationInfo> DetailList =new ArrayList<>();
        SelectedLocationInfo details;
        open();
        try {
            Cursor cursor = database.query(Table_SelectedLocationInfo.TABLE_NAME, columns1, null, null, null, null, null);
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    details = new SelectedLocationInfo();
                    details.setDeviceId(cursor.getString(cursor.getColumnIndex(Table_SelectedLocationInfo.COLUMN_DEVICEID)));
                    details.setShift(cursor.getString(cursor.getColumnIndex(Table_SelectedLocationInfo.COLUMN_CURRENTSHIFT)));
                    details.setStage(cursor.getString(cursor.getColumnIndex(Table_SelectedLocationInfo.COLUMN_CURRENTSTAGE)));
                    details.setRoute(cursor.getString(cursor.getColumnIndex(Table_SelectedLocationInfo.COLUMN_ROUTENUMBER)));
                    DetailList.add(details);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return DetailList;
    }


    public List<SelectBusStop> getStop(){
        List<SelectBusStop> DetailList = new ArrayList<>();
        SelectBusStop details;
        open();
        try{
            Cursor cursor =database.query(Table_StopTable.TABLE_NAME,columns2,null,null,null,null,null);
            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                do{
                    details=new SelectBusStop();
                    details.setStopName(cursor.getString(cursor.getColumnIndex(Table_StopTable.COLUMN_STOPNAME)));
                    details.setStopId(cursor.getString(cursor.getColumnIndex(Table_StopTable.COLUMN_STOPID)));
                    DetailList.add(details);

                }while (cursor.moveToNext());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return DetailList;
    }

    public List<SelectFare> getFare(String from,String to) {
        List<SelectFare> DetailList = new ArrayList<>();
        SelectFare details;
        String whereClause = "FromStation=? And ToStation=?";
        String[] whereArgs = new String[]{from, to};
        open();
        try {
            Cursor cursor =database.query(Table_FareTable.TABLE_NAME,columns3,whereClause,whereArgs,null,null,null);
            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                do {
                    details = new SelectFare();
                    details.setFare(cursor.getString(cursor.getColumnIndex(Table_FareTable.COLUMN_FARE)));
                    DetailList.add(details);

                } while (cursor.moveToNext());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return DetailList;
    }


    public boolean validateLoginUser(String userName, String password) {

        open();

        Cursor cursor = null;
        boolean statues = false;

        try {

            String query = "SELECT * FROM " + Table_UserDetails.TABLE_NAME + " WHERE "
                    + Table_UserDetails.COLUMN_NAME + " = '" + userName +"' AND "
                    + Table_UserDetails.COLUMN_PASSWORD + " = '" + password + "';" ;

            cursor = database.rawQuery(query, null);


            statues = cursor.getCount() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            close();
        }
        return statues;
    }

    public List<String> updateTransaction(CharSequence date,String time,String from,String to ,String adultfare ,String childfare ,String adultcount ,String childcount,String shiftcode )
    {
        List<TicketDetails> DetailList = new ArrayList<>();
        List<String>TicketIndex1=new ArrayList<>();
        List<String>TicketIndex2=new ArrayList<>();
        String  Index="100";
        Integer ticketCount=0;
        DetailList=getLastOccurence();
        for (  TicketDetails details: DetailList) {
            String LocalIndex;
            LocalIndex = details.getTicketIndex();
            if(LocalIndex.equals(null))
                break;
            else
                Index=LocalIndex;
            Log.d("Index",""+Index);

        }

        Integer Ticketflag=Integer.parseInt(Index);
        Ticketflag++;
        open();
        TicketIndex1.add(Ticketflag.toString());
        TicketIndex2.add(Ticketflag.toString());
        try {
            ContentValues contentValue = new ContentValues();
            for(int i=0;i<Integer.parseInt(adultcount);i++) {

                contentValue.put(Table_Transaction_Log.COLUMN_TICKET,""+Ticketflag);
                contentValue.put(Table_Transaction_Log.COLUMN_TICKETNO, "81010010"+Integer.toString(Ticketflag));
                contentValue.put(Table_Transaction_Log.COLUMN_DATE, date.toString());
                contentValue.put(Table_Transaction_Log.COLUMN_TIME, time);
                contentValue.put(Table_Transaction_Log.COLUMN_FROM, from);
                contentValue.put(Table_Transaction_Log.COLUMN_TO, to);
                contentValue.put(Table_Transaction_Log.COLUMN_TYPE, "Adult");
                contentValue.put(Table_Transaction_Log.COLUMN_TOTALFARE,adultfare);
                contentValue.put(Table_Transaction_Log.COLUMN_SHIFTCODE,shiftcode);
                Log.d("Content transaction",""+contentValue);
                database.insert(Table_Transaction_Log.TABLE_NAME, null, contentValue);
                Log.d("Adultfaredetails",""+contentValue);
                ticketCount++;

                Ticketflag++;
            }
            String TetxTo=" TO ";
            TicketIndex2.add(TetxTo);

            for(int i=0;i<Integer.parseInt(childcount);i++) {
                contentValue.put(Table_Transaction_Log.COLUMN_TICKET,""+Ticketflag);
                contentValue.put(Table_Transaction_Log.COLUMN_TICKETNO, "81010010"+Integer.toString(Ticketflag));
                contentValue.put(Table_Transaction_Log.COLUMN_DATE, date.toString());
                contentValue.put(Table_Transaction_Log.COLUMN_TIME, time);
                contentValue.put(Table_Transaction_Log.COLUMN_FROM, from);
                contentValue.put(Table_Transaction_Log.COLUMN_TO, to);
                contentValue.put(Table_Transaction_Log.COLUMN_TYPE, "Child");
                contentValue.put(Table_Transaction_Log.COLUMN_TOTALFARE,childfare);
                database.insert(Table_Transaction_Log.TABLE_NAME, null, contentValue);
                Ticketflag++;
            }
            Integer childticketflag=Ticketflag-1;
            TicketIndex2.add(childticketflag.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        if(ticketCount==1)
        {
            return TicketIndex1;
        }
        else
            return TicketIndex2;
    }


    public List<TicketDetails> getLastOccurence()
    {
        List<TicketDetails> DetailList = new ArrayList<>();
        TicketDetails details;
        open();
        try {

            String query="SELECT " +Table_Transaction_Log.COLUMN_TICKET+ " FROM " +Table_Transaction_Log.TABLE_NAME +" ORDER BY CAST("+Table_Transaction_Log.COLUMN_TICKET+" as INT) DESC LIMIT 1 ;";
            Cursor cursor =database.rawQuery(query,null);
            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                do {
                    details = new TicketDetails();
                    details.setTicketIndex(cursor.getString(cursor.getColumnIndex(Table_Transaction_Log.COLUMN_TICKET)));
                    DetailList.add(details);

                } while (cursor.moveToNext());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return DetailList;
    }

    public List<ShiftDetails> getAdultShiftDetails(String shiftCode){
        List<ShiftDetails> DetailList = new ArrayList<>();
        ShiftDetails details;
        String whereClause = "Type=? And ShiftCode=?";
        String[] whereArgs = new String[]{"Adult",shiftCode};
        open();
        try {
            Cursor cursor =database.query(Table_Transaction_Log.TABLE_NAME,columns4,whereClause,whereArgs,null,null,null);
            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                do {
                    details = new ShiftDetails();
                    details.setTotalAdultFare(cursor.getString(cursor.getColumnIndex(Table_Transaction_Log.COLUMN_TOTALFARE)));
                    DetailList.add(details);
                    Log.d("Adultfaredetails",""+DetailList);

                } while (cursor.moveToNext());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return DetailList;

    }

    public List<ShiftDetails> getChildShiftDetails(String shiftCode){
        List<ShiftDetails> DetailList = new ArrayList<>();
        ShiftDetails details;
        String whereClause = "Type=? And ShiftCode=?";
        String[] whereArgs = new String[]{"Child",shiftCode};
        open();
        try {
            Cursor cursor =database.query(Table_Transaction_Log.TABLE_NAME,columns4,whereClause,whereArgs,null,null,null);
            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                do {
                    details = new ShiftDetails();
                    details.setTotalChildFare(cursor.getString(cursor.getColumnIndex(Table_Transaction_Log.COLUMN_TOTALFARE)));
                    DetailList.add(details);
                    Log.d("Childfaredetails",""+DetailList);

                } while (cursor.moveToNext());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return DetailList;

    }



}



