package ru.amiralab.ecotales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FrTV extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenttv, container, false);


        ImageButton listFriendss = view.findViewById(R.id.mult1);
        ImageButton listThingss = view.findViewById(R.id.mult3);
        ImageButton listReasonss = view.findViewById(R.id.mult2);

        Intent FRiends = new Intent(getContext(), ListFriends.class);
        Intent SThings = new Intent(getContext(), ListThings.class);
        Intent RWhy = new Intent(getContext(), ListReasons.class);

        listFriendss.setOnClickListener(v -> startActivity(FRiends));
        listThingss.setOnClickListener(v -> startActivity(SThings));
        listReasonss.setOnClickListener(v -> startActivity(RWhy));



        return view;
    }

    

}
