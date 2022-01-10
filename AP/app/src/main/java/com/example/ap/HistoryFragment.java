package com.example.ap;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ap.api.models.Cake;
import com.example.ap.api.models.History;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class HistoryFragment extends Fragment {
    Context thiscontext;
    RecyclerView historyList;
    HistoryAdapter adapter;
    ArrayList<History> histories;
    HistoryFragmentArgs hargs;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext= container.getContext();
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        historyList = view.findViewById(R.id.historyList);

        historyList.setLayoutManager((new LinearLayoutManager(getContext())));
        historyList =(RecyclerView) view.findViewById(R.id.historyList);
        hargs = HistoryFragmentArgs.fromBundle(getArguments());
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Gson gson = new Gson();
        System.out.println(hargs.getHistories());
        Type listType = new TypeToken<ArrayList<History>>(){}.getType();
        histories = gson.fromJson(hargs.getHistories(),listType);
        System.out.println(histories.size());
        adapter = new HistoryAdapter(histories,(App) getActivity().getApplication());
        historyList.setAdapter(adapter);
    }
}