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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ps.onedoor.DTO.FCM_UserDTO;
import com.ps.onedoor.MainActivity;
import com.ps.onedoor.R;
import com.ps.onedoor.common.BaseActivity;
import com.ps.onedoor.databinding.ActivityLoginBinding;
import com.ps.onedoor.session.SessionManager;

public class LoginActivity extends BaseActivity {
    private  String TAG="LoginActivity";
    ActivityLoginBinding binding;
    private FirebaseAuth auth;
    SessionManager sessionManager;
    private Context mContext;
    private FirebaseStorage storage;
    private DatabaseReference dbFirebase;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_login);
    binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
    initvalues();
    ClickEvent();
    }


    private void initvalues()
    {
    mContext=this;
    sessionManager=new SessionManager(mContext);
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

    }
    
    private void ClickEvent() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*openActivity(MainActivity.class);*/
                checkValidation();
            }
        });
        binding.signupTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(SignUpActivity.class);
            }
        });
    }
    private void checkValidation() {

        String email = binding.emailET.getText().toString();
        final String password = binding.passwordET.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else {

            /*binding.progressBar.setVisibility(View.VISIBLE);*/

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    /*binding.progressBar.setVisibility(View.GONE);*/

                   /* if (!task.isSuccessful()) {
                        if (password.length() < 6) {
                            binding.passwordET.setError(getString(R.string.password));
                        } else {
                            alertDialogDialog();
                        }
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        sessionManager.login();
                        startActivity(intent);
                        finish();
                    }*/
                    FCM_UserDTO fcmUserDTO = new FCM_UserDTO("01", binding.emailET.getText().toString(), "Vasu",
                            "", "1", "", "1", "abc12345"
                    );
                    signInFirebase(binding.emailET.getText().toString(), binding.passwordET.getText().toString());

                }
            });

        }

    }

/*
    public void validation() {
        String email = binding.emailET.getText().toString();
        final String password = binding.passwordET.getText().toString();

        if (isETEmpty(binding.emailET, 1)) {
            customToast("Enter Email", 200, true, null);
        } else if (isETEmpty(binding.passwordET, 1)) {
            customToast("Enter Password", 200, true, null);
        } else if (isETEmpty(binding.passwordET, 4)) {
            customToast("Min 4 digit", 200, true, null);
        } else {
//            customToast("Success", 0, false, binding.linearLogin);
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    */
/*binding.progressBar.setVisibility(View.GONE);*//*


                   */
/* if (!task.isSuccessful()) {
                        if (password.length() < 6) {
                            binding.passwordET.setError(getString(R.string.password));
                        } else {
                            alertDialogDialog();
                        }
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        sessionManager.login();
                        startActivity(intent);
                        finish();
                    }*//*

                    FCM_UserDTO fcmUserDTO = new FCM_UserDTO("01", binding.emailET.getText().toString(), "abdur",
                            "", "1", "", "1", "abc12345"
                    );
                    signInFirebase(binding.emailET.getText().toString(), binding.passwordET.getText().toString());


                }
            });
        }
    }
*/
    private void signInFirebase(final String vEmail, final String vPassword/*, final String vUserType,
                                final FCM_UserDTO _fcmUserDTO*/) {
        Log.d(TAG, "signInFirebase");
        auth.signInWithEmailAndPassword(vEmail, vPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "===INSIDE===");
                            final FirebaseUser user = auth.getCurrentUser();
                            dbFirebase = FirebaseDatabase.getInstance().getReference("user");
                            dbFirebase.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    try {
                                        Log.d(TAG, "===INSIDE CHILD===");
                                        FCM_UserDTO fcm_userDTO = dataSnapshot.getValue(FCM_UserDTO.class);
                                        fcm_userDTO.setUser_id(auth.getUid());
                                        dbFirebase.child(fcm_userDTO.getUser_id()).setValue(fcm_userDTO);
                                        sessionManager.setUserId(fcm_userDTO.getUser_id());
                                        sessionManager.login();
                                      /*  Intent intent =new Intent(mContext,MainActivity.class);
                                      startActivity(intent);*/
                                        openActivity(MainActivity.class);
//                                        startActivity(new Intent(mContext, MainActivity.class));
                                        /*intent.putExtra("name",saloncatDTOS.get(position).getTitle());*/
                                    } catch (Exception e) {
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
//                                    dialogProgress.dismiss();
                                    Log.d(TAG, "===INSIDE CHILD ERROR===");
                                    Log.d(TAG, "@@firebase db@@" + databaseError.getDetails() + "\n message" + databaseError.getMessage());
                                    Toast.makeText(mContext, "Password Not Matched.", Toast.LENGTH_SHORT).show();
                                }
                            });


                        } else {
                            Toast.makeText(mContext, "Register First", Toast.LENGTH_SHORT).show();
                        }


                    }

                });
    }
    public void onBackPressed() {
        int backpress = 0;
        backpress = backpress + 1;
        /*   Toast.makeText(getApplicationContext(), "  ", Toast.LENGTH_SHORT).show();*/

        if (backpress < 1) {
            this.finish();
        }
    }
}