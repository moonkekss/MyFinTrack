package com.example.myfintrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private ArrayList<Transaction> transactionList;

    public TransactionAdapter(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.transactionDescription.setText(transaction.getDescription());
        holder.transactionAmount.setText(transaction.getAmount());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {

        TextView transactionDescription;
        TextView transactionAmount;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionDescription = itemView.findViewById(R.id.transactionDescription);
            transactionAmount = itemView.findViewById(R.id.transactionAmount);
        }
    }
}
