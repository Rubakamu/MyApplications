package com.example.primegen.home;

import android.os.Parcel;
import android.os.Parcelable;

public class DashBoard implements Parcelable {

    private String cardName;
    private Integer image;

    public DashBoard() {

    }


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public static Creator<DashBoard> getCREATOR() {
        return CREATOR;
    }
    protected DashBoard(Parcel in) {
        cardName = in.readString();
        if (in.readByte() == 0) {
            image = null;
        } else {
            image = in.readInt();
        }
    }

    public static final Creator<DashBoard> CREATOR = new Creator<DashBoard>() {
        @Override
        public DashBoard createFromParcel(Parcel in) {
            return new DashBoard(in);
        }

        @Override
        public DashBoard[] newArray(int size) {
            return new DashBoard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cardName);
        if (image == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(image);
        }
    }
}
