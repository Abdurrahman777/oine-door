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
import com.ps.onedoor.databinding.SalonadapterlayoutBinding;

import java.util.ArrayList;

public class Itemscatadapter extends RecyclerView.Adapter<Itemscatadapter.VH> {
    SalonadapterlayoutBinding binding;
    Context mContext;
    ArrayList<SaloncatDTO> saloncatDTOS;
    LayoutInflater inflater;

    public Itemscatadapter(Context context, ArrayList<SaloncatDTO>list) {
        saloncatDTOS=list;
        mContext = context;
        inflater=LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public Itemscatadapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding= DataBindingUtil.inflate(inflater, R.layout.salonadapterlayout,parent,false);
        return new Itemscatadapter.VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Itemscatadapter.VH holder, final int position) {
        holder.binding.image.setImageResource(saloncatDTOS.get(position).getImage());
        holder.binding.title.setText(saloncatDTOS.get(position).getTitle());
        holder.binding.itemcatLL.setOnClickListener(new View.OnClickListener() {
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
    public void filterList(ArrayList<SaloncatDTO> filteredList) {
        saloncatDTOS = filteredList;
        notifyDataSetChanged();
    }


    public class VH extends RecyclerView.ViewHolder {
        SalonadapterlayoutBinding binding;
        public VH(@NonNull SalonadapterlayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}


