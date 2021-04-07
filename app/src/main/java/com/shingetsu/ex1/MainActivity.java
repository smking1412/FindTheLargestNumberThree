package com.shingetsu.ex1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NumberAdapter numberAdapter;
    private ArrayList<String> numberList = new ArrayList<>();
    private ArrayList<String> stringList;
    ArrayList<Integer> intArr = new ArrayList<>();

    private EditText edtNumber;
    private Button btnShow;
    private ImageButton btnSubmit;
    private int max = 0;
    private int maxSecond = 0;
    private int maxThird = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_number);
        edtNumber = findViewById(R.id.edt_number);
        btnShow = findViewById(R.id.btn_show);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkNum = false;

                numberList.clear();
                String inputValue = edtNumber.getText().toString();
                stringList = new ArrayList<String>(Arrays.asList(inputValue.split(",")));
                for (int i = 0; i < stringList.size(); i++) {
                    if (stringList.get(i) != null) {
                        try {
                            int number = Integer.parseInt(stringList.get(i));
                            if (number >= 0) {
                                intArr.add(number);
                                checkNum = true;
                            }
                        } catch (NumberFormatException e) {
                            Log.d("PMTAN", "Not Number: " + stringList.get(i));
                            checkNum = false;
                        }
                        if (checkNum == true) {
                            numberList.add(stringList.get(i));
                        }
                    }
                }
                numberAdapter = new NumberAdapter(MainActivity.this, numberList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(numberAdapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intArr.size() < 1) {
                    Toast.makeText(MainActivity.this, "Mảng trống", Toast.LENGTH_SHORT).show();
                } else if (intArr.size() == 1) {
                    Toast.makeText(MainActivity.this, "Mảng có duy nhất 1 phần tử là: " + intArr.get(0), Toast.LENGTH_SHORT).show();
                } else if (intArr.size() == 2) {
                    if (intArr.get(0) >= intArr.get(1)) {
                        max = intArr.get(0);
                        maxSecond = intArr.get(1);
                    } else {
                        max = intArr.get(1);
                        maxSecond = intArr.get(0);
                    }
                    Toast.makeText(MainActivity.this, "Mảng có 2 phần tử \n , max là: "+ max +"\n min là: "+ maxSecond, Toast.LENGTH_SHORT).show();
                } else {
                    max = intArr.get(0);
                    for (int i = 1; i < intArr.size(); i++) {
                        if (intArr.get(i) > max) {
                            max = intArr.get(i);
                        }
                    }
                    Log.d("PMTAN", "max: "+max);
                    maxSecond = Integer.MIN_VALUE;
                    for (int i = 0; i < intArr.size(); i++) {
                        if (intArr.get(i) > maxSecond && intArr.get(i) < max) {
                            maxSecond = intArr.get(i);
                        }
                    }
                    Log.d("PMTAN", "maxSecond: "+maxSecond);
                    maxThird = Integer.MIN_VALUE;
                    for (int i = 0; i < intArr.size(); i++){
                        if (intArr.get(i)> maxThird&& intArr.get(i)<maxSecond){
                            maxThird = intArr.get(i);
                        }
                    }
                    Toast.makeText(MainActivity.this, "Số lớn thứ 3 trong mảng là: "+maxThird, Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("Số lớn thứ 3 của mảng là: "+maxThird);
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });
    }
}