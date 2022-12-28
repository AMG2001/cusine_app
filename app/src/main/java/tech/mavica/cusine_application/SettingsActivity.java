package tech.mavica.cusine_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        setTitle("Settings_page");
        getSupportFragmentManager().beginTransaction().replace(R.id.settingsFrame,new SettingsFragment()).commit();
    }
}