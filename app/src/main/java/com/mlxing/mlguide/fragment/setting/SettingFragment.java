package com.mlxing.mlguide.fragment.setting;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.mlxing.mlguide.R;
import com.mlxing.mlguide.utils.PreferencesUtils;

/**
 * Created by quan on 2016/6/21.
 */
public class SettingFragment extends PreferenceFragment {

    //日间或者夜间模式
    private static final String PRE_THEME_MODE = "dark_mode";

    //省流量模式 这儿和R.string.save_net_mode相同
    private static final String PRE_SAVE_NET_MODE = "save_net_mode";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        final CheckBoxPreference checkboxPref = (CheckBoxPreference) getPreferenceManager()
                .findPreference(getString(R.string.save_net_mode));

        checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            /**
             * @param preference The changed Preference.
             * @param newValue   The new value of the Preference.
             * @return True to update the state of the Preference with the new value.
             */
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean checked = Boolean.valueOf(newValue.toString());
                PreferencesUtils.putBoolean(getActivity(),PRE_SAVE_NET_MODE,checked);
                return true;
            }
        });
    }
}
