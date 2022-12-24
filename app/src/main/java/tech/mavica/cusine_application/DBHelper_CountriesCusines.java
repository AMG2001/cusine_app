package tech.mavica.cusine_application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper_CountriesCusines extends SQLiteOpenHelper {
    Context context;
    static String db_name = "cusines_db";
    static String table_countries_cusines="countries_cusines";
    static int db_version = 1;
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
        String createQuery = "create table "+table_countries_cusines+" ( "+col_id+" integer primary key autoincrement , "+col_cusine_description+" text not null , "+col_country+" text not null , "+col_country_flag+" integer); ";
        sqLiteDatabase.execSQL(createQuery);
        Toast.makeText(context, "database created for first Time ✔", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
