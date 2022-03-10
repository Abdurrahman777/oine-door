package com.ps.onedoor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.ps.onedoor.R;
import com.ps.onedoor.common.BaseActivity;
import com.ps.onedoor.databinding.ActivityItemviewBinding;
import com.ps.onedoor.fragment.BookingFragment;

public class ItemviewActivity extends BaseActivity {

    ActivityItemviewBinding binding;
    private Context mContext;
    Bundle bundle;
    String name;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemview);
     binding= DataBindingUtil.setContentView(this,R.layout.activity_itemview);
     initvalue();
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
    /* binding.addBT.setOnClickListener(new View.OnClickListener() {
         @Override
           public void onClick(View view) {
           Intent intent=new Intent(mContext,BookingFragment.class);
           mContext.startActivity(intent)   ;
              }
     });*/
    }

    private void initvalue()
    {
       mContext=this;
       bundle=getIntent().getExtras();
       if (bundle!=null)
       {
          name=bundle.getString("name");
           image = bundle.getInt("image");
       }
        binding.name.setText(name);
        binding.image.setImageResource(image);



    }


}