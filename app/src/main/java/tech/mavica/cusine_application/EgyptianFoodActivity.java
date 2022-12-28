package tech.mavica.cusine_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class EgyptianFoodActivity extends AppCompatActivity {
    static ListView lv_egyptianFood;
    SharedPreferences egyptianFoodSharedPref;
    SharedPreferences.Editor egyptianFoodSharedPrefEditor;
    static String egyptianFoodSharedPrefKey="egyptian_food";
    static ArrayList<String> foodNameList=new ArrayList<>();
    static ArrayList<String> foodDescriptionList=new ArrayList<>();
    static ArrayList<Integer> foodImageList=new ArrayList<>();
    static ArrayList<String> foodPreparingWay=new ArrayList<>();
    static ArrayList<Integer> ids = new ArrayList<>();
    static Context context;
    static FoodAdapter egyptianFoodAdatper;
    static DBHelperEgyptianFood dbHelperEgyptianFood;
    static DBManagerEgyptianFood dbManagerEgyptianFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egyptian_food);
        lv_egyptianFood=findViewById(R.id.lv_egyptianFood);
        context=this;
        /**
         * Getting dbhelper and preparing dbmanager :
         */
        dbHelperEgyptianFood=new DBHelperEgyptianFood(context);
        dbManagerEgyptianFood=new DBManagerEgyptianFood(dbHelperEgyptianFood,context);
        egyptianFoodSharedPref=getSharedPreferences(egyptianFoodSharedPrefKey,MODE_PRIVATE);
        egyptianFoodSharedPrefEditor=egyptianFoodSharedPref.edit();
        if(egyptianFoodSharedPref.getBoolean(egyptianFoodSharedPrefKey,true)==true){
            // insert food name , food description , food preparing way , food image .
            dbManagerEgyptianFood.insertNewEgyptianFoodItem("حواوشي","مقادير:½ كيلو لحم مفروم250 غ دهن خروفبصلة وسط مفرومة ناعم2 فلفل اخضررشة ملحرشة كمونرشة فلفل اسود2 حبهان¼ كوب زيت2 فلفل اخضر5 أرغفة خبز\n","1. افرمي البصل ناعما جدا و اتركيه جانبا فى وعاء عميق .\u000B2. قطعى الفلفل قصطع صغيرة و ضيفيه بالوعاء على البصل .\u000B3. ضيفى الملح و الكمون و الحبهان و الفلفل .\u000B4. ضيفى اللحم المفروم و قومى برش قطعة من دهن الخروف على الخليط .\u000B5. قومى بالتقليب و الخلط جيدا لتمتزج كل المكونات معا .\u000B6. ادخلى الوعاء الثلاجة لمدة ساعة .\u000B7. احضرى الخبز بعد مرور الساعة و اصنعى فى كل خبز من الجانب فتحة صغيرة ادخلى من خلالها الحشو و افرديه داخل الرغيف .\u000B8. كررى الامر مع كل الارغفة الحشو .\u000B9. ادهنى الخبز من اعلى و من اسفل بالزيت .\u000B10. سخنى الفرن جيدا على نار متوسطة .\u000B11. رصى الخبز فى صينية و ادخليها الفرن على الشبكة و اتركيه حتى تمام النضج (من ربع لتلت ساعة) .\u000B12. يقدم الحواوشي ساخنا .\n",R.drawable.hwawshi);
            dbManagerEgyptianFood.insertNewEgyptianFoodItem( "كشري","1 كوب معكرونة مسلوقة\n" +
                    "½1 كوب أرز\n" +
                    "1 كوب عدس أسمر\n" +
                    "1 كوب حمص مسلوق\n" +
                    "2 طماطم مقشرة ومهروسة\n" +
                    "2 بصل\n" +
                    "2 مكعب مرق دجاج\n" +
                    "2 ملعقة كبيرة معجون طماطم\n" +
                    "½ كوب شعيرية\n" +
                    "½ كوب خل أبيض\n" +
                    "3 ملعقة كبيرة زيت\n" +
                    "1 ملعقة صغيرة ملح\n" +
                    "1 ملعقة صغيرة كمون\n" +
                    "2 ثوم مهروس\n" +
                    "1 ملعقة عصير ليمون حامض\n","في وعاء على النار ضعي الماء حتى الغليان.\n" +
                    "ضعي العدس لسلقه وغطي الوعاء واتركيه على نار هادئة.\n" +
                    "في هذه الأثناء قطّعي البصل.\n" +
                    "في وعاء آخر سخّني نصف الزيت واضيفي البصل لتقليته، وحمصيه لدرجة كبيرة أي يتحول للون البني الفاتح.\n" +
                    "ارفعي البصل من الوعاء.\n" +
                    "حمّصي الشعيرية بنفس الوعاء وقلبيها حتى تتحول للون الذهبي.\n" +
                    "اضيفي الماء واتركي الوعاء حتى غليان الماء.\n" +
                    "اضيفي الأرز ومرق الدجاج.\n" +
                    "بعد سلق العدس بالكامل صفيه من الماء واضيفيه على الأرز.\n" +
                    "خففي النار تحت وعاء الأرز وغطيه واتركيه حتى ينضج.\n ",R.drawable.koshari);
            dbManagerEgyptianFood.insertNewEgyptianFoodItem(" باميه","2 ملعقة كبيرة زيت زيتون\n" +
                    "1 حبة بصل مفرومة\n" +
                    "2 فص ثوم مهروس\n" +
                    "200 غرام بامية\n" +
                    "رشة ملح\n" +
                    "رشة فلفل حار\n" +
                    "رشة فلفل أسود\n" +
                    "رشة بابريكا\n" +
                    "1 ملعقة كبيرة معجون طماطم\n" +
                    "½ كوب ماء\n" +
                    "¼ كوب عصير ليمون حامض\n" +
                    "كزبرة خضراء، للزينة\n","سخني الزيت في قدر على نار متوسطة. أضيفي البصل وحمريه لمدة 10 دقائق حتى يصبح لونه ذهبياً.\n" +
                    "أضيفي الثوم وحمريه لمدة دقيقة ثم أضيفي البامية وحركيها باستمرار.\n" +
                    "امزجي معجون الطماطم مع نصف كوب ماء، وقلّبي المزيج جيداً.\n" +
                    "أضيفي الملح، الفلفل الحار، الفلفل والبابريكا إلى الصلصة وقلّبي المزيج.\n" +
                    "أضيفي مزيج الصلصة إلى القدر.\n" +
                    "غطّي القدر واتركيه على نار هادئة لمدة 30 دقيقة مع التقليب كل 5 دقائق.\n",R.drawable.pamia);
            dbManagerEgyptianFood.insertNewEgyptianFoodItem("ورق عنب ","1 كيلو غرام ورق عنب مغسول ومصفى\n" +
                    "الحشو :350 غرام لحم مفروم\n" +
                    "2 حبة بصل مفروم ناعم\n" +
                    "1 باقة بقدونس مفرومة ناعم\n" +
                    "1 باقة شبت مفرومة ناعم\n" +
                    "2 فص ثوم مفرومين ناعم\n" +
                    "2 ملعقة كبيرة زيت نباتي\n" +
                    "مقادير الصلصة :1 مكعب مرقة دجاج\n" +
                    "1 كيلو غرام طماطم معصورة\n" +
                    "¼ ملعقة صغيرة فلفل أسود\n" +
                    "¼ ملعقة صغيرة ملح\n","في قدر عميق مدهون بالزيت، رصي في القاع أوراق عنب غير محشية.\n" +
                    "في وعاء على النار، سخنى الزيت ثم اقلي اللحم فيه، واضيفي باقي مكونات الحشو مع التقليب المستمر ثم ارفعيه عن النار.\n" +
                    "افردي ورق العنب ثم ابدئي بحشوه، ورصيه في القدر بشكل متلاصق جانبيا، وفي طبقات فوق بعضها.\n" +
                    "غطي المحشي بالصلصة ثم أضيفي مكعب مرقة الدجاج والقليل من الماء.\n" +
                    "غطي القدر واتركيه على نار عالية حتى غليان الماء، ثم هدئي النار واتركي القدر حتى ينضج لمدة ساعة تقريبا. ثم قدميه ساخنا.\n" +
                    "\n",R.drawable.wark);



            egyptianFoodSharedPrefEditor.putBoolean(egyptianFoodSharedPrefKey,false);
            egyptianFoodSharedPrefEditor.commit();
        }
        /**
         * refreshing UI to load data from database in ArrayLists
         * assign ArrayLists to Adapter and link it with UI
         */
        refreshUI();
        egyptianFoodAdatper =new FoodAdapter(this,ids,foodNameList,foodDescriptionList,foodImageList);
        lv_egyptianFood.setAdapter(egyptianFoodAdatper);
        lv_egyptianFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {
                showPopupMenu(view,ids.get(index),index);
                return true;
            }
        });
        lv_egyptianFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }



    /**
     * Menu create and inflate in AppBar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return true;
    }
    /**
     * Actioning appbar buttons .
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings_tab:
                startActivity(new Intent(this,SettingsActivity.class));
                return  true;
            default:
                return true;
        }
    }


    /**
     * adding new egyptian food
     * @param view
     */
    public  void insertNewEgyptianFoodItem(View view){
        openDialog();
    }
    public void openDialog(){
        NewEgyptianFoodDialog dialog = new NewEgyptianFoodDialog(dbManagerEgyptianFood,this);
        dialog.show(getSupportFragmentManager(),null);
    }

    /**
     * it used to refresh Ui each time you open this page .
     */
    static void refreshUI(){
        Cursor cursor = dbManagerEgyptianFood.display();
        /**
         * Clear all ArrayLists Data :
         */
        ids.clear();
        foodNameList.clear();
        foodDescriptionList.clear();
        foodImageList.clear();
        foodPreparingWay.clear();
        while(cursor.moveToNext()){
            /**
             * reloading all ArrayLists Data from database :
             */
            // id , name , description , preparing way , image
            ids.add(cursor.getInt(0));
            foodNameList.add(cursor.getString(1));
            foodDescriptionList.add(cursor.getString(2));
            foodPreparingWay.add(cursor.getString(3));
            foodImageList.add(cursor.getInt(4));
        }
        // TODO , base new array lists to adapter .
        egyptianFoodAdatper = new FoodAdapter(context,ids,foodNameList,foodDescriptionList,foodImageList);
        lv_egyptianFood.setAdapter(egyptianFoodAdatper);
    }



    /**
     * Show Edit , Delete Popup Menu .
     * @param view
     */
    void showPopupMenu(View view,int idInDatabase,int indexInMenu){
        PopupMenu popup = new PopupMenu(context,view);
        popup.inflate(R.menu.edit_delete_menu);
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.edit:
                        /**
                         * Send data to change Cusine info page to Edit on it .
                         */
                        Intent i = new Intent(context , ChangeFoodInfoActivity.class);
                        i.putExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_ID,idInDatabase);
                        i.putExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_FOODNAME,foodNameList.get(indexInMenu));
                        i.putExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_FOODDESCRIPTION,foodDescriptionList.get(indexInMenu));
                        i.putExtra(DBManagerEgyptianFood.EGYPTFOOD_COLUMN_PREPARING_WAY,foodPreparingWay.get(indexInMenu));
                        i.putExtra(DBManagerEgyptianFood.EGYPTIANFOOD_COLUMN_IMAGE,foodImageList.get(indexInMenu));
                        startActivity(i);
                        return true;
                    case R.id.Delete:
                        dbManagerEgyptianFood.deleteEgyptianFood(idInDatabase);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}