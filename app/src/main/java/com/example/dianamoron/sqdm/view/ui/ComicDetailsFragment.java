package com.example.dianamoron.sqdm.view.ui;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dianamoron.sqdm.R;
import com.example.dianamoron.sqdm.service.model.Comic;
import com.example.dianamoron.sqdm.viewmodel.ComicViewModel;
//import com.example.dianamoron.sqdm.databinding.FragmentComicDetailsBinding;


public class ComicDetailsFragment extends Fragment {

    public static final String TAG = "ComicListFragment";
    public static final String KEY_COMIC_ID = "ComicID";
    private int comicID;


    //private FragmentComicDetailsBinding fragmentComicDetailsBinding;
    private View view;
    private ImageView comic_image;
    private TextView comic_name;
    private TextView comic_description;

    public ComicDetailsFragment() {
    }

    @SuppressLint("ValidFragment")
    public ComicDetailsFragment(int comicID) {
        this.comicID = comicID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*fragmentComicDetailsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_comic_details, container, false);*/
        view = inflater.inflate(R.layout.fragment_comic_details, container, false);

        comic_image = (ImageView)view.findViewById(R.id.comic_image);
        comic_name = (TextView)view.findViewById(R.id.comic_name);
        comic_description = (TextView)view.findViewById(R.id.comic_description);

        ComicViewModel model = ViewModelProviders.of(this).get(ComicViewModel.class);
        model.init(comicID);

        model.getComicDetails().observe(this, new Observer<Comic>() {
            @Override
            public void onChanged(@Nullable Comic comic) {
                //fragmentComicDetailsBinding.setComic(comic);
                comic_name.setText(comic.getName());
                comic_description.setText(comic.getDescription());
                Glide.with(view.getContext())
                        .load(comic.getImage().getIcon_url())
                        .into(comic_image);
            }
        });


        return view;
        //return fragmentComicDetailsBinding.getRoot();
    }

}
