package com.barryzea.simpleadmob.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import com.barryzea.simpleadmob.BuildConfig;
import com.barryzea.simpleadmob.R;
import com.barryzea.simpleadmob.databinding.AboutThisLayoutBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SettingsFragment extends PreferenceFragmentCompat {
    private SwitchPreferenceCompat swpDarkTheme;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        swpDarkTheme = findPreference("darkTheme");
        assert swpDarkTheme != null;
        swpDarkTheme.setOnPreferenceChangeListener((preference, newValue) ->{
            boolean value = (boolean) newValue;
            if(value){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

            return true;
        });
        findPreference("info").setOnPreferenceClickListener(preference -> {
            showDialogAboutThis();
            return false;
        });
        findPreference("rateUs").setOnPreferenceClickListener(preference->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.barryzea.simpleadmob"));
            startActivity(intent);
            return false;
        });


    }
    private void showDialogAboutThis(){

        AboutThisLayoutBinding bind = AboutThisLayoutBinding.inflate(getLayoutInflater());
        bind.tvDescription.setText(getString(R.string.aboutThisBody, BuildConfig.VERSION_NAME));
        new MaterialAlertDialogBuilder(getActivity())
                .setView(bind.getRoot())
                .setPositiveButton(getString(R.string.Accept), (dialog, which) -> {
                    dialog.dismiss();
                }).show();
    }

}