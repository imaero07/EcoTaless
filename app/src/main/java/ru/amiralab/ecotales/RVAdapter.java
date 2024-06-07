package ru.amiralab.ecotales;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private ArrayList<Card> cards;
    private final CustomClickListener listener;

    public RVAdapter(ArrayList<Card> cards, CustomClickListener listener) {
        this.cards = cards;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.wordTextView.setText(cards.get(position).getEng());
        viewHolder.translationTextView.setText(cards.get(position).getRus());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(viewHolder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordTextView;
        private final TextView translationTextView;
        private final View cardView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            wordTextView = (TextView) view.findViewById(R.id.word);
            translationTextView = (TextView) view.findViewById(R.id.translation);
            cardView = view.findViewById(R.id.card);
        }
    }
}
