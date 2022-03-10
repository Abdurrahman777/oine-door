package com.ps.onedoor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ps.onedoor.R;
import com.ps.onedoor.common.BaseActivity;
import com.ps.onedoor.databinding.ActivityAboutCompanyBinding;

public class AboutCompanyActivity extends BaseActivity {
    private  Context mContext;
      ActivityAboutCompanyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_about_company);
      binding= DataBindingUtil.setContentView(this,R.layout.activity_about_company);
        initvalues();
        ClickEvent();
    }

    private void ClickEvent()
    {
      binding.backIV.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              finish();
          }
      });
    }

    private void initvalues() {
       mContext=this;
    }

}