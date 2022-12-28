package tech.mavica.cusine_application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;


public class SettingsFragment extends PreferenceFragmentCompat {

    static SharedPreferences lightModeSharedPref;
    SharedPreferences.Editor lightModeSharedPrefEditor;

    static String LIGHT_KEY="isLight";

    static CheckBoxPreference isLightCheckBox;
    static SwitchPreference isLightSwitch;

    static boolean isLightBool;
    static String SHARED_PREFERENCES_SWITCH = "sharedPrefSwitch";

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        /**
         * link Class with it's UI XML code .
         */
        setPreferencesFromResource(R.xml.settings_preferences,rootKey);
        /**
         * Assign items preferences with its UI Components using it's key .
         */
        isLightSwitch= (SwitchPreference) getPreferenceManager().findPreference("sharedPref_switch_light_dark_mode");
        isLightCheckBox=(CheckBoxPreference) getPreferenceManager().findPreference("sharedPref_checkbox_dark_light_mode");
        /**
         * light mode shared preference and it's editor .
         */
        lightModeSharedPref= getActivity().getSharedPreferences(LIGHT_KEY, Context.MODE_PRIVATE);
        lightModeSharedPrefEditor=lightModeSharedPref.edit();
        /**
         * getting value from shared preference and assign it to UI .
         */
        isLightBool = lightModeSharedPref.getBoolean(LIGHT_KEY,true);
        isLightSwitch.setChecked(isLightBool);
        isLightCheckBox.setChecked(isLightBool);
        /**
         * Action listener for Switch :
         */
        isLightSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue instanceof  Boolean){
                    isLightBool=((Boolean) newValue).booleanValue();
                    if(isLightBool==true){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                    lightModeSharedPrefEditor.putBoolean(LIGHT_KEY,isLightBool);
                    lightModeSharedPrefEditor.commit();
                    // true
                    isLightCheckBox.setChecked(isLightBool);
                    isLightSwitch.setChecked(isLightBool);
                    }
                return true;
            }
        });
        /**
         * Action listener for check box :
         */
        isLightCheckBox.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue instanceof  Boolean){
                    isLightBool=((Boolean) newValue).booleanValue();
                    if(isLightBool==true){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    }
                    lightModeSharedPrefEditor.putBoolean(LIGHT_KEY,isLightBool);
                    lightModeSharedPrefEditor.commit();
                    isLightCheckBox.setChecked(isLightBool);
                    isLightSwitch.setChecked(isLightBool);
                }
                return true;
            }
        });
    }
}
