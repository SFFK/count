package com.cookandroid.countingnumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView fox;
    TextView num1;
    int count = 0;
    Button btn1, btn2, btn3, btn4;
    LinearLayout show1;
    ListView add1;
    ScrollView scrollView;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간편한 카운터");
        setVolumeControlStream(AudioManager.ERROR);

        UserDatabase db = UserDatabase.getDBInstance(this);
        Todo todo = new Todo();

        fox = (ImageView) findViewById(R.id.fox);
        num1 = (TextView) findViewById(R.id.num1);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        scrollView = (ScrollView) findViewById(R.id.scroll1);
        listItem = new ArrayList<String>();

        show1 = (LinearLayout) findViewById(R.id.show1);
        add1 = (ListView) findViewById(R.id.add1);

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listItem);
        .setText(db.getUserDao().getAll().toString());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                num1.setText(Integer.toString(count));
                db.getUserDao().delete(todo);
                todo.setUid(0);
                listItem.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "초기화됬습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fox.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo.setCount(Integer.toString(count));
                todo.setResult(count);
                db.getUserDao().insert(new Todo());
                listItem.add(todo.getUid() + " : " + todo.getCount());
                adapter.notifyDataSetChanged();
                add1.setAdapter(adapter);
                count = 0;
                todo.setUid(todo.getUid() + 1);
                num1.setText(Integer.toString(count));
                Toast.makeText(getApplicationContext(), "기록되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.setVisibility(View.GONE);
                fox.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                count += 1;
                num1.setText(Integer.toString(count));
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(count > 0) {
                    count -= 1;
                }
                num1.setText(Integer.toString(count));
                break;
        }
        return false;
    }

}