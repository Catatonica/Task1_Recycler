package com.epam.havina.task1_recycler;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Data-class for item_name.xml
 *
 * @author Lizaveta Havina
 */
final class NameItem extends Item {

    private final String name;
    private final String surname;

    NameItem(@NonNull final String name, @NonNull final String surname) {
        super(Type.NAME);
        this.name = name;
        this.surname = surname;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeString(name);
        parcel.writeString(surname);
    }

    public static final Parcelable.Creator<NameItem> CREATOR = new Parcelable.Creator<NameItem>() {

        public NameItem createFromParcel(final Parcel in) {
            return new NameItem(in.readString(), in.readString());
        }

        public NameItem[] newArray(final int size) {
            return new NameItem[size];
        }
    };
}
