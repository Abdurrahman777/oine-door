package com.ps.onedoor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ps.onedoor.DTO.FCM_UserDTO;
import com.ps.onedoor.MainActivity;
import com.ps.onedoor.R;
import com.ps.onedoor.common.BaseActivity;
import com.ps.onedoor.databinding.ActivitySignUpBinding;
import com.ps.onedoor.session.SessionManager;

import java.util.TimeZone;

public class SignUpActivity extends BaseActivity {
   Context mContext;
   ActivitySignUpBinding binding;
   SessionManager sessionManager;
    private FirebaseAuth auth;
    private DatabaseReference dbFirebase;
    private String TAG="SignUpActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
    binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
    initvalues();
    ClickEvent();
    }
    private void initvalues()
    {
        mContext=this;
        sessionManager=new SessionManager(mContext);
        auth = FirebaseAuth.getInstance();
    }
    private void ClickEvent()
    {
     binding.submitBT.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             /*openActivity(MainActivity.class);*/
             /*Validation();*/
             checkValidation();
         }
     });
    }
    private void checkValidation() {
        final String name = binding.nameET.getText().toString().trim();
        String email = binding.emailET.getText().toString().trim();
        String mobile = binding.numberET.getText().toString().trim();
        String password = binding.passwordET.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(SignUpActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(SignUpActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(SignUpActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(SignUpActivity.this, "Password Enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
        } else {

            signInFirebase(binding.emailET.getText().toString(), binding.passwordET.getText().toString());


        }
    }

/*
    public void Validation() {
        if (isETEmpty(binding.numberET, 1)) {
            customToast("Enter Email", 200, true, null);
        } else if (isETEmpty(binding.passwordET, 1)) {
            customToast("Enter Password", 200, true, null);
        } else if (isETEmpty(binding.passwordET, 4)) {
            customToast("Min 4 digit", 200, true, null);
        } else {
//            customToast("Success", 0, false, binding.linearLogin);
            signInFirebase(binding.emailET.getText().toString(), binding.passwordET.getText().toString());
        }
    }
*/
    private void signUpUsingFirebase(final String vEmail, final String vPassword, final FCM_UserDTO fcm_userDTO) {

        auth.createUserWithEmailAndPassword(vEmail, vPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                /*binding.progressBar.setVisibility(View.GONE);*/
                if (!task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    String vUid = auth.getUid();
                    SplashActivity.fcmUserDTO = new FCM_UserDTO();
                    SplashActivity.fcmUserDTO.setUser_id(vUid);
                    SplashActivity.fcmUserDTO.setUsername(fcm_userDTO.getUsername());
                    SplashActivity.fcmUserDTO.setEmail(fcm_userDTO.getEmail());
                    SplashActivity.fcmUserDTO.setDevice_token("");
                    SplashActivity.fcmUserDTO.setDevice_type("1");
                    SplashActivity.fcmUserDTO.setImage("");
                    SplashActivity.fcmUserDTO.setTimeZone(TimeZone.getDefault().getID());
                    dbFirebase = FirebaseDatabase.getInstance().getReference("user");
                    dbFirebase.child(vUid).setValue(SplashActivity.fcmUserDTO);
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    finish();
                }

            }

        });
    }
    private void signInFirebase(final String vEmail, final String vPassword/*, final String vUserType,
                                final FCM_UserDTO _fcmUserDTO*/) {
        Log.d(TAG, "signInFirebase");
        auth.signInWithEmailAndPassword(vEmail, vPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(mContext, "User already registered", Toast.LENGTH_SHORT).show();
                        } else {
                            FCM_UserDTO fcmUserDTO = new FCM_UserDTO(
                                    "", binding.emailET.getText().toString().trim(),
                                    binding.nameET.getText().toString().trim(),
                                    TimeZone.getDefault().getID(),
                                    "", "",
                                    "1", "abc12345"
                            );
                            signUpUsingFirebase(vEmail, vPassword, fcmUserDTO);
                        }


                    }

                });
    }



}