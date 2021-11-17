package com.example.calculatorx;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

public enum  Theme {

    THEME_ONE(R.string.dark_theme, R.drawable.ic_circle, R.style.DarkTheme, "dark"),
    THEME_TWO(R.string.light_theme, R.drawable.ic_circle, R.style.LightTheme, "light");


    @StringRes
    private int title;
    @DrawableRes
    private int img;
    @StyleRes
    private int theme;

    private final String key;

    private Theme(int title, int img, int theme, String key) {
        this.title = title;
        this.img = img;
        this.theme = theme;
        this.key = key;
    }

    //
//    public static final Creator<Theme> CREATOR = new Creator<Theme>() {
//        @Override
//        public Theme createFromParcel(Parcel in) {
//            return new Theme(in);
//        }
//
//        @Override
//        public Theme[] newArray(int size) {
//            return new Theme[size];
//        }
//    };
//
//
    public int getTitle() {
        return title;
    }

    public int getImg() {
        return img;
    }

    public int getTheme() {
        return theme;
    }

    public String getKey() {
        return key;
    }

    //
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(title);
//        dest.writeInt(img);
//        dest.writeInt(theme);
//    }
//    }
}
