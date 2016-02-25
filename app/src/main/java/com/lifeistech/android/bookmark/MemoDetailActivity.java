package com.lifeistech.android.bookmark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MemoDetailActivity extends AppCompatActivity {

    MemoDB mMemoDB;
    EditText mTitle;
    EditText mURL;
    CheckBox mtagA;
    CheckBox mtagB;
    CheckBox mtagC;
    CheckBox mtagD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);

        mTitle = (EditText) findViewById(R.id.detail_title);
        mURL = (EditText) findViewById(R.id.detail_url);
        setMemo();
    }

    //Intent...set the data from MemoDB
    void setMemo(){
        Intent i = getIntent();
        List<MemoDB>memoDBList = new Select().from(MemoDB.class).where("date=?",i.getStringExtra("data")).execute();
        mMemoDB = memoDBList.get(0);
        mTitle.setText(mMemoDB.title);
        mURL.setText(mMemoDB.url);
    }

    //update memo
    void updateMemo(){
        mMemoDB.title = mTitle.getText().toString();
        mMemoDB.url = mURL.getText().toString();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        mMemoDB.date = sdf.format(date);
        mMemoDB.save();
    }


    //MENUBAR display:menu_memo_detail
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memo_detail, menu);
        return true;
    }

    //MENUBAR behavior: MemoDetailActivity >> ClicktheButton "detail_save" >> save
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (R.id.detail_save == id){
            updateMemo();
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
