package com.epam.havina.task1_recycler;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generates random CardItems and NameItems and returns them composed in List,
 * which can then be used in ItemListAdapter.
 *
 * @author Lizaveta Havina
 */
final class ItemListProvider {

    private final List<Item> itemList = new ArrayList<>();
    private final String[] randomStringParts = {
            "abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz"
    };
    private final StringBuilder descBuilder = new StringBuilder();

    @NonNull
    List<Item> getItemList() {
        if (itemList.isEmpty()) {
            final int ITEMS_NUMBER = 30;
            for (int i = 0; i < ITEMS_NUMBER; i++) {
                descBuilder.append(i).append(", ");
                if (i % 4 == 0) {
                    itemList.add(getRandomNameItem());
                    continue;
                }
                itemList.add(getRandomCardItem(i));
            }
        }
        return itemList;
    }

    @NonNull
    private CardItem getRandomCardItem(final int index) {
        return new CardItem(index, getRandomString(), getDescription());
    }

    @NonNull
    private NameItem getRandomNameItem() {
        return new NameItem(getRandomString(), getRandomString());
    }

    @NonNull
    private String getDescription() {
        final StringBuilder builder = new StringBuilder(descBuilder);
        final int builderLength = builder.length();
        builder.delete(builderLength - 2, builderLength);
        return builder.toString();
    }

    @NonNull
    private String getRandomString() {
        final Random random = new Random();
        return randomStringParts[random.nextInt(randomStringParts.length)]
                + (randomStringParts[random.nextInt(randomStringParts.length)]);
    }
}
