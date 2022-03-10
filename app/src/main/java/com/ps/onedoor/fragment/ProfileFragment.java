package com.ps.onedoor.fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.ps.onedoor.R;
import com.ps.onedoor.activity.AboutCompanyActivity;
import com.ps.onedoor.activity.LoginActivity;
import com.ps.onedoor.databinding.FragmentProfileBinding;
import com.ps.onedoor.session.SessionManager;
import java.io.File;
public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
     private  View view;
    Context mContext;
    SessionManager sessionManager;
    public static ProfileFragment profileFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_profile, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
      view=binding.getRoot();
      initvalues();
      ClickEvent();
      return  view;
    }
    private void initvalues()
    {
      mContext=getContext();
      profileFragment=this;
      sessionManager=new SessionManager(mContext);
    }
    private void ClickEvent()
    {
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showlogout();
            }
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "shared Succesful", Toast.LENGTH_SHORT).show();
                ApplicationInfo api=getContext().getApplicationInfo();
                String apkpath=api.sourceDir;
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
                startActivity(Intent.createChooser(intent,"ShareVia"));
            }
        });

        binding.aboutcTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent (mContext, AboutCompanyActivity.class);
                mContext.startActivity(intent);
            }
        });

    }
    private void Showlogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.logout))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sessionManager.setlogin(false);
                        startActivity(new Intent(mContext, LoginActivity.class)
                                .putExtra("value", true));
/*closeToBottomAnimation();*/

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }





}