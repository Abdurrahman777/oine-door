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
import com.ps.onedoor.common.BaseActivity;
import com.ps.onedoor.databinding.ActivityAllRepairseriveBinding;

import java.util.ArrayList;

public class AllRepairseriveActivity extends BaseActivity {
    private Context mContext;
    ActivityAllRepairseriveBinding binding;
    AllServiceAdapter allServiceAdapter;
    ArrayList<SaloncatDTO> saloncatDTOS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    setContentView(R.layout.activity_all_repairserive);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_repairserive);
        initvalues();
        setsaloncatitem();
        scatCategory();
        ClickEvent();
    }

    private void initvalues() {
        mContext = this;
         saloncatDTOS = new ArrayList<>();
    }

    private void ClickEvent() {
        binding.backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        binding.searchSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filter(newText);
//                return false;
//            }
//        });
        /*return  true;*/
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

    }
//    private void filter(String text)
//    {
//        ArrayList<SaloncatDTO>saloncatDTOS1=new ArrayList<>();
//        for (SaloncatDTO item:saloncatDTOS)
//        {
//            if (item.getTitle().toLowerCase().contains(text.toLowerCase()))
//            {
//                saloncatDTOS1.add(item);
//            }
//        }
//        if (saloncatDTOS1.isEmpty())
//        {
//            Toast.makeText(mContext, " No Data found ", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            allServiceAdapter.datalist(saloncatDTOS1);
//
//        }
//    }

    public void filter(String text)
    {
        for (SaloncatDTO item:saloncatDTOS) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                saloncatDTOS.add(item);
            }
        }
        if (saloncatDTOS.isEmpty())
            {
                customToast("No Data Found",200,true,null);
            }
        else {
            allServiceAdapter.datalist(saloncatDTOS);
        }

    }




    private void setsaloncatitem() {

        saloncatDTOS.add(new SaloncatDTO(R.drawable.ac, "Air Conditioner"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.chimney, "chimney"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.geyser, "Geyser"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.microwave, "Microwave"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.refrigerator, "Refrigerator"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.water, "Water Purifier"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.television, "Television"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.washing_machine, "Washing Machine"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.fan, "Fan"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.aircooler, " Air cooler"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.wiring, "Wiring"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.microwave, "Microwave"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.doorbell,"door bell"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.chandelier, "chandelier"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.inverter, "inverter"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.tubelight, "tubelight"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.mcb, "Mcb"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.socket, "Switch & Socket"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.tubelight, "tubelight"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.heater, "Room Heater"));
    }

    private void scatCategory()
    {
        allServiceAdapter = new AllServiceAdapter(mContext, saloncatDTOS);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        binding.allrepairRV.setLayoutManager(linearLayoutManager);
        binding.allrepairRV.setAdapter(allServiceAdapter);
    }

}