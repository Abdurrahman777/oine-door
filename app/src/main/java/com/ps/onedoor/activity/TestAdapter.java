package com.ps.onedoor.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ps.onedoor.R;
import com.ps.onedoor.databinding.TestadapterlayoutBinding;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    Context mContext;
    ArrayList<testDTO>testDTOS;
    LayoutInflater inflater;
    TestadapterlayoutBinding binding;
    public TestAdapter(Context context, ArrayList<testDTO> list)
    {
        mContext=context;
        testDTOS=list;
        inflater=LayoutInflater.from(mContext);

    }
    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding= DataBindingUtil.inflate(inflater, R.layout.testadapterlayout,parent,false);
        return  new TestAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.nameTV.setText(testDTOS.get(position).getName());
        holder.binding.ageTV.setText(testDTOS.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return testDTOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TestadapterlayoutBinding binding;
        public ViewHolder(@NonNull TestadapterlayoutBinding binding) {
            super(binding.getRoot());
            this.binding =binding;
        }
    }
}
