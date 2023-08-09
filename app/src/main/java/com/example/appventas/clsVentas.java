package com.example.appventas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class clsVentas extends SQLiteOpenHelper {
    // Definir las tablas que tendrá la base de datos
    String tblSalesman = "CREATE TABLE Salesman (ident text primary key, fullname text, email text, password text)";
    String tblSale = "CREATE TABLE Sale(idsale integer primary key autoincrement, ident text, valuesale integer, datesale text )";
    public clsVentas(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblSalesman);
        db.execSQL(tblSale);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Salesman");
        db.execSQL(tblSalesman);
        db.execSQL("DROP TABLE Sale");
        db.execSQL(tblSale);
    }
}
