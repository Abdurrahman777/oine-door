package com.ps.onedoor.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.ps.onedoor.DTO.CategoryAdapterDTO;
import com.ps.onedoor.DTO.SaloncatDTO;
import com.ps.onedoor.R;
import com.ps.onedoor.activity.AllCleanerservicesActivity;
import com.ps.onedoor.activity.AllRepairseriveActivity;
import com.ps.onedoor.activity.AllserviceActivity;
import com.ps.onedoor.adapter.CategoryAdapter;
import com.ps.onedoor.adapter.Itemscatadapter;
import com.ps.onedoor.adapter.ViewPagerAdapter;
import com.ps.onedoor.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    ViewPagerAdapter adapter;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private int dotsCount;
    private ImageView[] dots;
    private String MSG = "";
    Context mContext;
    CategoryAdapter categoryAdapter;
    Itemscatadapter itemscatadapter;
    ArrayList<CategoryAdapterDTO> categoryAdapterDTOS;
    ArrayList<SaloncatDTO> saloncatDTOS;

    public static HomeFragment homeFragment;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initvalues();
        setAdapter();
        setcategoryitem();
        setcategory();
        setsaloncatitem();
        scatCategory();
        rapaircatitem();
        rapaircategory();
        cleaningcategoryitem();
        cleaningcategory();
        ClickEvent();
        return view;
    }

    private void ClickEvent()
    {
      binding.allserviceTV.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(mContext, AllserviceActivity.class);
              mContext.startActivity(intent);
          }
      });
      binding.allrapairservices.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(mContext, AllRepairseriveActivity.class);
              mContext.startActivity(intent);
          }
      });
    binding.allcleanerservices.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(mContext, AllCleanerservicesActivity.class);
            mContext.startActivity(intent);
        }
    });
/*
        binding.edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
*/

    }

/*
    private void filter(String text) {
        ArrayList<SaloncatDTO> filteredList = new ArrayList<>();

        for (SaloncatDTO item : saloncatDTOS) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        itemscatadapter.filterList(filteredList);
    }
*/




    private void initvalues() {
        mContext = getContext();
        view = binding.getRoot();
        homeFragment = this;

    }

    private void setAdapter() {
        adapter = new ViewPagerAdapter(getContext());
        binding.viewpager.setAdapter(adapter);
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGES) {

                    currentPage = 0;
                }

                binding.viewpager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 3000);


        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {

            dots[i] = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);

            binding.sliderDots.addView(dots[i], params);

        }

//        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                currentPage = position;

                for (int i = 0; i < dotsCount; i++) {

//                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                }

//                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
     private  void setcategoryitem()
     {
         categoryAdapterDTOS=new ArrayList<>();
         categoryAdapterDTOS.add(new CategoryAdapterDTO(R.drawable.salonwomen,"Salon For \nWomen"));
         categoryAdapterDTOS.add(new CategoryAdapterDTO(R.drawable.salonmen,"Salon For\n men"));
         categoryAdapterDTOS.add(new CategoryAdapterDTO(R.drawable.massagemen," Massage for \nmen"));
         categoryAdapterDTOS.add(new CategoryAdapterDTO(R.drawable.massagewomen,"Massage For\n Women"));
         categoryAdapterDTOS.add(new CategoryAdapterDTO(R.drawable.repair," Appliance \n Repair"));
         categoryAdapterDTOS.add(new CategoryAdapterDTO(R.drawable.airconditioner,"Air Conditioner"));
         categoryAdapterDTOS.add(new CategoryAdapterDTO(R.drawable.plumber,"Pumber & \ncarpentor"));
         categoryAdapterDTOS.add(new CategoryAdapterDTO(R.drawable.sofacleaner,"Cleaning & \n Disinfection"));
         categoryAdapterDTOS.add(new CategoryAdapterDTO(R.drawable.electrisian,"Elecrtrician"));

     }
    private void setcategory() {
     categoryAdapter=new CategoryAdapter(mContext,categoryAdapterDTOS);
     binding.categorygride.setLayoutManager(new GridLayoutManager(getContext(),4));
     binding.categorygride.setAdapter(categoryAdapter);
    }
    private void setsaloncatitem()
    {
        saloncatDTOS=new ArrayList<>();
        saloncatDTOS.add(new SaloncatDTO(R.drawable.salon,"Salon For Men"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.salonww,"Salon For Women"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.massage,"Massage For Men"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.salonw,"Massage For Women"));
    }
    private void scatCategory()
    {
        itemscatadapter =new Itemscatadapter(mContext,saloncatDTOS);
        binding.saloncatRV.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.saloncatRV.setAdapter(itemscatadapter);
    }
    private void rapaircatitem()
    {
        saloncatDTOS=new ArrayList<>();
        saloncatDTOS.add(new SaloncatDTO(R.drawable.ac,"Air Conditioner"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.refrigerator,"Refrigerator"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.water,"Water Purifier"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.washing_machine,"Washing Machine"));
    }

    private void rapaircategory()
    {
        itemscatadapter =new Itemscatadapter(mContext,saloncatDTOS);
        binding.repairRV.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.repairRV.setAdapter(itemscatadapter);
    }
    private void cleaningcategoryitem()
    {
        saloncatDTOS=new ArrayList<>();
        saloncatDTOS.add(new SaloncatDTO(R.drawable.sofaclean,"Sofa Clean"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.hclean,"Full Home Cleaning"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.bclean,"Bathroom Cleaning"));
        saloncatDTOS.add(new SaloncatDTO(R.drawable.pestclean,"Pest control"));
    }
    private void cleaningcategory()
    {

        itemscatadapter =new Itemscatadapter(mContext,saloncatDTOS);
        binding.cleaningRV.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.cleaningRV.setAdapter(itemscatadapter);
    }

}