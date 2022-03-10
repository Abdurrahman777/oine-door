package com.ps.onedoor.activity;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.ps.onedoor.DTO.FCM_UserDTO;
import com.ps.onedoor.MainActivity;
import com.ps.onedoor.R;
import com.ps.onedoor.common.BaseActivity;
import com.ps.onedoor.databinding.ActivitySplashBinding;
import com.ps.onedoor.session.SessionManager;


public class SplashActivity extends BaseActivity {
    public static FCM_UserDTO fcmUserDTO= new FCM_UserDTO();
    private Context mContext;
    ActivitySplashBinding binding;
    private String TAG = "SplashActivity";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    setContentView(R.layout.activity_splash);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        initvalues();
        delayScreen();
    }

    private void initvalues() {
        mContext = this;
        sessionManager = new SessionManager(mContext);
    }

    public void delayScreen() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.isLogin())
                    openActivity(MainActivity.class);
                else openActivity(LoginActivity.class);
            }
        }, 2000);
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