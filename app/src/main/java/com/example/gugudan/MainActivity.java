package com.example.gugudan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("구구단 맞추기");

        EditText edtNum1, edtNum2, edtNum3;
        Button btnRan, btnCheck;
        ListView list;

        // 바인딩
        edtNum1 = (EditText) findViewById(R.id.edtNum1);
        edtNum2 = (EditText) findViewById(R.id.edtNum2);
        edtNum3 = (EditText) findViewById(R.id.edtNum3);

        btnRan = (Button) findViewById(R.id.btnRan);
        btnCheck = (Button) findViewById(R.id.btnCheck);

        list = (ListView) findViewById(R.id.listView1);

        // 난수생성! 버튼 클릭
        btnRan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 난수 생성
                int ran1 = new Random().nextInt(8)+2; //0~7 사이의 난수 발생 +2 -> 2~9 사이의 난수
                int ran2 = new Random().nextInt(8)+2;

                // 화면에 난수 표시해주기
                edtNum1.setText(String.valueOf(ran1));
                edtNum2.setText(String.valueOf(ran2));

                edtNum3.setText("");
                list.setVisibility(View.INVISIBLE);
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNum1.getText().toString().equals("") | edtNum2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "먼저 난수를 생성하세요.", Toast.LENGTH_SHORT).show();
                } else if(edtNum3.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "정답을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    int num3 = Integer.parseInt(edtNum3.getText().toString());

                    int num1 = Integer.parseInt(edtNum1.getText().toString());
                    int num2 = Integer.parseInt(edtNum2.getText().toString());

                    int result = num1 * num2;

                    // 구구단 배열
                    final String[] gugudan = new String[9];
                    for (int i = 1; i < 10; i++) {
                        gugudan[i - 1] = num1 + " X " + i + " = " + num1 * i;
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, gugudan);
                    list.setAdapter(adapter);

                    if (result == num3) {
                        list.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "틀렸습니다!", Toast.LENGTH_SHORT).show();
                        list.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}