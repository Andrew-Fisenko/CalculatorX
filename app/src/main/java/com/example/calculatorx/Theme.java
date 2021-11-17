package com.example.calculatorx;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

public class Theme implements Parcelable {
    @StringRes
    private int title;
    @DrawableRes
    private int img;
    @StyleRes
    private int theme;


    public Theme(int title, int img, int theme) {
        this.title = title;
        this.img = img;
        this.theme = theme;
    }

    protected Theme(Parcel in) {
        title = in.readInt();
        img = in.readInt();
        theme = in.readInt();
    }

    public static final Creator<Theme> CREATOR = new Creator<Theme>() {
        @Override
        public Theme createFromParcel(Parcel in) {
            return new Theme(in);
        }

        @Override
        public Theme[] newArray(int size) {
            return new Theme[size];
        }
    };

    public int getTitle() {
        return title;
    }

    public int getImg() {
        return img;
    }

    public int getTheme() {
        return theme;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(title);
        dest.writeInt(img);
        dest.writeInt(theme);
    }
}
