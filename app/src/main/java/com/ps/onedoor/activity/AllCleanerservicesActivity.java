package com.ps.onedoor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ps.onedoor.DTO.SaloncatDTO;
import com.ps.onedoor.R;
import com.ps.onedoor.adapter.AllServiceAdapter;
import com.ps.onedoor.databinding.ActivityAllCleanerservicesBinding;

import java.util.ArrayList;

public class AllCleanerservicesActivity extends AppCompatActivity {
  private Context mContext;
  ActivityAllCleanerservicesBinding binding;
    AllServiceAdapter allServiceAdapter;
    ArrayList<SaloncatDTO> saloncatDTOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_all_cleanerservices);
     binding= DataBindingUtil.setContentView(this,R.layout.activity_all_cleanerservices);
     initvalues();
     setsaloncatitem();
     scatCategory();
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
    private void initvalues()
    {
     mContext=this;
    }
    private void setsaloncatitem()
    {

        saloncatDTOS=new ArrayList<>();
        saloncatDTOS.add(new SaloncatDTO(R.drawable.carwash,"car washing"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.clothwash,"Cloth wash"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.helper,"helper"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.pentor,"pentor"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.plumbers,"Plumber"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.bclean,"bathoom cleaner"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.helper,"Worker"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.worker,"Constructor"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.sofaclean,"sofa clearner"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.hclean,"homeCleaner"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.machanic,"Machanic"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.ragmen,"ragmen(kabari)"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.carpentor,"carpentor"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.electrisian,"electricion"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.door,"Door"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.furnitureassamble,"Furniture Assambly"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.kitchenfurniture,"Kitchen furniture"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.bed,"Bed"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.window,"Window & curtain"));
    }
    private void scatCategory()
    {
        allServiceAdapter =new AllServiceAdapter(mContext,saloncatDTOS);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        binding.allcleanerservicesRV.setLayoutManager(linearLayoutManager);
        binding.allcleanerservicesRV.setAdapter(allServiceAdapter);

    }

}