package com.example.projetmobile.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.projetmobile.R;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View fragmentFistLayout = inflater.inflate(R.layout.fragment_first, container, false);
        //return inflater.inflate(R.layout.fragment_first, container, false);
        // Get the count text view
        TextView showCountTextView = fragmentFistLayout.findViewById(R.id.TextView_first);
        return fragmentFistLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            }
        });

        view.findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });
    }
}
