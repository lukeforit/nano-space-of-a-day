package me.lukeforit.spaceofaday.ui.pref

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat

import me.lukeforit.spaceofaday.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        addPreferencesFromResource(R.xml.pref_general);
        //
        //        bindPreferenceSummaryToValue(findPreference("example_text"));
        //        bindPreferenceSummaryToValue(findPreference("example_list"));
    }

    override fun onCreatePreferences(savedInstanceState: Bundle, rootKey: String) {
        setPreferencesFromResource(R.xml.pref_general, rootKey)

        //        bindPreferenceSummaryToValue(findPreference("example_text"));
        //        bindPreferenceSummaryToValue(findPreference("example_list"));
    }

    companion object {

        private val sBindPreferenceSummaryToValueListener = Preference.OnPreferenceChangeListener { preference, value ->
            val stringValue = value.toString()
            preference.summary = stringValue
            true
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            // Set the listener to watch for value changes.
            preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

            // Trigger the listener immediately with the preference's
            // current value.
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.context)
                            .getString(preference.key, ""))
        }
    }
}
