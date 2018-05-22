package com.jumpdigital.nico.classcode.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jumpdigital.nico.classcode.Item.Record;
import com.jumpdigital.nico.classcode.R;
import com.jumpdigital.nico.classcode.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico on 21/05/2018.
 */

public class RecordFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<Record> recordList;

    public RecordFragment() {

    }

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_record, container, false);
        recyclerView = view.findViewById(R.id.record_recycler_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), recordList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordList = new ArrayList<>();
        recordList.add(new Record("05-21-2018","Monday",R.drawable.ic_absent));
        recordList.add(new Record("05-22-2018","Tuesday",R.drawable.ic_present));
        recordList.add(new Record("05-23-2018","Wednesday",R.drawable.ic_present));
        recordList.add(new Record("05-24-2018","Thursday",R.drawable.ic_absent));
        recordList.add(new Record("05-25-2018","Friday",R.drawable.ic_present));
        recordList.add(new Record("05-26-2018","Saturday",R.drawable.ic_present));
        recordList.add(new Record("05-27-2018","Sunday",R.drawable.ic_present));
        recordList.add(new Record("05-28-2018","Monday",R.drawable.ic_present));
    }
}
