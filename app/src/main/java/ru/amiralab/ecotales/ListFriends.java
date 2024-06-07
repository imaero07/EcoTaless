package ru.amiralab.ecotales;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ListFriends extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_friends);

        Intent Active = new Intent(this, MainActivity.class);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        RecyclerView recyclerViewF = findViewById(R.id.listFriends);

        db.collection("friends")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Card> cards = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getId() + " => " + document.getData());
                                cards.add(new Card(
                                        (Long) document.getData().get("id"),
                                        String.valueOf(document.getData().get("word")),
                                        String.valueOf(document.getData().get("translation")),
                                        String.valueOf(document.getData().get("context")),
                                        "", "", ""));
                            }

                            CustomClickListener customClickListener = itemId -> {
                                Intent i = new Intent(getApplicationContext(), WordActivity.class);
                                i.putExtra("id", cards.get(itemId).getId());
                                i.putExtra("name", "friends");
                                System.out.println(i.getExtras().get("id"));
                                startActivity(i);
                            };

                            RVAdapter adapter = new RVAdapter(cards, customClickListener);
                            recyclerViewF.setAdapter(adapter);
                        } else {
                            System.out.println("Error getting documents.");
                        }
                    }
                });

    }
}