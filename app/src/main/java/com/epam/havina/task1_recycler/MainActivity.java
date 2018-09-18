package com.epam.havina.task1_recycler;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

/**
 * RecyclerView with items' callback presentation
 *
 * @author Lizaveta Havina
 * @version 11.10.2018
 */
public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ItemClickListener {

    private static final int[] itemsArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();
    }

    @Override
    public void onItemClicked(@NonNull final String itemId) {
        Toast.makeText(this, itemId + getString(R.string.toast_message), Toast.LENGTH_SHORT).show();
    }

    private void setRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RecyclerAdapter(this, itemsArray));
    }
}
