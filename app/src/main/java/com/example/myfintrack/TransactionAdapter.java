package com.example.myfintrack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.Executors;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Transaction> transactionList;
    private Context context;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
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
        holder.transactionAmount.setText(String.valueOf(transaction.getAmount()));
        Log.d("TransactionAdapter", "Binding transaction: " + transaction);

        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditTransactionActivity.class);
            intent.putExtra("transaction_id", transaction.getId());
            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            deleteTransaction(transaction);
        });
    }

    @Override
    public int getItemCount() {
        return transactionList != null ? transactionList.size() : 0;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        notifyDataSetChanged();
    }

    private void deleteTransaction(Transaction transaction) {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase.getInstance(context.getApplicationContext()).transactionDao().delete(transaction);
            transactionList.remove(transaction);
            ((MainActivity) context).runOnUiThread(this::notifyDataSetChanged);
        });
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {

        TextView transactionDescription;
        TextView transactionAmount;
        Button editButton;
        Button deleteButton;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionDescription = itemView.findViewById(R.id.transactionDescription);
            transactionAmount = itemView.findViewById(R.id.transactionAmount);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
