package com.example.dianamoron.sqdm.view.ui;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dianamoron.sqdm.R;
import com.example.dianamoron.sqdm.service.model.User;

public class UserFragment extends Fragment {

    public static final String TAG = "UserFragment";
    private View view;
    private ImageView profileImage;
    private TextView name;
    private TextView email;
    private TextView id;

    private User user;


    public UserFragment() {

    }

    @SuppressLint("ValidFragment")
    public UserFragment(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);

        profileImage = (ImageView)view.findViewById(R.id.profileImage);
        name = (TextView)view.findViewById(R.id.name);
        email = (TextView)view.findViewById(R.id.email);
        id = (TextView)view.findViewById(R.id.id);

        name.setText(user.getName());
        email.setText(user.getEmail());
        Glide.with(view.getContext()).load(user.getImage()).into(profileImage);


        /*model.init(userID);

        model.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                //fragmentComicDetailsBinding.setComic(comic);
                name.setText(user.getName());
                email.setText(user.getEmail());
                Glide.with(view.getContext()).load(user.getImage()).into(profileImage);
            }
        });*/


        return view;
    }

}
