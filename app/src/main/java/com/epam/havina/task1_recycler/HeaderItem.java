package com.epam.havina.task1_recycler;

import android.os.Parcel;

/**
 * Data-class for item_header.xml.
 * Is used to show number of clicks on CardView.
 *
 * @author Lizaveta Havina
 */
final class HeaderItem extends Item {

    public static final Creator<HeaderItem> CREATOR = new Creator<HeaderItem>() {

        public HeaderItem createFromParcel(final Parcel in) {
            final HeaderItem headerItem = new HeaderItem();
            headerItem.header = in.readString();
            return headerItem;
        }

        public HeaderItem[] newArray(final int size) {
            return new HeaderItem[size];
        }
    };
    private static final String MESSAGE = "Clicked items: ";
    private String header = MESSAGE + 0;

    HeaderItem() {
        super(Type.HEADER);
    }

    public void setHeader(final int clicks) {
        this.header = MESSAGE + clicks;
    }

    public String getHeader() {
        return header;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeString(header);
    }
}
