package com.example.androidlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RecepiFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recepi, container, false);

        ImageView drinksimage, riceimage, vegieimage, westernfoodimage, cookiesimage;

        drinksimage = view.findViewById(R.id.sectiondrinks);
        riceimage = view.findViewById(R.id.sectionrice);
        vegieimage = view.findViewById(R.id.sectionvegie);
        westernfoodimage = view.findViewById(R.id.sectionwasternfood);
        cookiesimage = view.findViewById(R.id.sectioncookies);

        //bila user tekan gambar drink
        drinksimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), drinksrecepi.class));

            }
        });

        //bila user tekan gambar rice
        riceimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ricerecepi.class));

            }
        });

        //bila user tekan vegieimage
        vegieimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), vegierecepi.class));

            }
        });

        //bila user tekan westernfoodimage
        westernfoodimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), wasternfoodrecepi.class));

            }
        });

        //bila user tekan cookiesimage
        cookiesimage .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), cookiesrecepi.class));

            }
        });
        return view;
    }
}

