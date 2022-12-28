package tech.mavica.cusine_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ChangeFoodInfoActivity extends AppCompatActivity {
DBManagerEgyptianFood dbManagerEgyptianFood;
DBHelperEgyptianFood dbHelperEgyptianFood;
Intent intent;
EditText et_foodName;
EditText et_foodDescription;
EditText et_foodPreparingWay;
ImageView image;
int idFromIntent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_food_info);
        et_foodDescription=findViewById(R.id.et_changeFoodInfo_foodDescription);
        et_foodName=findViewById(R.id.et_changeFoodInfo_foodName);
        et_foodPreparingWay=findViewById(R.id.et_changeFoodInfo_foodPreparingWay);
        image=findViewById(R.id.image_changeFoodItem);
        intent=getIntent();
        idFromIntent=intent.getIntExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_ID,1);
        et_foodName.setText(intent.getStringExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_FOODNAME));
        et_foodDescription.setText(intent.getStringExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_FOODDESCRIPTION));
        et_foodPreparingWay.setText(intent.getStringExtra(DBManagerEgyptianFood.EGYPTFOOD_COLUMN_PREPARING_WAY));
        image.setImageResource(intent.getIntExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_IMAGE,R.drawable.ic_launcher_foreground));
        dbHelperEgyptianFood=new DBHelperEgyptianFood(this);
        dbManagerEgyptianFood=new DBManagerEgyptianFood(dbHelperEgyptianFood,this);
    }
    public  void updateFoodInfo(View view){
        String name=intent.getStringExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_FOODNAME) ;
        String description = intent.getStringExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_FOODDESCRIPTION) ;
        String preparingWay = intent.getStringExtra(DBManagerEgyptianFood.EGYPTFOOD_COLUMN_PREPARING_WAY) ;
        int image = intent.getIntExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_IMAGE,R.drawable.ic_launcher_foreground) ;
        int id = intent.getIntExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_ID,1) ;
        dbManagerEgyptianFood.update(id,name,description,preparingWay,image);
        EgyptianFoodActivity.refreshUI();
        startActivity(new Intent(this,EgyptianFoodActivity.class));
    }
}