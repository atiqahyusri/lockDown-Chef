package com.example.androidlogin;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GroceryFragment extends Fragment {
    private Button btningredient;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragement_grocery, container, false);

        Button btningredient = (Button) view.findViewById(R.id.btningredient);
        btningredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), RecipeIngredient.class);
                in.putExtra("some", "some");
                startActivity(in);
            }
        });
        return view;

    }


}

