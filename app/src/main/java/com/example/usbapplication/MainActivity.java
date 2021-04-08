package com.example.usbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.usbapplication.services.USBService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    USBService usbService;
    ArrayList<String> listInfo = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listInfo);
        list.setAdapter(adapter);

        usbService = new USBService(this);

    }

    public void addInfo(String str) {
        listInfo.add(str);
        adapter.notifyDataSetChanged();
    }
}