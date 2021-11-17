package com.example.calculatorx.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextDirectionHeuristic;

import com.example.calculatorx.Theme;

public class ThemeStorage {

    private static final String ARG_THEME = "ARG_THEME";

    private Context context;

    private SharedPreferences sharedPreferences;

    public ThemeStorage(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("theme", Context.MODE_PRIVATE);
    }

    public void setTheme(Theme theme) {
        sharedPreferences.edit().putString(ARG_THEME, theme.getKey()).apply();
    }

    public Theme getTheme() {
        String key = sharedPreferences.getString(ARG_THEME, Theme.THEME_ONE.getKey());

        for (Theme theme: Theme.values()){
            if (theme.getKey().equals(key)) {
                return theme;
            }
        }
        return Theme.THEME_ONE;

    }
}
