package com.yuwin.miniproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.yuwin.miniproject.Fragments.SignUpFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.Gravity;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        drawerLayout = findViewById(R.id.drawer_layout);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bootomNavigationView);


        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.discoverFragment);
        topLevelDestinations.add(R.id.selectedPlaceFragment);
        topLevelDestinations.add(R.id.selectedMealFragment);
        topLevelDestinations.add(R.id.upsellFragment);
        topLevelDestinations.add(R.id.userFragment);
        topLevelDestinations.add(R.id.foodItemFragment);
        topLevelDestinations.add(R.id.favouriteFragment);
        topLevelDestinations.add(R.id.orderFragment);
        topLevelDestinations.add(R.id.cardFragment);
        topLevelDestinations.add(R.id.settingFragment);
        topLevelDestinations.add(R.id.contactFragment);
        topLevelDestinations.add(R.id.checkoutFragment);
        topLevelDestinations.add(R.id.confirmationFragment);


        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        if(navController.getCurrentDestination().getId() == R.id.fragment_food_types){
            finish();
            return;
        }
        else if (navController.getCurrentDestination().getId() == R.id.discoverFragment){
            navController.navigate(R.id.action_discoverFragment_to_foodItemFragment);
            return;
        }
        else if(navController.getCurrentDestination().getId() == R.id.userFragment) {
            navController.navigate(R.id.action_userFragment_to_foodItemFragment);
            return;
        }
        else if(navController.getCurrentDestination().getId() == R.id.favouriteFragment) {
            navController.navigate(R.id.action_favouriteFragment_to_foodItemFragment);
            return;
        }
        else if(navController.getCurrentDestination().getId() == R.id.orderFragment) {
            navController.navigate(R.id.action_orderFragment_to_foodItemFragment);
            return;
        }
        else if(navController.getCurrentDestination().getId() == R.id.settingFragment) {
            navController.navigate(R.id.action_settingFragment_to_foodItemFragment);
            return;
        }
        else if(navController.getCurrentDestination().getId() == R.id.contactFragment) {
            navController.navigate(R.id.action_contactFragment_to_foodItemFragment);
            return;
        }else if(navController.getCurrentDestination().getId() == R.id.selectedPlaceFragment) {
            navController.navigate(R.id.action_selectedPlaceFragment_to_discoverFragment);
            return;
        }
        else if(navController.getCurrentDestination().getId() == R.id.cardFragment) {
            navController.navigate(R.id.action_cardFragment_to_foodItemFragment);
            return;
        }

        super.onBackPressed();
//         NavigationUI.navigateUp(navController, appBarConfiguration);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavController navigation = Navigation.findNavController(this, R.id.nav_host_fragment);

        switch (item.getItemId()){
            case R.id.userFragment:navigation.navigate(R.id.userFragment);break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}