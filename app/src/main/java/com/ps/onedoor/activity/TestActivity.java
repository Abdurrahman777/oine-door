package com.ps.onedoor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;

import com.ps.onedoor.R;
import com.ps.onedoor.databinding.ActivityTestBinding;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    ActivityTestBinding binding;
         Context mContext;
         TestAdapter testAdapter;
         ArrayList<testDTO>testDTOS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_test);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_test);
        initvalues();
        setadapter();
        setitem();
    }

    private void setitem() {
        testDTOS.add(new testDTO("rahman","salman"));
        testDTOS.add(new testDTO("rahman","salman"));
        testDTOS.add(new testDTO("rahman","salman"));
        testDTOS.add(new testDTO("rahman","salman"));
        testDTOS.add(new testDTO("rahman","salman"));
        testDTOS.add(new testDTO("rahman","salman"));
        testDTOS.add(new testDTO("rahman","salman"));
        testDTOS.add(new testDTO("rahman","salman"));
        testDTOS.add(new testDTO("rahman","salman"));

    }

    private void setadapter() {
       testAdapter= new TestAdapter(mContext,testDTOS);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        binding.demoRV.setLayoutManager(linearLayoutManager);
        binding.demoRV.setAdapter(testAdapter);

    }

    private void initvalues() {
     mContext=this;
        testDTOS= new ArrayList<>();
    }
}