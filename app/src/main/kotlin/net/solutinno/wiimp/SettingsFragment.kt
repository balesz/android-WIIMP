package net.solutinno.wiimp

import android.os.Bundle
import android.support.v14.preference.PreferenceFragment

class SettingsFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)
    }

    override fun onCreatePreferences(p0: Bundle?, p1: String?) {

    }
}
