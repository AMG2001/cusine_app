package tech.mavica.cusine_application;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper_CountriesCusines extends SQLiteOpenHelper {
    Context context;
    static String db_name = "cusines_db";
    static String table_countries_cusines="countries_cusines";
    static int db_version = 16;
    static String col_id="id";
    static String col_cusine_description="cusine_description";
    static String col_country="country";
    static String col_country_flag="flags";


    DBHelper_CountriesCusines(Context context){
        super(context,db_name,null,db_version);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /**
         * Create Country name and flag
         */
        String createQuery = "create table "+table_countries_cusines+" ( "+col_id+" integer primary key autoincrement , "+col_country+" text not null , "+col_cusine_description+" text not null , "+col_country_flag+" integer); ";
        MainActivity.firstTimeDBCreatedSharedPreferencesEditor.putBoolean(MainActivity.first_time_created_key,true);
        sqLiteDatabase.execSQL(createQuery);
        Toast.makeText(context, "database created for first Time ✔", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /**
         * Make first time created shared preferences == true to add initialized cusines again to database  ;
         */
        Toast.makeText(context, "in rebuild table - value before reinitialize : "+MainActivity.firstTimeDBCreatedSharedPreferences.getBoolean(MainActivity.first_time_created_key,true), Toast.LENGTH_SHORT).show();
        MainActivity.firstTimeDBCreatedSharedPreferencesEditor.putBoolean(MainActivity.first_time_created_key,true);
        MainActivity.firstTimeDBCreatedSharedPreferencesEditor.commit();
        Toast.makeText(context, "in rebuild table - value after reinitialize : "+MainActivity.firstTimeDBCreatedSharedPreferences.getBoolean(MainActivity.first_time_created_key,true), Toast.LENGTH_SHORT).show();

        /**
         * Drop table and delete all data
         */
        sqLiteDatabase.execSQL("drop table "+table_countries_cusines);
        /**
         * re create table again :
         */
        onCreate(sqLiteDatabase);

    }

}

