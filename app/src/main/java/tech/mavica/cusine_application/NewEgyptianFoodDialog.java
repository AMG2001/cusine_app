package tech.mavica.cusine_application;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class NewEgyptianFoodDialog  extends AppCompatDialogFragment {
    DBManagerEgyptianFood dbManagerEgyptianFood;
    private EditText et_egyptianName;
    private EditText et_egyptianDescription;
    private  EditText et_egyptianFoodPreparingWay;
    static Context context;
    /**
     * take DBManager as Parameter to be able to implement all CRUD Operations
     */
    NewEgyptianFoodDialog(DBManagerEgyptianFood dbManagerEgyptianFood,Context c){
        this.dbManagerEgyptianFood=dbManagerEgyptianFood;
        context=c;
    }
    @SuppressLint("MissingInflatedId")
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
        View view = inflater.inflate(R.layout.new_food_item,null);
        /**
         * setTitle -> is Text that appear at the top of the Dialog.
         * setNegativeButton(Text,Listener) , is Cancel Button .
         * setPositiveButton is the oppisite : used to insert new Cusine into DB with "Name , description and Flag"
         */
        builder.setView(view).setTitle("Enter New Item").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
                 * Insert New Egyptian food  in Database and then referesh UI
                 */
                try{
                    dbManagerEgyptianFood.insertNewEgyptianFoodItem(et_egyptianName.getText().toString(),et_egyptianDescription.getText().toString(),et_egyptianFoodPreparingWay.getText().toString(),R.drawable.eg_food_ma74y);
                }catch (Exception e){
                    Toast.makeText(context,"problem when adding new egyptian food" , Toast.LENGTH_SHORT).show();
                }
                 EgyptianFoodActivity.refreshUI();
            }
        });
        et_egyptianName=view.findViewById(R.id.et_foodName);
        et_egyptianDescription=view.findViewById(R.id.et_foodDescription);
        et_egyptianFoodPreparingWay=view.findViewById(R.id.et_preparingWay);
        return builder.create();
    }


}
