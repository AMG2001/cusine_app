package tech.mavica.cusine_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBManager_CountriesCusines {
    Context context;
    static String db_name = "cusines_db";
    static String table_countries_cusines="countries_cusines";
    static int db_version = 1;
    SQLiteDatabase dbManager_countriesCusines;
    static String col_id="id";
    static String col_cusine_description="cusine_description";
    static String col_country="country";
    static String col_country_flag="flags";
    DBHelper_CountriesCusines dbHelper_countriesCusines;
    DBManager_CountriesCusines(Context c,DBHelper_CountriesCusines dphelper){
        this.context=c;
        this.dbHelper_countriesCusines=dphelper;
    }

    void insertNewCusine(String cusineCountry,String description,int flag){
        dbManager_countriesCusines=dbHelper_countriesCusines.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col_country,cusineCountry);
        cv.put(col_cusine_description,description);
        cv.put(col_country_flag,flag);
        long x = dbManager_countriesCusines.insert(table_countries_cusines,null,cv);
        Toast.makeText(context, "row inserted Successfully ✔", Toast.LENGTH_SHORT).show();
        dbHelper_countriesCusines.close();
    }

    void deleteCusine(int id){
        dbManager_countriesCusines = dbHelper_countriesCusines.getWritableDatabase();
        dbManager_countriesCusines.delete(table_countries_cusines,"id = "+id,null);
        MainActivity.refreshUI();
        dbHelper_countriesCusines.close();
    }

    Cursor display(){
        String displayQuery = "select * from "+table_countries_cusines;
       dbManager_countriesCusines= dbHelper_countriesCusines.getWritableDatabase();
       Cursor cursor = dbManager_countriesCusines.rawQuery(displayQuery,null);
       return  cursor;
    }

}
