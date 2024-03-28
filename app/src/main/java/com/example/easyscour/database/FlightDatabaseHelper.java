package com.example.easyscour.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FlightDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FlightDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FLIGHTS = "flights";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ORIGIN = "origin";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_DEPARTURE_DATE = "departure_date";
    private static final String COLUMN_NUM_PASSENGERS = "num_passengers";

    private static final String COLUMN_FROM_STATE = "from_state";
    private static final String COLUMN_DESTINATION_STATE = "destination_state";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_PASSENGERS = "passengers";

    public FlightDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the flights table if it doesn't exist
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_FLIGHTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ORIGIN + " TEXT," +
                COLUMN_DESTINATION + " TEXT," +
                COLUMN_DEPARTURE_DATE + " TEXT," +
                COLUMN_NUM_PASSENGERS + " INTEGER," +
                COLUMN_FROM_STATE + " TEXT," +
                COLUMN_DESTINATION_STATE + " TEXT," +
                COLUMN_DATE + " TEXT," +
                COLUMN_PASSENGERS + " INTEGER" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades here if needed
        // This method is called when the database version is increased
    }

    // Insert a new flight record into the database
    public long insertFlight(String origin, String destination, String departureDate, int numPassengers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORIGIN, origin);
        values.put(COLUMN_DESTINATION, destination);
        values.put(COLUMN_DEPARTURE_DATE, departureDate);
        values.put(COLUMN_NUM_PASSENGERS, numPassengers);
        return db.insert(TABLE_FLIGHTS, null, values);
    }

    // Update an existing flight record
    public int updateFlight(long id, String origin, String destination, String departureDate, int numPassengers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORIGIN, origin);
        values.put(COLUMN_DESTINATION, destination);
        values.put(COLUMN_DEPARTURE_DATE, departureDate);
        values.put(COLUMN_NUM_PASSENGERS, numPassengers);
        return db.update(TABLE_FLIGHTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Delete a flight record
    public void deleteFlight(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FLIGHTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Get all flights from the database
    public Cursor getAllFlights() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_FLIGHTS, null, null, null, null, null, null);
    }

    // Get a flight by ID
    public Cursor getFlightById(long id) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true, TABLE_FLIGHTS, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor searchFlights(String fromState, String destinationState, String date, int passengers) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_FROM_STATE, COLUMN_DESTINATION_STATE, COLUMN_DATE, COLUMN_PASSENGERS};
        String selection = COLUMN_FROM_STATE + " = ? AND " +
                COLUMN_DESTINATION_STATE + " = ? AND " +
                COLUMN_DATE + " = ? AND " +
                COLUMN_PASSENGERS + " = ?";
        String[] selectionArgs = {fromState, destinationState, date, String.valueOf(passengers)};
        return db.query(TABLE_FLIGHTS, projection, selection, selectionArgs, null, null, null);
    }
}
