package ru.amiralab.ecotales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FrDictionary extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ArrayList<Card> cards = new ArrayList<>();
        CustomClickListener customClickListener = itemId -> {
            Intent i = new Intent(getContext(), WordActivity.class);
            i.putExtra("id", cards.get(itemId).getId());
            i.putExtra("name", "dict");
            System.out.println(i.getExtras().get("id"));
            startActivity(i);
            // Toast.makeText(getContext(), cards.get(itemId).getEng(), Toast.LENGTH_LONG).show();
        };

        View view = inflater.inflate(R.layout.fragmentdictionary, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        RVAdapter adapter = new RVAdapter(new ArrayList<Card>(), customClickListener);
        recyclerView.setAdapter(adapter);
        db.collection("dict")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            cards.clear();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getId() + " => " + document.getData());
                                Object id = document.getData().get("id");
                                cards.add(new Card(
                                        id instanceof Long ? (Long) id : Long.parseLong((String) id),
                                        String.valueOf(document.getData().get("word")),
                                        String.valueOf(document.getData().get("translation")),
                                        String.valueOf(document.getData().get("context")),
                                        "", "", ""));
                            }
                            RVAdapter adapter = new RVAdapter(cards, customClickListener);
                            recyclerView.setAdapter(adapter);
                        } else {
                            System.out.println("Error getting documents.");
                        }
                    }
                });
        recyclerView.addOnItemTouchListener(
                new RecyclerView.SimpleOnItemTouchListener() {
                    @Override
                    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                    }
                }
        );
        return view;
    }
}
