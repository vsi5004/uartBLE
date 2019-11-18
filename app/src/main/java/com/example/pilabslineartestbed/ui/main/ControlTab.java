package com.example.pilabslineartestbed.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;

import com.example.pilabslineartestbed.MainActivity;
import com.example.pilabslineartestbed.R;

public class ControlTab extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.control_tab, container, false);
        rootView.findViewById(R.id.btn_stop).setOnClickListener(this);
        rootView.findViewById(R.id.btn_1_left).setOnClickListener(this);
        rootView.findViewById(R.id.btn_10_left).setOnClickListener(this);
        rootView.findViewById(R.id.btn_100_left).setOnClickListener(this);
        rootView.findViewById(R.id.btn_home_left).setOnClickListener(this);
        rootView.findViewById(R.id.btn_1_right).setOnClickListener(this);
        rootView.findViewById(R.id.btn_10_right).setOnClickListener(this);
        rootView.findViewById(R.id.btn_100_right).setOnClickListener(this);
        rootView.findViewById(R.id.btn_home_right).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        MainActivity main = (MainActivity) view.getContext();
        switch (view.getId()) {
            case R.id.btn_stop:
                sendToTestbed(view, main, "stop");
                break;
            case R.id.btn_home_left:
                sendToTestbed(view, main, "homem");
                break;
            case R.id.btn_home_right:
                sendToTestbed(view, main, "homep");
                break;
            case R.id.btn_1_left:
                sendToTestbed(view, main, "movem:100");
                break;
            case R.id.btn_10_left:
                sendToTestbed(view, main, "movem:1000");
                break;
            case R.id.btn_100_left:
                sendToTestbed(view, main, "movem:10000");
                break;
            case R.id.btn_1_right:
                sendToTestbed(view, main, "movep:100");
                break;
            case R.id.btn_10_right:
                sendToTestbed(view, main, "movep:1000");
                break;
            case R.id.btn_100_right:
                sendToTestbed(view, main, "movep:10000");
                break;
            default:
                break;
        }
    }

    private void sendToTestbed(View view, MainActivity main, String command) {
        if (main.mState == main.UART_PROFILE_CONNECTED) {
            try {
                main.mService.writeRXCharacteristic((command).getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(view.getContext(), "Please connect to Testbed", Toast.LENGTH_SHORT).show();
        }
    }
}
