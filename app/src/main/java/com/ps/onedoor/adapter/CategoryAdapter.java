package com.ps.onedoor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ps.onedoor.DTO.CategoryAdapterDTO;
import com.ps.onedoor.R;
import com.ps.onedoor.databinding.CategoryadpterlayoutBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VH> {
    CategoryadpterlayoutBinding binding;
    Context mContext;
    ArrayList<CategoryAdapterDTO>categoryAdapterDTOS;
    LayoutInflater inflater;

    public CategoryAdapter( Context context,ArrayList<CategoryAdapterDTO>list) {
        categoryAdapterDTOS=list;
        mContext = context;
        inflater=LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding= DataBindingUtil.inflate(inflater, R.layout.categoryadpterlayout,parent,false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.VH holder,  final  int position) {
       holder.binding.itemImage.setImageResource(categoryAdapterDTOS.get(position).getImage());
       holder.binding.itemName.setText(categoryAdapterDTOS.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryAdapterDTOS.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        CategoryadpterlayoutBinding binding;
        public VH(@NonNull CategoryadpterlayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
