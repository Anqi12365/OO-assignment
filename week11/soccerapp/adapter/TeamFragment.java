package com.example.soccerapp.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.soccerapp.R;

public class TeamFragment extends Fragment {

    private TeamAdapter adapter;

    // 简单的创建方法，不通过Bundle传递adapter
    public static TeamFragment newInstance(TeamAdapter adapter) {
        TeamFragment fragment = new TeamFragment();
        fragment.adapter = adapter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 直接使用setAdapter
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}