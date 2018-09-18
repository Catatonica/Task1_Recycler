package com.epam.havina.task1_recycler;

import android.os.Parcelable;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Is used for polymorphism in ItemListAdapter.
 *
 * @author Lizaveta Havina
 */
abstract class Item implements Parcelable {

    private final int type;

    @Type
    public int getType() {
        return type;
    }

    @IntDef({Type.CARD_VIEW, Type.NAME})
    @Retention(RetentionPolicy.SOURCE)
    protected @interface Type {

        int NAME = 123;
        int CARD_VIEW = 456;
    }

    Item(@Type final int type) {
        this.type = type;
    }
}
