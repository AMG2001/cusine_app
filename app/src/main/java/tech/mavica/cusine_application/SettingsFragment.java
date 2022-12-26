package tech.mavica.cusine_application;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    /**
     * this is one type of fragments that used to show PreferenceScreen .
     * @param savedInstanceState
     * @param rootKey
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences,rootKey);
    }
}
