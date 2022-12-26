package tech.mavica.cusine_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
//    SharedPreferences lightModeSharedPref;
//    static String LIGHT_KEY="isLight";
//    CheckBox isLightCheckBox;
//    Switch isLightSwitch;
//    boolean isLightBool;
//    static String SHARED_PREFERENCES_SWITCH = "sharedPrefSwitch";
//    SharedPreferences.Editor sharedPrefEditor;
//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        setTitle("Settings_page");
        getSupportFragmentManager().beginTransaction().replace(R.id.settingsFrame,new SettingsFragment()).commit();
//        isLightSwitch=findViewById(R.id.sharedPref_switch_light_dark_mode);
//        isLightCheckBox=findViewById(R.id.sharedPref_checkbox_dark_light_mode);
//        isLightSwitch.setChecked(isLightBool);
//        isLightCheckBox.setChecked(isLightBool);
//        isLightBool = lightModeSharedPref.getBoolean(LIGHT_KEY,true);
//        sharedPrefEditor=lightModeSharedPref.edit();


    }
}