package com.ps.onedoor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.ps.onedoor.DTO.ViewPagerDTO;
import com.ps.onedoor.R;

import java.util.ArrayList;
public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<ViewPagerDTO> list;
    ViewPager viewPager;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private int images[] = {R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1,R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1,R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1,R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1,R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1,R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1,R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1,R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1,R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1,R.drawable.sofaclean,R.drawable.pestclean,R.drawable.hclean,R.drawable.air,R.drawable.clean1};

    public ViewPagerAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.adapter_view_pager, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageTV);
        imageView.setImageResource(images[position]);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Clicked Image ", Toast.LENGTH_SHORT).show();


//                Intent intent = new Intent(getContext(), ZoomActivity.class);
//                intent.putExtra("My Image", images[position]);
//                getContext().startActivity(intent);

            }
        });

        return view;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
