package com.exercicio05;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SecretoAdapter extends RecyclerView.Adapter<SecretoAdapter.SecretoViewHolder> {

    private List<Secreto> secretos;

    public SecretoAdapter(List<Secreto> secretos) {
        this.secretos = secretos;
    }

    @NonNull
    @Override
    public SecretoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_secreto, parent, false);
        return new SecretoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecretoViewHolder holder, int position) {
        Secreto secreto = secretos.get(position);
        holder.guessTextView.setText(secreto.getGuess());
        holder.feedbackTextView.setText(secreto.getFeedback());
    }

    @Override
    public int getItemCount() {
        return secretos.size();
    }

    static class SecretoViewHolder extends RecyclerView.ViewHolder {
        TextView guessTextView;
        TextView feedbackTextView;

        public SecretoViewHolder(@NonNull View itemView) {
            super(itemView);
            guessTextView = itemView.findViewById(R.id.guessTextView);
            feedbackTextView = itemView.findViewById(R.id.feedbackTextView);
        }
    }
}
