package com.example.jeswords.data;

import android.provider.BaseColumns;

public class WordsContract {

    private WordsContract() {}

    public static final class LearnedWords implements BaseColumns {
        public final static String TABLE_NAME = "words";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_WORD = "word";
        public final static String COLUMN_TRANSLATE = "translate";
        public final static String COLUMN_ISPASSED = "ispassed";
    }

}
