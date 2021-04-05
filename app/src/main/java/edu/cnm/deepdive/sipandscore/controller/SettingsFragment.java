package edu.cnm.deepdive.sipandscore.controller;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import edu.cnm.deepdive.sipandscore.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting_preference, rootKey);
        // switch to setting_preferences once built
    }
}