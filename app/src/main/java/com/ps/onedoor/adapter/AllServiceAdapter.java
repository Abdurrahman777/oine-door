package com.ps.onedoor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ps.onedoor.DTO.SaloncatDTO;
import com.ps.onedoor.R;
import com.ps.onedoor.activity.ItemviewActivity;
import com.ps.onedoor.databinding.AllservicecategoryadapterlayoutBinding;
import com.ps.onedoor.databinding.SalonadapterlayoutBinding;

import java.util.ArrayList;

public class    AllServiceAdapter extends RecyclerView.Adapter<AllServiceAdapter.VH> {
    AllservicecategoryadapterlayoutBinding  binding;
    Context mContext;
    ArrayList<SaloncatDTO> saloncatDTOS;
    LayoutInflater inflater;

    public AllServiceAdapter(Context context, ArrayList<SaloncatDTO>list) {
        saloncatDTOS=list;
        mContext = context;
        inflater=LayoutInflater.from(mContext);
    }

    public  void datalist(ArrayList<SaloncatDTO> saloncatDTOS1)
    {
        saloncatDTOS=saloncatDTOS1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllServiceAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding= DataBindingUtil.inflate(inflater, R.layout.allservicecategoryadapterlayout,parent,false);
        return new AllServiceAdapter.VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllServiceAdapter.VH holder, final int position) {
        holder.binding.catImage.setImageResource(saloncatDTOS.get(position).getImage());
        holder.binding.catName.setText(saloncatDTOS.get(position).getTitle());
        holder.binding.allserviceLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(mContext, "CLick Item Successful", Toast.LENGTH_SHORT).show();*/
                Intent intent=new Intent(mContext, ItemviewActivity.class);
                intent.putExtra("name",saloncatDTOS.get(position).getTitle());
                intent.putExtra("image",saloncatDTOS.get(position).getImage());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return saloncatDTOS.size();
    }
    public class VH extends RecyclerView.ViewHolder {
        AllservicecategoryadapterlayoutBinding binding;
        public VH(@NonNull AllservicecategoryadapterlayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}


