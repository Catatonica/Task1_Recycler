package com.epam.havina.task1_recycler;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Data-class for item_card_view.xml
 *
 * @author Lizaveta Havina
 */
final class CardItem extends Item {

    public static final Parcelable.Creator<CardItem> CREATOR = new Parcelable.Creator<CardItem>() {

        public CardItem createFromParcel(final Parcel in) {
            return new CardItem(in.readInt(), in.readString(), in.readString());
        }

        public CardItem[] newArray(final int size) {
            return new CardItem[size];
        }
    };
    private static int clicks;
    private final int id;
    private final String title;
    private final String description;
    private boolean clicked;

    CardItem(final int id, @NonNull final String title, @NonNull final String description) {
        super(Type.CARD_VIEW);
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public static void setClicks(final int clicks) {
        CardItem.clicks = clicks;
    }

    public static int getClicks() {
        return clicks;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(final boolean clicked) {
        this.clicked = clicked;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
    }
}
