package com.example.oliverchang.tigernewspaper4.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oliverchang.tigernewspaper4.Model.MyRecyclerAdapter;
import com.example.oliverchang.tigernewspaper4.R;

public class RecentFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private RecyclerView recentRecyclerView;
    private MyRecyclerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        drawerLayout = (DrawerLayout)container.findViewById(R.id.recent_drawer);
        recentRecyclerView = (RecyclerView)container.findViewById(R.id.recent_recycler);

        return inflater.inflate(R.layout.recent_fragment, container, false);
    }
}