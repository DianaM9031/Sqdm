package com.example.dianamoron.sqdm.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dianamoron.sqdm.R;

import com.example.dianamoron.sqdm.service.model.Comic;
import com.example.dianamoron.sqdm.service.model.ComicResponse;
import com.example.dianamoron.sqdm.view.adapter.ComicListAdapter;
import com.example.dianamoron.sqdm.view.callback.ComicClickCallback;
import com.example.dianamoron.sqdm.viewmodel.ComicListViewModel;

//import com.example.dianamoron.sqdm.databinding.FragmentComicListBinding;

import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ComicListFragment extends Fragment {

    public static final String TAG = "ComicListFragment";

    //private FragmentComicListBinding fragmentComicListBinding;
    private View view;
    private RecyclerView recyclerView;
    private ComicListAdapter adapter;
    private ComicListViewModel comicListViewModel;

    public ComicListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //fragmentComicListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_comic_list, container, false);
        view = inflater.inflate(R.layout.fragment_comic_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.comic_list);

        comicListViewModel = ViewModelProviders.of(this).get(ComicListViewModel.class);

        if(isNetworkConnected()) {
            comicListViewModel.insertComics();
        } else {
            Log.e(TAG,"No hay conexion a internet");
        }

        comicListViewModel.getComics().observe(this, new Observer<List<Comic>>() {
            @Override
            public void onChanged(@Nullable List<Comic> heroList) {
                adapter = new ComicListAdapter(getContext(), heroList);
                //fragmentComicListBinding.comicList.setAdapter(adapter);
                recyclerView.setAdapter(adapter);
            }
        });

        //return fragmentComicListBinding.getRoot();
        return view;
    }

    protected boolean isNetworkConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return (mNetworkInfo == null) ? false : true;

        }catch (NullPointerException e){
            return false;

        }
    }

}

