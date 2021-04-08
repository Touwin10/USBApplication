package com.example.usbapplication.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;

import com.example.usbapplication.MainActivity;


public class USBService {
    MainActivity mContext;
    private BroadcastReceiver receiver;
    private IntentFilter intentFilter;
    public static final String ACTION_USB_STATE = "android.hardware.usb.action.USB_STATE";

    public USBService(MainActivity context) {
        this.mContext = context;

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                MainActivity mainActivity = (MainActivity) context;

                if (action == USBService.ACTION_USB_STATE) {
                    boolean connected = intent.getBooleanExtra("connected", false);
                    mainActivity.addInfo("Connection State : " + (connected ? "Connected" : "Not Connected"));
                    boolean mtpState = intent.getBooleanExtra("mtp", false);
                    mainActivity.addInfo("MTP State : " + (mtpState ? "Available" : "Unavailable"));
                    boolean ptpState = intent.getBooleanExtra("ptp", false);
                    mainActivity.addInfo("PTP State : " + (ptpState ? "Available" : "Unavailable"));
                    boolean audioState = intent.getBooleanExtra("audio_source", false);
                    mainActivity.addInfo("Audio State : " + (audioState ? "Available" : "Unavailable"));
                }

            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        intentFilter.addAction(USBService.ACTION_USB_STATE);

        this.mContext.registerReceiver(receiver, intentFilter);
    }

}
