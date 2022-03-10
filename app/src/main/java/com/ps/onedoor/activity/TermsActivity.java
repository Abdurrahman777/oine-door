package com.ps.onedoor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ps.onedoor.R;
import com.ps.onedoor.common.BaseActivity;
import com.ps.onedoor.databinding.ActivityTermsBinding;

public class TermsActivity extends BaseActivity  {
     ActivityTermsBinding binding;
     Context mContext;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_terms);
     binding= DataBindingUtil.setContentView( this, R.layout.activity_terms);
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


    private void initvalues()
    {
       mContext=this;
    }

}