package com.example.jeswords.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "words.db";
    private static final int DATABASE_VERSION = 1;

    public static String SQL_CREATE_WORDS_TABLE = "CREATE TABLE " + WordsContract.LearnedWords.TABLE_NAME + " ("
            + WordsContract.LearnedWords._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WordsContract.LearnedWords.COLUMN_WORD + " TEXT NOT NULL, "
            + WordsContract.LearnedWords.COLUMN_TRANSLATE + " TEXT NOT NULL, "
            + WordsContract.LearnedWords.COLUMN_ISPASSED + " INTEGER NOT NULL DEFAULT 0);";

    public WordsDbHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + WordsContract.LearnedWords.TABLE_NAME);
        onCreate(db);
    }


}
