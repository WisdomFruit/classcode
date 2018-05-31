package com.jumpdigital.nico.classcode.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.jumpdigital.nico.classcode.R;
import com.jumpdigital.nico.classcode.activity.LoginActivity;
import com.jumpdigital.nico.classcode.helper.Helper;
import com.jumpdigital.nico.classcode.manager.DataManager;
import com.jumpdigital.nico.classcode.model.AttendanceResult;
import com.jumpdigital.nico.classcode.retrofit.Interface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Nico on 21/05/2018.
 */

public class ScannerFragment extends Fragment {

    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String formattedDate = df.format(c);

    String qr_code = "Lucky";

    private String attendance;
    Helper helper;
    private CodeScanner mCodeScanner;
    private boolean isPresent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        helper = new Helper(getContext());
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.fragment_scanner, container, false);
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String res = result.toString();
//                        Toast.makeText(activity, res, Toast.LENGTH_SHORT).show();
                        if (res.equals("BAT")){
                            loadAttendance();
                        }
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    public void loadAttendance() {
        LoginActivity la = new LoginActivity();
        HashMap<String, String> map = new HashMap<>();
        map.put("studentnum",la.SN);
        map.put("date", formattedDate.toString());
        map.put("attendance", String.valueOf(isPresent));
        DataManager.getmInstance(getActivity()).postAttendance(map, new Interface.postAttendanceCallback() {
            @Override
            public void onResponse(boolean isSuccess, AttendanceResult response, String message) {
                if (isSuccess) {
                    //helper.setAttendance(true);
                    Toast.makeText(getActivity(), "Attendance Checked", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(), "Status: " + message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
