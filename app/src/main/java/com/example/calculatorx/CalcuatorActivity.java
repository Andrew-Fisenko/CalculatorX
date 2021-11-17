package com.example.calculatorx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calculatorx.storage.ThemeStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class CalcuatorActivity extends AppCompatActivity {

    private TextView txtResult;
    private TextView txtNumberOne;
    private TextView txtNumberTwo;
    private TextView txtOperation;

    private CalculatorPresenter presenter;
   

    private LinearLayout container;

    private static final String ARG_THEME = "ARG_THEME";
    private Theme selectedTheme;

    private ThemeStorage storage;


 

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("numOne", presenter.getNumOne());
        savedInstanceState.putDouble("numTwo", presenter.getNumTwo());
        savedInstanceState.putString("oper", presenter.getOper());
        savedInstanceState.putDouble("result", presenter.getResult());
        if (selectedTheme != null) {
            savedInstanceState.putSerializable(ARG_THEME, selectedTheme);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);

        setTheme(storage.getTheme().getTheme());

//        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_THEME)) {
//            Theme selectedTheme = (Theme) savedInstanceState.getSerializable(ARG_THEME);
//            setTheme(selectedTheme.getTheme());
//        }

        setContentView(R.layout.activity_calculator);

        txtResult = findViewById(R.id.txt_result);
        txtNumberOne = findViewById(R.id.number_one);
        txtNumberTwo = findViewById(R.id.number_two);
        txtOperation = findViewById(R.id.operation);

        CalcuatorActivity calculatorView = this;

        presenter = new CalculatorPresenter();

            if (savedInstanceState != null) {
            presenter.setNumOne(savedInstanceState.getDouble("numOne"));
            showNumOne(presenter.getNumOne());
            presenter.setNumTwo(savedInstanceState.getDouble("numTwo"));
            showNumTwo(presenter.getNumTwo());
            presenter.setOper(savedInstanceState.getString("oper"));
            showOperation(presenter.getOper());
            presenter.setResult(savedInstanceState.getDouble("result"));
            showResult(presenter.getResult());
        }


        Map<Integer, Integer> numbers = new HashMap<>();
        numbers.put(R.id.but_0, 0);
        numbers.put(R.id.but_1, 1);
        numbers.put(R.id.but_2, 2);
        numbers.put(R.id.but_3, 3);
        numbers.put(R.id.but_4, 4);
        numbers.put(R.id.but_5, 5);
        numbers.put(R.id.but_6, 6);
        numbers.put(R.id.but_7, 7);
        numbers.put(R.id.but_8, 8);
        numbers.put(R.id.but_9, 9);

        View.OnClickListener numbersClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x = presenter.onNumberPressed(numbers.get(v.getId()), presenter.getOper(),
                        presenter.getResult(), calculatorView);
                if (presenter.getOper() == null) {
                    showNumOne(x);
                } else {
                    showNumTwo(x);
                }

            }
        };

        findViewById(R.id.but_0).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_1).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_2).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_3).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_4).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_5).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_6).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_7).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_8).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_9).setOnClickListener(numbersClickListener);

        Map<Integer, String> operations = new HashMap<>();
        operations.put(R.id.but_div, "/");
        operations.put(R.id.but_mult, "*");
        operations.put(R.id.but_sub, "-");
        operations.put(R.id.but_sum, "+");

        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOperationPressed(operations.get(v.getId()), calculatorView);
            }
        };

        findViewById(R.id.but_div).setOnClickListener(operationClickListener);
        findViewById(R.id.but_mult).setOnClickListener(operationClickListener);
        findViewById(R.id.but_sub).setOnClickListener(operationClickListener);
        findViewById(R.id.but_sum).setOnClickListener(operationClickListener);

        Button butEqual = findViewById(R.id.but_eql);
        butEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult(presenter.doOperation(presenter.getNumOne(), presenter.getNumTwo(), presenter.getOper()));
            }
        });

        Button butDot = findViewById(R.id.but_dot);
        butDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDotPressed(calculatorView);
            }
        });

        Button butC = findViewById(R.id.but_c);
        butC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doResrart(calculatorView);
            }
        });

//        container = findViewById(R.id.theme_container);

//        requestTheme();

        if (container != null) {

        }

        Button butDark = findViewById(R.id.but_dark);
        Button butLight = findViewById(R.id.but_light);
        for (Theme theme : Theme.values()) {
            butDark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storage.setTheme(Theme.THEME_ONE);
                    recreate();
                }
            });

            butLight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storage.setTheme(Theme.THEME_TWO);
                    recreate();
                }
            });
        }

//        for (Theme theme : Theme.values()) {
//            View itemView = getLayoutInflater().inflate(R.layout.item_theme, container, false);
//            ImageView img = itemView.findViewById(R.id.img_theme_1);
//            TextView txt = itemView.findViewById(R.id.text_theme);
//
//            img.setImageResource(theme.getImg());
//            txt.setText(theme.getTitle());
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    storage.setTheme(theme);
//
//                    recreate();
//                }
//            });
//
//            container.addView(itemView);
//        }
    }

    public void showOperation(String operation) {
        txtOperation.setText(operation);
    }

    public void showNumOne(Double numOne) {
        txtNumberOne.setText(numOne.toString());
    }

    public void showNumTwo(Double numTwo) {
        txtNumberTwo.setText(numTwo.toString());
    }

    public void showResult(Double result) {
        txtResult.setText(result.toString());
    }

//    public void requestTheme() {
//        List<Theme> themes = new ArrayList<>();
//        themes.add(new Theme(R.string.dark_theme, R.drawable.ic_circle, R.style.DarkTheme));
//        themes.add(new Theme(R.string.light_theme, R.drawable.ic_circle, R.style.LightTheme));
//        showThemes(themes);
//    }


//    public void showThemes(List<Theme> themes) {
//        if (container == null) {
//            return;
//        }
//
//

    }


