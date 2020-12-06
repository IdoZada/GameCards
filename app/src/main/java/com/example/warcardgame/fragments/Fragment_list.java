package com.example.warcardgame.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warcardgame.R;


public class Fragment_list extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        //list_LBL_title = view.findViewById(R.id.list_LBL_title);
    }
}
