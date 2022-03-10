package com.ps.onedoor.activity;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ps.onedoor.DTO.SaloncatDTO;
import com.ps.onedoor.R;
import com.ps.onedoor.adapter.AllServiceAdapter;
import com.ps.onedoor.adapter.Itemscatadapter;
import com.ps.onedoor.common.BaseActivity;
import com.ps.onedoor.databinding.ActivityAllserviceBinding;

import java.util.ArrayList;

public class AllserviceActivity extends BaseActivity {
    ActivityAllserviceBinding binding;
    Context mContext;
     /*Itemscatadapter itemscatadapter;*/
    AllServiceAdapter allServiceAdapter;
    ArrayList<SaloncatDTO> saloncatDTOS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    setContentView(R.layout.activity_allservice);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_allservice);
        initvalues();
        setsaloncatitem();
        scatCategory();
        ClickEvent();
    }

    private void initvalues() {
        mContext = this;
    }

    private void setsaloncatitem() {
        saloncatDTOS = new ArrayList<>();
        saloncatDTOS.add(new SaloncatDTO(R.drawable.salon, "Salon At home"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.salonww, "Salon for Women"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.massage, "Massage For Men"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.salonw, "Massage For Women"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.doctor, "doctor "));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.beardgroomingmen, " Beard Grooming"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.cleanshavemen, "Clean save"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.haircolormen, "hair color men"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.stressmen, " stress Relief Therapies "));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.stresswomen, "stress Relief Therapies  "));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.painmen, "Pain Relief Therapies"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.painwomen, "Pain Relief Therapies"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.facialwomen, "facial & clean"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.waxingformen, "Waxing for men"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.waxingmen, "Waxing for women"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.manicarewomen, "Manicare for Women"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.pedicare, "pedicare for  Women"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.threadingwomen, "Threading Women"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.haircolormen, "hair color men"));

    }

    private void scatCategory() {
        allServiceAdapter = new AllServiceAdapter(mContext, saloncatDTOS);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        binding.serviceRV.setLayoutManager(linearLayoutManager);
        binding.serviceRV.setAdapter(allServiceAdapter);

    }

    private void ClickEvent() {
        binding.backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.searchmenu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.action_search);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();*/

        binding.searchSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        /*return  true;*/

    }
    private void filter(String text)
    {
        ArrayList<SaloncatDTO>saloncatDTOS1=new ArrayList<>();
        for (SaloncatDTO item:saloncatDTOS)
        {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase()))
            {
            saloncatDTOS1.add(item);

            }
        }
        if (saloncatDTOS1.isEmpty())
        {
            Toast.makeText(mContext, " No Data found ", Toast.LENGTH_SHORT).show();
        }
         else {
            allServiceAdapter.datalist(saloncatDTOS1);

        }
    }

}


