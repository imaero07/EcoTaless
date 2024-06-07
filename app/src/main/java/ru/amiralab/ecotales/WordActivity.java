package ru.amiralab.ecotales;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class WordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        long id = getIntent().getExtras().getLong("id");
        String name = getIntent().getExtras().getString("name");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(name)
                .whereEqualTo("id", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getId() + " => " + document.getData());
                                Card card = new Card(
                                        (Long) document.getData().get("id"),
                                        String.valueOf(document.getData().get("word")),
                                        String.valueOf(document.getData().get("translation")),
                                        String.valueOf(document.getData().get("context")),
                                        String.valueOf(document.getData().get("photo")),
                                        String.valueOf(document.getData().get("video")),
                                        String.valueOf(document.getData().get("imagevideo")));

                                ((TextView) findViewById(R.id.TextView)).setText(card.getEng());
                                ((TextView) findViewById(R.id.textView2)).setText(card.getRus());
                                ((TextView) findViewById(R.id.textView3)).setText(card.getContext());
                                Picasso.get()
                                        .load(card.getImagePath())
                                        .resize(705, 800)
                                        .into((ImageView) findViewById(R.id.picture));

                                Picasso.get()
                                        .load(card.getImageVid())
                                        .resize(1150, 700)
                                        .into((ImageView) findViewById(R.id.VideoImage));

                                ImageButton videoImage = findViewById(R.id.VideoImage);
                                videoImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                                        httpIntent.setData(Uri.parse(card.getVideoPath()));
                                        startActivity(httpIntent);
                                    }
                                });

//                                Toast.makeText(getApplicationContext(), card.getContext(), Toast.LENGTH_LONG).show();

                                break;
                            }
                        } else {
                            System.out.println("Error getting documents.");
                        }
                    }
                });
    }
}