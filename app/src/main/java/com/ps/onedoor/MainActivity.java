package com.ps.onedoor;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.Transliterator;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fxn.OnBubbleClickListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.ps.onedoor.databinding.ActivityMainBinding;
import com.ps.onedoor.fragment.BookingFragment;
import com.ps.onedoor.fragment.HomeFragment;
import com.ps.onedoor.fragment.ProfileFragment;
import com.ps.onedoor.fragment.RewardFragment;
import com.ps.onedoor.fragment.SettingsFragment;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
     private static final String TAG=MainActivity.class.getSimpleName();
     ActivityMainBinding binding;
     private  Context mContext;
    MenuItem menuItemitem;
    FragmentManager fragmentManager;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
     initvalues();
     ClickEvent();
          loadFragment(new HomeFragment());
    }

    private void initvalues() {
     mContext=this;
      }

    private void ClickEvent()
    {

/*
        binding.navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                Log.d(TAG, "onNavigationItemSelected: " + item.getItemId());
                menuItemitem = item;
                switch (item.getItemId()) {

                    case R.id.home:
                        binding.tvTopHeading.setText("Home");
                        loadFragment(new HomeFragment());
                        binding.Drawer.closeDrawer(Gravity.START);

                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.profile:
                        binding.tvTopHeading.setText("Profile");
                        loadFragment(new ProfileFragment());
                        binding.Drawer.closeDrawer(Gravity.START);
                        Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.booking:
                        binding.tvTopHeading.setText("Booking");
                        loadFragment(new BookingFragment());
                        binding.Drawer.closeDrawer(Gravity.START);
                        Toast.makeText(MainActivity.this, "booking", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.rewards:
                        binding.tvTopHeading.setText("rewards");
                        loadFragment(new RewardFragment());
                        binding.Drawer.closeDrawer(Gravity.START);
                        Toast.makeText(MainActivity.this, "rewads", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.settings:
                        */
/*binding.tvTopHeading.setText("settings");*//*

                        loadFragment(new SettingsFragment());
                        binding.Drawer.closeDrawer(Gravity.START);
                        Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT).show();

      return true;


                    case R.id.share:
//                        Toast.makeText(MainActivity.this, "shared Succesful", Toast.LENGTH_SHORT).show();
                        ApplicationInfo api=getApplicationContext().getApplicationInfo();
                        String apkpath=api.sourceDir;
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
                        startActivity(Intent.createChooser(intent,"ShareVia"));
                        return true;
                }
                item.setChecked(true);

                return false;

            }
        });
*/
       binding.imgMenu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               binding.Drawer.openDrawer(Gravity.LEFT);
           }
       });

       binding.chipbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
           @Override
           public void onItemSelected(int id) {
               Fragment fragment=null;
               switch (id)
               {
                   case R.id.home:
                       fragment=new HomeFragment();
                       binding.tvTopHeading.setText("Home");
                       break;
                   case R.id.booking:
                       fragment=new BookingFragment();
                       binding.tvTopHeading.setText("Booking");
                       break;
                   case R.id.rewards:
                       fragment=new RewardFragment();
                       binding.tvTopHeading.setText("Rewards");
                       break;
                   case R.id.profile:
                       fragment=new ProfileFragment();
                       binding.tvTopHeading.setText("Profile");
                       break;
               }
               if (fragment!=null)
               {
                  fragmentManager=getSupportFragmentManager();
                  fragmentManager.beginTransaction()
                          .replace(R.id.frameDemo1,fragment)
                          .commit();
               }
               else {
                   Log.e(TAG,"Error in Creating Fragment");

               }
           }
       });

    }
    public void loadFragment(Fragment fragment) {
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameDemo1, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } catch (Exception e) {
            Log.d("TAG", "\tloadFragment\t" + e);
        }
//        binding.drawerView.closeDrawer(GravityCompat.START);
    }
    public void onBackPressed() {
        int backpress = 0;
        backpress = backpress + 1;
        /*   Toast.makeText(getApplicationContext(), "  ", Toast.LENGTH_SHORT).show();*/

        if (backpress < 1) {
            this.finish();
        }
    }

}