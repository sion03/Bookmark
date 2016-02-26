package com.lifeistech.android.bookmark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MemoCreateActivity extends AppCompatActivity {

    MemoDB mMemoDB;
    TAG mTAG;
    EditText mTitle;
    EditText mURL;
    CheckBox mtagA;
    CheckBox mtagB;
    CheckBox mtagC;
    CheckBox mtagD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_create);

        //EditTextArea
        mTitle = (EditText) findViewById(R.id.create_title);
        mURL = (EditText) findViewById(R.id.create_url);

        //CheckboxArea-TagA　※完成次第、他tagについても複製,DetailActivityについても複製
        mtagA = (CheckBox) findViewById(R.id.check_A);
            //if the box checked
            mtagA.setChecked(true);
            mtagA.setOnClickListener(new View.OnClickListener() {
                //when checkbox-TAG A clicked.
                @Override
                public void onClick(View v) {
                    CheckBox mtagA = (CheckBox) v;
//                    //if clicked, then set to tagA's array.
//                    if (mtagA.isChecked()) {
//                        mMemoDB.bookmarkId = new tagArraya;
                    }
                }

                );


                //new DB.
                mMemoDB = new MemoDB();
                mTAG = new TAG();
            }


    //MENUBAR display: menu_memo_create
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memo_create, menu);
        return true;
    }

    //MENUBAR behavior: MemoCreateActivity >> ClicktheButton "create_save" >> save
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (R.id.create_save == id) {
            saveMemo();
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //save the new data.
    void saveMemo(){
        //new title/url
        mMemoDB.title = mTitle.getText().toString();
        mMemoDB.url = mURL.getText().toString();

        //new tags ※未記入


        //new date
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        mMemoDB.date = sdf.format(date);

        //save all data.
        mMemoDB.save();

        //get id (start from 1 ...not 0!)
        mMemoDB.bookmarkId = mMemoDB.getId() + 1 ;
        //if check_A checked, then set to tagA's array.
        if (mtagA.isChecked()) {
            int k = mTAG.tagArraya.length ;
            int j;
            for(j=0 ; j<k ;j++){if(mTAG.tagArraya[j]==0)break;}
            mTAG.tagArraya[j] = mMemoDB.bookmarkId ;
        }

    }
}
