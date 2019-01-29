package com.k4dima.features;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PackageManager packageManager = getPackageManager();
        Map<String, Integer> map = new HashMap<>();
        packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
                .stream()
                .map(applicationInfo -> applicationInfo.packageName)
                .forEach(packageName -> {
                    String name = packageManager.getInstallerPackageName(packageName);
                    Integer number = map.get(name);
                    map.put(name, number == null ? 0 : ++number);
                    ((TextView) findViewById(R.id.text))
                            .append(packageName + "\n");
                });
        ((TextView) findViewById(R.id.text))
                .append(map.toString());
    }
}