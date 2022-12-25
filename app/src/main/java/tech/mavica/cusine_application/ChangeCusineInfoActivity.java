package tech.mavica.cusine_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChangeCusineInfoActivity extends AppCompatActivity {
    Uri imagepath;
    Bitmap imageToStore;
    EditText et_cusineCountry;
    EditText et_cusineDescription;
    ImageView flag_image;
    Button btn_changeFlag;
    String cusine_name;
    String cusine_description;
    int flag;
    int id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_cusine_info);

        /**
         * Get data that sent from main page .
         */
        Intent i = getIntent();
        cusine_name=i.getStringExtra("cusine_name");
        cusine_description=i.getStringExtra("cusine_description");
        flag = i.getIntExtra("flag" , R.drawable.ic_launcher_foreground);
        id = i.getIntExtra("id",1);
        /**
         * link UI with class .
         */
        et_cusineCountry=findViewById(R.id.et_cusineName);
        et_cusineDescription=findViewById(R.id.et_description);
        flag_image=findViewById(R.id.country_flag);
        btn_changeFlag=findViewById(R.id.btn_changeFlag);
        /**
         * set UI Data with comming intent data
         */
        et_cusineCountry.setText(cusine_name);
        et_cusineDescription.setText(cusine_description);
        flag_image.setImageResource(flag);
        /**
         * change flag Button Listener .
         */
        btn_changeFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }

    /**
     * this method will get the image and load it into image view .
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try{
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==100 && resultCode== RESULT_OK && data!=null){
                imagepath=data.getData();
                imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);

            }
        }catch (Exception e){
            Toast.makeText(this, "error while getting image", Toast.LENGTH_SHORT).show();
        }
    }

    void chooseImage(){

        try{
            Intent intent=new Intent();
            intent.setType("image/*"); // this line mean that this intent will carry image with ant extensions
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,100);
        }catch (Exception e){
            Toast.makeText(this, "No Image Choosed !! ", Toast.LENGTH_SHORT).show();
        }
    }

}