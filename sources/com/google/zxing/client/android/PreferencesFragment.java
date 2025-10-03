package com.google.zxing.client.android;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class PreferencesFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private CheckBoxPreference[] checkBoxPrefs;

    public class CustomSearchURLValidator implements Preference.OnPreferenceChangeListener {
        private CustomSearchURLValidator() {
        }

        private boolean isValid(Object obj) {
            if (obj == null) {
                return true;
            }
            String string = obj.toString();
            if (string.isEmpty()) {
                return true;
            }
            try {
                return new URI(string.replaceAll("%[st]", "").replaceAll("%f(?![0-9a-f])", "")).getScheme() != null;
            } catch (URISyntaxException unused) {
                return false;
            }
        }

        @Override // android.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object obj) {
            if (isValid(obj)) {
                return true;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(PreferencesFragment.this.getActivity());
            builder.setTitle(C4453R.string.msg_error);
            builder.setMessage(C4453R.string.msg_invalid_value);
            builder.setCancelable(true);
            builder.show();
            return false;
        }
    }

    private void disableLastCheckedPref() {
        ArrayList arrayList = new ArrayList(this.checkBoxPrefs.length);
        for (CheckBoxPreference checkBoxPreference : this.checkBoxPrefs) {
            if (checkBoxPreference.isChecked()) {
                arrayList.add(checkBoxPreference);
            }
        }
        boolean z8 = arrayList.size() <= 1;
        for (CheckBoxPreference checkBoxPreference2 : this.checkBoxPrefs) {
            checkBoxPreference2.setEnabled((z8 && arrayList.contains(checkBoxPreference2)) ? false : true);
        }
    }

    private static CheckBoxPreference[] findDecodePrefs(PreferenceScreen preferenceScreen, String... strArr) {
        CheckBoxPreference[] checkBoxPreferenceArr = new CheckBoxPreference[strArr.length];
        for (int i9 = 0; i9 < strArr.length; i9++) {
            checkBoxPreferenceArr[i9] = (CheckBoxPreference) preferenceScreen.findPreference(strArr[i9]);
        }
        return checkBoxPreferenceArr;
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C4453R.xml.preferences);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        this.checkBoxPrefs = findDecodePrefs(preferenceScreen, PreferencesActivity.KEY_DECODE_1D_PRODUCT, PreferencesActivity.KEY_DECODE_1D_INDUSTRIAL, PreferencesActivity.KEY_DECODE_QR, PreferencesActivity.KEY_DECODE_DATA_MATRIX, PreferencesActivity.KEY_DECODE_AZTEC, PreferencesActivity.KEY_DECODE_PDF417);
        disableLastCheckedPref();
        ((EditTextPreference) preferenceScreen.findPreference(PreferencesActivity.KEY_CUSTOM_PRODUCT_SEARCH)).setOnPreferenceChangeListener(new CustomSearchURLValidator());
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        disableLastCheckedPref();
    }
}
