package com.jl.servicedemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.yyxnb.system.permission.PermissionListener;
import com.yyxnb.system.permission.PermissionUtils;

import java.util.List;

/**
 * 开机启动服务demo
 */
public class MainActivity extends AppCompatActivity {


    private Button startButton = null;
    private Button stopButton = null;
    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取权限
        getPermissionMethod(this);
        startButton = (Button) findViewById(R.id.btn_hand_start);
        stopButton = (Button) findViewById(R.id.btn_stop);
        startButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Toast.makeText(view.getContext(),"start service",Toast.LENGTH_SHORT).show();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Toast.makeText(view.getContext(),"stop service",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取权限
    public void getPermissionMethod(Context context){
                PermissionUtils.with(this)
                .addPermissions(Manifest.permission.RECEIVE_BOOT_COMPLETED)
                        .addPermissions(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                        .setPermissionsCheckListener(new PermissionListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void permissionRequestSuccess() {
                                ignoreBatteryOptimization();
                            }

                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void permissionRequestFail(String[] strings, String[] strings1, String[] strings2) {
                                ignoreBatteryOptimization();
                            }
                        }).createConfig().buildConfig().startCheckPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void ignoreBatteryOptimization() {
        try{
            PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            boolean hasIgnored = powerManager.isIgnoringBatteryOptimizations(this.getPackageName());
            if(!hasIgnored) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:"+getPackageName()));
                startActivity(intent);
            }
        }catch(Exception e){
            //TODO :handle exception
        }
    }


}