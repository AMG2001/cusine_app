package tech.mavica.cusine_application;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelperEgyptianFood extends SQLiteOpenHelper {
    Context c;
    static  String EGYPTIANFOOD_TABLE_NAME = "egyptian_food_table";
    static String EGYPTIANFOOD_DATABASE_NAME="egyptianfood";
    static int EGYPTIANFOOD_DATABASE_VERSION=4;
    static  String EGYPTFOOD_COLUMN_PREPARING_WAY="preparing_way";
    static String EGYPTIANFOOD_COLUMN_ID="ID";
        static String EGYPTIANFOOD_COLUMN_FOODNAME="foodname";
        static String EGYPTIANFOOD_COLUMN_FOODDESCRIPTION="FOODDESCRIPTION";
        static String EGYPTIANFOOD_COLUMN_IMAGE="egyptian_food_image";
        static SQLiteDatabase egyptianFoodDBManager;
    DBHelperEgyptianFood(Context c){
        super(c,EGYPTIANFOOD_DATABASE_NAME,null,EGYPTIANFOOD_DATABASE_VERSION);
        this.c=c;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createEgyptianFoodTableQuery="create table "+EGYPTIANFOOD_TABLE_NAME+"( "+EGYPTIANFOOD_COLUMN_ID+" integer primary key autoincrement , "+EGYPTIANFOOD_COLUMN_FOODNAME+" text , "+EGYPTIANFOOD_COLUMN_FOODDESCRIPTION+" text , "+EGYPTFOOD_COLUMN_PREPARING_WAY+" text , "+EGYPTIANFOOD_COLUMN_IMAGE+" integer ) ;";
        sqLiteDatabase.execSQL(createEgyptianFoodTableQuery);
        egyptianFoodDBManager=sqLiteDatabase;
        Toast.makeText(c, "database created # ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+EGYPTIANFOOD_TABLE_NAME);
        onCreate(sqLiteDatabase);
        Toast.makeText(c, "egyptian food db recreated", Toast.LENGTH_SHORT).show();
    }
}
