package tech.mavica.cusine_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * CountryCosine Arraylist .
     */
    ArrayList<String> countriesCusinesList=new ArrayList<>();
    ArrayList<Integer> countriesFlagsList=new ArrayList<>();
    ArrayList<String> cusinesDescriptionList=new ArrayList<>();
    CountriesCusinesAdapter countriesCusinesAdapter;
    ListView listOfCusinesCountires;
    /**
     * Country Cosine DBHelper and DBManager .
     */
    DBHelper_CountriesCusines dbHelper_countriesCusines;
    DBManager_CountriesCusines dbManager_countriesCusines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfCusinesCountires=findViewById(R.id.lv_countires_cusines_list);
        /**
         * Add Countrires Names :
         */
        countriesCusinesList.add("Egypt");
        countriesCusinesList.add("Italy");
        countriesCusinesList.add("india");
        /**
         * Add cusines Descriptions :
         */
        cusinesDescriptionList.add("Try and taste all Egyptian Food");
        cusinesDescriptionList.add("Try and taste all Italian Food");
        cusinesDescriptionList.add("Try and taste all indian Food");
        /**
         * Add countries Flags :
         */
        countriesFlagsList.add(R.drawable.egypt_flag);
        countriesFlagsList.add(R.drawable.italy_flag);
        countriesFlagsList.add(R.drawable.india_flag);
        /**
         * Set title for Page .
         */
        this.setTitle("Cusine App");
        /**
         * pass ArrayLists to adapter
         */
        countriesCusinesAdapter=new CountriesCusinesAdapter(this,countriesCusinesList,cusinesDescriptionList,countriesFlagsList);
        /**
         * Add adapter to listView .
         */
        listOfCusinesCountires.setAdapter(countriesCusinesAdapter);
        /**
         * when you press on any item , it navigate to this country foods page :
         */
        listOfCusinesCountires.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenu popup = new PopupMenu(MainActivity.this,view);
                popup.inflate(R.menu.edit_delete_menu);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                      switch(menuItem.getItemId()){
                            case R.id.edit:
                                // TODO : implement edit method
                                Toast.makeText(MainActivity.this, "Edit clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.Delete:
                                Toast.makeText(MainActivity.this, "Delete Clicked", Toast.LENGTH_SHORT).show();
                                // TODO : implement delete method .
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                return true;
            }
        });
    }
}