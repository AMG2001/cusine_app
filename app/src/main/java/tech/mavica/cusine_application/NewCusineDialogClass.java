package tech.mavica.cusine_application;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class NewCusineDialogClass extends AppCompatDialogFragment{



    DBManager_CountriesCusines dbManager_countriesCusines;
    private EditText cusineCountry;
    private EditText description;
    static Context context;
    /**
     * take DBManager as Parameter to be able to implement all CRUD Operations
     * @param dbManager_countriesCusines
     */
    NewCusineDialogClass(DBManager_CountriesCusines dbManager_countriesCusines,Context c){
        this.dbManager_countriesCusines=dbManager_countriesCusines;
        context=c;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /**
         * getActivity -> Context .
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(
                getActivity()
        );
        /**
         * to inflate dialog on Activity .
         */
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layouy_dialog,null);
        /**
         * setTitle -> is Text that appear at the top of the Dialog.
         * setNegativeButton(Text,Listener) , is Cancel Button .
         * setPositiveButton is the oppisite : used to insert new Cusine into DB with "Name , description and Flag"
         */
        builder.setView(view).setTitle("Enter New Cusine").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /**
                 * Nothing to provide , Just remove dialog .
                 */
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /**
                 * Insert New Cusine in Database and then referesh UI
                 */
                dbManager_countriesCusines.insertNewCusine(cusineCountry.getText().toString(),description.getText().toString(),R.drawable.ic_launcher_foreground);
                MainActivity.refreshUI();
            }
        });
        cusineCountry=view.findViewById(R.id.et_cusineCountry);
        description=view.findViewById(R.id.et_description);
        return  builder.create();
    }

}
