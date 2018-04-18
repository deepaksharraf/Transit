package com.example.deepak.transit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by avinash on 06-04-2018.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserInfo.db";


    public DataBaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table_UserDetails.CREATE_TABLE);
        db.execSQL(Table_SelectedLocationInfo.CREATE_TABLE);
        db.execSQL(Table_StopTable.CREATE_TABLE);
        db.execSQL(Table_FareTable.CREATE_TABLE);
        db.execSQL(Table_Transaction_Log.CREATE_TABLE);
        Log.d("User Detail",""+Table_UserDetails.CREATE_TABLE);
        Log.d("Select Location",""+Table_SelectedLocationInfo.CREATE_TABLE);
        Log.d("Stop Table",""+Table_StopTable.CREATE_TABLE);
        Log.d("Rare Table",""+Table_FareTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public static class Table_UserDetails {
        public static final String TABLE_NAME = "UserDetails";
        public static final String COLUMN_ID = "Id";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_PASSWORD = "Password";

        private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +COLUMN_ID + " TEXT, " +COLUMN_NAME + " TEXT, " + COLUMN_PASSWORD
                + " TEXT);";
    }
    public static class Table_SelectedLocationInfo {
        public static final String TABLE_NAME = "SelectedLocationInfo";
        public static final String COLUMN_CURRENTSHIFT = "Shift";
        public static final String COLUMN_CURRENTSTAGE = "Stage";
        public static final String COLUMN_DEVICEID = "DeviceId";
        public static final String COLUMN_ROUTENUMBER= "Route";
        public static final String COLUMN_MASTERIP= "MasterIP";
        public static final String COLUMN_AFCSIP= "AFCSIP";
        public static final String COLUMN_PORT= "Port";
        public static final String COLUMN_APN= "APN";
        public static final String COLUMN_PTO= "PTO";

        private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +COLUMN_CURRENTSHIFT + " TEXT, " +COLUMN_CURRENTSTAGE + " TEXT, " + COLUMN_DEVICEID + " TEXT," +COLUMN_ROUTENUMBER
                + " TEXT," +COLUMN_MASTERIP+ " TEXT, "+COLUMN_AFCSIP + " TEXT, " +COLUMN_PORT + " TEXT, " +COLUMN_APN + " TEXT, " +COLUMN_PTO + " TEXT);";

    }
    public static class Table_StopTable {
        public static final String TABLE_NAME = "StopTable";
        public static final String COLUMN_ROUTENUMBER= "Route";
        public static final String COLUMN_STOPID = "StopId";
        public static final String COLUMN_STOPNAME = "StopName";
        public static final String COLUMN_SEQUENCENUMBER = "SequenceNumber";

        private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (  " +COLUMN_ROUTENUMBER + " TEXT, " +COLUMN_STOPID + " TEXT, " + COLUMN_STOPNAME + " TEXT," +COLUMN_SEQUENCENUMBER
                + " TEXT);";
    }
    public static class Table_FareTable {
        public static final String TABLE_NAME = "FareTable";
        public static final String COLUMN_ROUTENUMBER= "Route";
        public static final String COLUMN_FROM = "FromStation";
        public static final String COLUMN_TO = "ToStation";
        public static final String COLUMN_FARE = "Fare";

        private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +COLUMN_ROUTENUMBER + " TEXT, " +COLUMN_FROM + " TEXT, " + COLUMN_TO + " TEXT," +COLUMN_FARE
                + " TEXT);";
    }

    public static class Table_Transaction_Log {
        public static final String TABLE_NAME = "TransactionLog";
        public static final String COLUMN_TICKET = "TicketIndex";
        public static final String COLUMN_TICKETNO= "TicketNo";
        public static final String COLUMN_DATE= "Date";
        public static final String COLUMN_TIME= "Time";
        public static final String COLUMN_FROM = "FromStation";
        public static final String COLUMN_TO = "ToStation";
        public static final String COLUMN_TYPE = "Type";
        public static final String COLUMN_TOTALFARE = "TotalFare";
        public static final String COLUMN_SHIFTCODE = "ShiftCode";

        private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +COLUMN_TICKET + " TEXT," +COLUMN_TICKETNO + " TEXT, " +COLUMN_DATE + " TEXT, " +COLUMN_TIME + " TEXT, " +COLUMN_FROM + " TEXT, " + COLUMN_TO + " TEXT," + COLUMN_TYPE + " TEXT," +COLUMN_TOTALFARE
                + " TEXT, " + COLUMN_SHIFTCODE + " TEXT);";
    }
}

