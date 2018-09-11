package com.epam.havina.task1_recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemHolder> {

    private final int[] itemsList;
    private final ItemClickListener listener;

    RecyclerAdapter(@NonNull final ItemClickListener listener,
                    @NonNull final int[] itemsList) {
        this.listener = listener;
        this.itemsList = itemsList.clone();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        final RecyclerView.ViewHolder holder = new ItemHolder(view);
        view.setOnClickListener(v -> {
            final int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClicked(String.valueOf(itemsList[adapterPosition]));
            }
        });

        return (ItemHolder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {
        final int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition != RecyclerView.NO_POSITION) {
            holder.tvItemId.setText(String.valueOf(itemsList[adapterPosition]));
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.length;
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private final TextView tvItemId;

        ItemHolder(@NonNull final View itemView) {
            super(itemView);
            tvItemId = itemView.findViewById(R.id.tvItemId);
        }
    }

    interface ItemClickListener {
        void onItemClicked(@NonNull final String itemId);
    }
}
