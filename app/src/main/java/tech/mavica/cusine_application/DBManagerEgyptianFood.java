package tech.mavica.cusine_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManagerEgyptianFood {
static Context context;
static DBHelperEgyptianFood egyptianFoodDBHelper;
    static  String EGYPTIANFOOD_TABLE_NAME = "egyptian_food_table";
    static String EGYPTIANFOOD_DATABASE_NAME="egyptianfood";
    static int EGYPTIANFOOD_DATABASE_VERSION=1;
    static String EGYPTIANFOOD_COLUMN_ID="ID";
    static String EGYPTIANFOOD_COLUMN_FOODNAME="foodname";
    static String EGYPTFOOD_COLUMN_PREPARING_WAY="preparing_way";
    static String EGYPTIANFOOD_COLUMN_FOODDESCRIPTION="FOODDESCRIPTION";
    static String EGYPTIANFOOD_COLUMN_IMAGE="egyptian_food_image";
    static SQLiteDatabase egyptianFoodDBManager;
    DBManagerEgyptianFood(DBHelperEgyptianFood dbhelper_egyptianfoodCons,Context contextCons){
        context=contextCons;
        egyptianFoodDBHelper=dbhelper_egyptianfoodCons;
    }

    void insertNewEgyptianFoodItem(String name,String description,String preparingWay,int image){
        egyptianFoodDBManager=egyptianFoodDBHelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(EGYPTIANFOOD_COLUMN_FOODNAME,name);
        cv.put(EGYPTIANFOOD_COLUMN_FOODDESCRIPTION,description);
        cv.put(EGYPTFOOD_COLUMN_PREPARING_WAY,preparingWay);
        cv.put(EGYPTIANFOOD_COLUMN_IMAGE,image);
        egyptianFoodDBManager.insert(EGYPTIANFOOD_TABLE_NAME,null,cv);
        egyptianFoodDBHelper.close();
    }

    Cursor display(){
        String displayQuery = "select * from "+EGYPTIANFOOD_TABLE_NAME;
        egyptianFoodDBManager= egyptianFoodDBHelper.getWritableDatabase();
        Cursor cursor = egyptianFoodDBManager.rawQuery(displayQuery,null);
        return  cursor;
    }

    void deleteEgyptianFood(int id){
        egyptianFoodDBManager=egyptianFoodDBHelper.getWritableDatabase();
    egyptianFoodDBManager.delete(EGYPTIANFOOD_TABLE_NAME,EGYPTIANFOOD_COLUMN_ID+" = "+id,null);
    egyptianFoodDBHelper.close();
    EgyptianFoodActivity.refreshUI();
    }

    void update(int id ,String name,String description,String preparingWay,int image){
        egyptianFoodDBManager=egyptianFoodDBHelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(EGYPTIANFOOD_COLUMN_FOODNAME,name);
        cv.put(EGYPTIANFOOD_COLUMN_FOODDESCRIPTION,description);
        cv.put(EGYPTFOOD_COLUMN_PREPARING_WAY,preparingWay);
        cv.put(EGYPTIANFOOD_COLUMN_IMAGE,image);
        egyptianFoodDBManager.update(EGYPTIANFOOD_TABLE_NAME,cv,EGYPTIANFOOD_COLUMN_ID+" = "+id,null);
        egyptianFoodDBHelper.close();
    }
}
