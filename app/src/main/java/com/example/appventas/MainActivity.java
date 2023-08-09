package com.example.appventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etIdent, etFullname, etEmail, etPassword;
    Button btnSave, btnSearch, btnUpdate, btnDelete;
    TextView tvMessage;
    // Instanciar la base de datos de la clase clsVentas
    String oldIdent;
    clsVentas dbSales = new clsVentas(this, "dbSales", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etIdent = findViewById(R.id.etident);
        etFullname = findViewById(R.id.etfullname);
        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etpassword);
        btnSave = findViewById(R.id.btnsave);
        btnUpdate = findViewById(R.id.btnupdate);
        btnSearch = findViewById(R.id.btnsearch);
        btnDelete = findViewById(R.id.btndelete);
        tvMessage = findViewById(R.id.tvMessage);



        // Eventos
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbw = dbSales.getWritableDatabase();
                if(oldIdent.equals().toString)));
                dbw.execSQL("UPDATE salesman SET fullname ='"+etFullname.getText().toString()+"',email);
                tvMessage.setText("Vendec actuliazado correctamente");
                tvMessage.setTextColor(Color.GREEN);
            }
            else{
                tvMessage.setText("Las identificaciones son diferentes");
                SQLiteDatabase dbr = dbSales.getReadableDatabase();
                String sql = "select * ident ,fullname, email FROM salesman WHERE ident ='" + etIdent.getText().toString() + "'";
                Cursor cSalesman = dbr.rawQuery(sql,null);
                if(!cSalesman.moveToFirst()) {
                    dbr.execSQL();"UPDATE salesman SET fullname ='"+etFullname.getText().toString()+"',email);
                tvMessage.setText("Vendedor actualizado");
                tvMessage.setTextColor(Color.GREEN);

                }
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase dbr = dbSales.getReadableDatabase();
                // variable que contiene la consulta
                String query = "select * ident ,fullname, email FROM salesman WHERE ident ='" + etIdent.getText().toString() + "'";
                //Crear la tabla cursor para almacenar
                Cursor CursorSalesman = dbr.rawQuery(query, null);
                //Si encuentra el registro con el ident especifico
                if (CursorSalesman.moveToFirst()) {
                    etFullname.setText(CursorSalesman.getString(1));
                    etEmail.setText(CursorSalesman.getString(2));
                    tvMessage.setText("");
                    oldIdent = etIdent.getText().toString();

                } else {
                    tvMessage.setTextColor(Color.RED);
                    tvMessage.setText("La identificacion del cliente NO existe, intente con otra...");
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etIdent.getText().toString().isEmpty() && !etFullname.getText().toString().isEmpty() && !etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                    saveSalesman(etIdent.getText().toString(), etFullname.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
                } else {
                    tvMessage.setTextColor(Color.RED);
                    tvMessage.setText("Todos los datos son obligatorios");
                }
            }
        });
    }

    private void saveSalesman(String sIdent, String sFullname, String sEmail, String sPassword) {
        //Buscar la identificacion para que no se repita esta
        SQLiteDatabase dbr = dbSales.getReadableDatabase();
        String sql = "SELECT ident FROM salesman WHERE ident ='" + etIdent.getText().toString() + "'";
        Cursor cSalesmanr = dbr.rawQuery(sql, null);
        if (!cSalesmanr.moveToFirst()) {

            // Instanciar objeto de la clase SQLiteDatabase
            SQLiteDatabase dbw = dbSales.getWritableDatabase();
            ContentValues cSalesman = new ContentValues(); // temporal table
            cSalesman.put("ident", sIdent);
            cSalesman.put("fullname", sFullname);
            cSalesman.put("email", sEmail);
            cSalesman.put("password", sPassword);
            dbw.insert("Salesman", null, cSalesman);
            dbw.close();
            tvMessage.setTextColor(Color.GREEN);
            tvMessage.setText("Vendedor agregado correctamente");

        } else {
            tvMessage.setText("La identificacion del vendedor YA Existe, Intentelo con otra...");
            tvMessage.setTextColor(Color.RED);
        }
    }
}