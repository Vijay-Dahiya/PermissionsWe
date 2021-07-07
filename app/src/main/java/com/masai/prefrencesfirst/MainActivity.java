 package com.masai.prefrencesfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {
     private static final int REQUEST_CODE =0;
    private Button mBtnRequestPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnRequestPermission = findViewById(R.id.btnRequestPermission);

        mBtnRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions={Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(MainActivity.this,permissions,REQUEST_CODE);
            }
        });
    }

     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions,  int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
         if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
             showToast("Both the permissions are granted");
         }else if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_DENIED){
             showToast("Camera granted but storage denied");
         }else if(grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
             showToast("Camera denied but storage granted");
         }else if(grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_DENIED){
             showToast("Both the permission are denied");
             String[] permissions1={Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
             ActivityCompat.requestPermissions(MainActivity.this,permissions1,REQUEST_CODE);
         }
     }
     private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

     }
 }