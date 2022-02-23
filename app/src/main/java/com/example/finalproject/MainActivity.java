package com.example.finalproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    private RecyclerView mRecycleView;
    private ArrayList<ToDo> mTodo;
    private ToDoAdapter mAdapter;
    public DatabaseHelper databaseHelper;
    private ArrayList<String> titleList;
    private ArrayList<String> infoList;
    private ArrayList<String> dueList;
    private ArrayList<String> doList;
    private ArrayList<String> idList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(MainActivity.this);

        titleList = databaseHelper.GetTitle();
        infoList = databaseHelper.GetInfo();
        dueList = databaseHelper.GetDue();
        doList = databaseHelper.GetDo();
        idList = databaseHelper.GetId();
        mRecycleView = findViewById(R.id.recyclerView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mTodo = new ArrayList<>();
        mAdapter = new ToDoAdapter(this,mTodo);
        mRecycleView.setAdapter(mAdapter);
        checkempty();
        initializeData();
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                databaseHelper.Delete(mTodo.get(viewHolder.getAdapterPosition()).getId());
                mTodo.remove(viewHolder.getAdapterPosition());

                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                checkempty();
            }
        });

        // Attach the helper to the RecyclerView.
        helper.attachToRecyclerView(mRecycleView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent GoToInsert = new Intent(this,NewThingActivity.class);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(GoToInsert,TEXT_REQUEST);
            }
        });



    }
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String []reply = data.getStringArrayExtra(NewThingActivity.EXTRA_REPLY);
                if(databaseHelper.Add(reply[0],reply[1],reply[2],reply[3])){
                    initializeData();
                }
            }
        }
    }
    private void initializeData() {
        titleList = databaseHelper.GetTitle();
        infoList = databaseHelper.GetInfo();
        dueList = databaseHelper.GetDue();
        doList = databaseHelper.GetDo();
        idList = databaseHelper.GetId();
        mTodo.clear();
        for(int i=0;i<titleList.size();i++){
            mTodo.add(new ToDo(titleList.get(i),infoList.get(i),dueList.get(i),doList.get(i),idList.get(i)));
        }
        mAdapter.notifyDataSetChanged();
        checkempty();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("remove","remove");
            databaseHelper.removeTodo();
            initializeData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkempty(){
        ImageView bg = (ImageView) findViewById(R.id.bg);
        TextView emptytext = (TextView) findViewById(R.id.emptytext);
        titleList = databaseHelper.GetTitle();
        if(titleList.size()<1){
            bg.setVisibility(View.VISIBLE);
            emptytext.setVisibility(View.VISIBLE);
        }
        else{
            bg.setVisibility(View.INVISIBLE);
            emptytext.setVisibility(View.INVISIBLE);
        }
    }

}