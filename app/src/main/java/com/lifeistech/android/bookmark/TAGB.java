package com.lifeistech.android.bookmark;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by sion on 2/25/2016.
 */
@Table(name = "tag_table")
public class TAGB extends Model {

    @Column(name = "title")
    public String title;

    @Column(name = "url")
    public String url;

    @Column(name = "date")
    public String date;

    @Override
    public String toString(){
        return title;
    }

}