package com.epam.havina.task1_recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.epam.havina.task1_recycler.Item.Type.CARD_VIEW;
import static com.epam.havina.task1_recycler.Item.Type.NAME;

/**
 * Fills up RecyclerView with items of two type.
 * <p>
 * BaseViewHolder class is used for polymorphism.
 *
 * @author Lizaveta Havina
 */
final class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.BaseViewHolder> {

    private final List<Item> itemList = new ArrayList<>();
    private final CardViewCallback callback;

    @Override
    public int getItemViewType(final int position) {
        return itemList.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder holder, final int position) {
        final int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition != RecyclerView.NO_POSITION) {
            final Item item = itemList.get(adapterPosition);
            holder.bindViews(item);
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        final BaseViewHolder holder;
        switch (viewType) {
            case NAME:
                final View nameView = inflateView(R.layout.item_name, viewGroup);
                holder = new NameViewHolder(nameView);
                break;
            case CARD_VIEW:
                final View cardView = inflateView(R.layout.item_card_view, viewGroup);
                holder = new CardViewHolder(cardView);
                cardView.setOnClickListener(v -> {
                    final int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        callback.onCardClicked(((CardItem) itemList.get(adapterPosition)).getTitle());
                    }
                });
                break;
            default:
                final View emptyView = inflateView(R.layout.item_empty, viewGroup);
                holder = new EmptyViewHolder(emptyView);
                break;
        }
        return holder;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    ItemListAdapter(@NonNull final Collection<Item> itemList, @NonNull final CardViewCallback callback) {
        this.itemList.addAll(itemList);
        this.callback = callback;
    }

    private View inflateView(final int id, @NonNull final ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(id, viewGroup, false);
    }

    /**
     * Is used for polymorphism.
     */
    abstract static class BaseViewHolder extends RecyclerView.ViewHolder {

        BaseViewHolder(@NonNull final View itemView) {
            super(itemView);
        }

        abstract void bindViews(final Item item);
    }

    private class CardViewHolder extends BaseViewHolder {

        private final TextView tvTitle;
        private final TextView tvDescription;
        private final ImageView ivArrow;

        CardViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivArrow = itemView.findViewById(R.id.ivArrow);
        }

        @Override
        public void bindViews(@NonNull final Item item) {
            final CardItem cardItem = (CardItem) item;
            tvTitle.setText(cardItem.getTitle());
            tvDescription.setText(cardItem.getDescription());
            ivArrow.setOnClickListener(iv -> callback.onArrowClicked(cardItem.getId()));
        }
    }

    private static class EmptyViewHolder extends BaseViewHolder {

        EmptyViewHolder(@NonNull final View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews(final Item item) {

        }
    }

    private static class NameViewHolder extends BaseViewHolder {

        private final TextView tvName;

        NameViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }

        @Override
        public void bindViews(@NonNull final Item item) {
            final NameItem nameItem = (NameItem) item;
            tvName.setText(String.format("%s %s", nameItem.getName(), nameItem.getSurname()));
        }
    }

    interface CardViewCallback {

        void onCardClicked(@NonNull final String name);

        void onArrowClicked(final int position);
    }
}
