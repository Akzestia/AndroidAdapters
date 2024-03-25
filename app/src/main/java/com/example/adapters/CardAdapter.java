package com.example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<Card> cards;

    public CardAdapter(List<Card> data) {
        this.cards = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = cards.get(position);

        holder.cardNumber.setText("**** **** **** " + card.number.substring(card.number.length()-4));
        holder.cardBalance.setText("$" + String.format("%.2f", card.balance).replace('.', ','));

        if(card.type.equals("Visa")){
            holder.imgView.setImageResource(R.drawable.visa);
        }
        else{
            holder.imgView.setImageResource(R.drawable.master);
        }

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardNumber;
        TextView cardBalance;
        ImageView imgView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardNumber = itemView.findViewById(R.id.cardNumber);
            cardBalance = itemView.findViewById(R.id.cardBalance);
            imgView = itemView.findViewById(R.id.typeHolder);
        }
    }
}
