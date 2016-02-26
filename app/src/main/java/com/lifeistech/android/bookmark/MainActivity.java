package com.lifeistech.android.bookmark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    private String[] mStrings = {"TAG A","TAG B","TAG C","TAG D"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.tag_list);

        //Intent..MainView: MainActivity >> OnClick >> MemoDetailActivity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //change DB(TAG.A~D) according to clicked position
                if (position == 0){TAGA data = (TAGA) parent.getItemAtPosition(position);}
                else if (position == 1){TAGB data = (TAGB) parent.getItemAtPosition(position);}
                else if (position == 2){TAGC data = (TAGC) parent.getItemAtPosition(position);}
                else {TAGD data = (TAGD) parent.getItemAtPosition(position);}
                Intent i = new Intent(MainActivity.this, MemoDetailActivity.class);
//                i.putExtra("data", data.date);
                startActivity(i);
            }
            });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Intent.. MENUBAR behavior: MainActivity >> ClicktheButton "main_create" >> MemoCreateActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (R.id.main_create == id){
            Intent i = new Intent(MainActivity.this, MemoCreateActivity.class);
            startActivity(i);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //TAGList...refer to clicked item

    void setTAGList() {
        List<MemoDB> memoList = new Select().from(MemoDB.class).execute();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.tag_list,
                mStrings
        );//MainActivity >> <TAG> >> <DB> >> <TAGlist> >> here.

        mListView.setAdapter(adapter);

    }

    @Override
    public void onResume(){
        super.onResume();
        setTAGList();
    }//START "setTAGList()" when Activity displayed.



}