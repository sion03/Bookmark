package com.lifeistech.android.bookmark;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by sion on 2/22/2016.
 */

@Table(name = "memo_table")
public class MemoDB extends Model {

    @Column(name = "bookmark_id")
    public long bookmarkId;

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
