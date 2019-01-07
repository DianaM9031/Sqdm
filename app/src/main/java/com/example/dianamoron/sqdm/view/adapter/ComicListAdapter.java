package com.example.dianamoron.sqdm.view.adapter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dianamoron.sqdm.R;
//import com.example.dianamoron.sqdm.databinding.ComicBinding;
import com.example.dianamoron.sqdm.service.model.Comic;
import com.example.dianamoron.sqdm.service.model.ComicResponse;
import com.example.dianamoron.sqdm.view.callback.ComicClickCallback;
import com.example.dianamoron.sqdm.view.ui.ComicDetailsFragment;
import com.example.dianamoron.sqdm.view.ui.MainActivity;
import com.example.dianamoron.sqdm.viewmodel.ComicListViewModel;
import com.example.dianamoron.sqdm.viewmodel.ComicViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ComicListAdapter extends RecyclerView.Adapter<ComicListAdapter.ComicListViewHolder> {

    Context mCtx;
    List<Comic> heroList;

    public ComicListAdapter() {
    }

    public ComicListAdapter(Context mCtx, List<Comic> heroList) {
        this.mCtx = mCtx;
        this.heroList = heroList;

    }

    @NonNull
    @Override
    public ComicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ComicBinding comicBinding = DataBindingUtil.inflate(LayoutInflater.from(mCtx),R.layout.comic, parent, false);
        //return new ComicListViewHolder(comicBinding);
        View view = LayoutInflater.from(mCtx).inflate(R.layout.comic, parent, false);
        return new ComicListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicListViewHolder holder, int position) {
        final Comic comic = heroList.get(position);
        /*holder.bind(comic);

        final ComicBinding comicBinding = holder.getDataBinding();
        comicBinding.setCallback(new ComicClickCallback() {
            @Override
            public void onClick(Comic comic) {

                    Fragment fragment = new ComicDetailsFragment(comic.getId());
                        ((AppCompatActivity) mCtx)
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment).addToBackStack(null)
                            .commit();

            }
        });*/
        Glide.with(mCtx)
                .load(comic.getImage().getIcon_url())
                .into(holder.imageView);

        holder.textView.setText(comic.getName());
        holder.comic_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ComicDetailsFragment(comic.getId());
                ((AppCompatActivity) mCtx)
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment).addToBackStack(null)
                        .commit();
            }
        });
    }



    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class ComicListViewHolder extends RecyclerView.ViewHolder {

        //private ComicBinding comicBinding;

        ImageView imageView;
        TextView textView;
        LinearLayout comic_item;

        public ComicListViewHolder(View view) {
            super(view);

            imageView = (ImageView) itemView.findViewById(R.id.comic_image);
            textView = (TextView) itemView.findViewById(R.id.comic_name);
            comic_item =(LinearLayout) itemView.findViewById(R.id.comic_item);
        }

        /*public void bind(Comic comic){
            this.comicBinding.setComic(comic);

        }

        public ComicBinding getDataBinding(){
            return  comicBinding;
        }*/
    }
}


