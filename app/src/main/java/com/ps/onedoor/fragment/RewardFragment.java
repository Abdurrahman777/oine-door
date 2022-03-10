package com.ps.onedoor.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.onedoor.R;
import com.ps.onedoor.activity.LoginActivity;
import com.ps.onedoor.activity.TermsActivity;
import com.ps.onedoor.common.BaseActivity;
import com.ps.onedoor.databinding.FragmentRewardBinding;


public class RewardFragment extends Fragment {

    private FragmentRewardBinding binding;
    private View view;
    private Context mContext;
    /*BaseActivity baseActivity;*/
    public static RewardFragment rewardFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_reward, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reward, container, false);
      initvalues();
      ClickEvent();
        return view;

    }

    private void ClickEvent()
    {
/*
        binding.loginBT.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
*/
/*
              baseActivity.openActivity(LoginActivity.class);
*//*

     Intent intent=new Intent(RewardFragment.rewardFragment.getContext(),LoginActivity.class);
     startActivity(intent);
          }
      });
*/
      binding.termTV.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(mContext, TermsActivity.class);
              mContext.startActivity(intent);
          }
      });
    }

    private void initvalues() {
        rewardFragment=this;
        view=binding.getRoot();
    mContext=getContext();
    /*baseActivity=(BaseActivity) getActivity();*/
    }
}