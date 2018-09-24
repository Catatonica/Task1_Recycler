package com.epam.havina.task1_recycler;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView with two types of view presentation
 *
 * @author Lizaveta Havina
 * @version 11.10.2018
 */
public class MainActivity extends AppCompatActivity implements ItemListAdapter.CardViewCallback {

    private static final String ITEM_LIST_KEY = "itemList";
    private List<Item> itemList;
    private RecyclerView recyclerView;

    @Override
    public void onCardClicked(@NonNull final String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onArrowClicked(final int position) {
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if (savedInstanceState == null || !savedInstanceState.containsKey(ITEM_LIST_KEY)) {
            itemList = new ItemListProvider().getItemList();
        } else {
            itemList = savedInstanceState.getParcelableArrayList(ITEM_LIST_KEY);
        }
        setRecyclerView();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ITEM_LIST_KEY, (ArrayList<? extends Parcelable>) itemList);
    }

    private void setRecyclerView() {
        final ItemListAdapter adapter = new ItemListAdapter(itemList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}
