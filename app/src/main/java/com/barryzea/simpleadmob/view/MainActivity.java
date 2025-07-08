package com.barryzea.simpleadmob.view;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.barryzea.simpleadmob.R;
import com.barryzea.simpleadmob.databinding.ActivityMainBinding;

import dagger.hilt.EntryPoint;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<IntentSenderRequest> launcher;
    private ActivityMainBinding bind;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_SimpleAdMob);
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        bind=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(bind.getRoot(), new OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                Insets systemBars= insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left,systemBars.top,systemBars.right, systemBars.bottom);
                return insets;
            }
        });
        //onActivityResult();
         setUpNavController();

    }

    private void setUpNavController(){
        navController = Navigation.findNavController(this,R.id.navHostFragment);
        AppBarConfiguration appBarConfig= new AppBarConfiguration.Builder(
                R.id.mainFragmentItem,R.id.settingsFragmentItem
        ).build();
        setupWithNavController(bind.bottomNavView,navController);
        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {

        });


    }
    private void onActivityResult(){
        launcher= registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                int requestCode=result.getData().getExtras().getInt("REQUEST_CODE");
                if (result.getResultCode() == RESULT_OK){
                }}
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(MainActivity.this, R.id.navHostFragment);
        return  NavigationUI.onNavDestinationSelected(item,navController) ||  super.onOptionsItemSelected(item);
    }
}