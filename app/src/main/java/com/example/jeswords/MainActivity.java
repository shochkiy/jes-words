package com.example.jeswords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jeswords.data.WordsContract;
import com.example.jeswords.data.WordsDbHelper;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private WordsDbHelper wordsDbHelper;

    private String[] words;
    private String[] translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordsDbHelper = new WordsDbHelper(this);
        SQLiteDatabase db = wordsDbHelper.getReadableDatabase();
//        Intent intent = new Intent(MainActivity.this, AddWordsActivity.class);
//        startActivity(intent);
        Cursor cursor = db.query(WordsContract.LearnedWords.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        if (cursor.getCount() == 0) {
            Intent intent = new Intent(MainActivity.this, AddWordsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayWords();

    }

    // Отображение 10 слов на активити

    private void displayWords() {
        SQLiteDatabase db = wordsDbHelper.getReadableDatabase();
        String[] projection = {
                WordsContract.LearnedWords._ID,
                WordsContract.LearnedWords.COLUMN_WORD,
                WordsContract.LearnedWords.COLUMN_TRANSLATE,
                WordsContract.LearnedWords.COLUMN_ISPASSED };

        Cursor cursor = db.query(WordsContract.LearnedWords.TABLE_NAME,
                projection,
                WordsContract.LearnedWords.COLUMN_ISPASSED + " = 0",
                null,
                null,
                null,
                null);

        TextView[] textViews = { findViewById(R.id.firstTextView),
                findViewById(R.id.secondTextView),
                findViewById(R.id.thirdTextView),
                findViewById(R.id.fourthTextView),
                findViewById(R.id.fifthTextView),
                findViewById(R.id.sixthTextView),
                findViewById(R.id.seventhTextView),
                findViewById(R.id.eigthTextView),
                findViewById(R.id.ninthTextView),
                findViewById(R.id.tenthTextView)};

        int currentElPosition = 0;
        words = new String[10];
        translate = new String[10];

        try {
            int wordColumnIndex = cursor.getColumnIndex(WordsContract.LearnedWords.COLUMN_WORD);
            int translateColumnIndex = cursor.getColumnIndex(WordsContract.LearnedWords.COLUMN_TRANSLATE);

            while (cursor.moveToNext()) {
                if (currentElPosition > 9) {
                    currentElPosition = 0;
                }
                String currentWord = cursor.getString(wordColumnIndex);
                String currentTranslate = cursor.getString(translateColumnIndex);
                textViews[currentElPosition].setText(currentWord + " - " + currentTranslate);
                words[currentElPosition] = currentWord;
                translate[currentElPosition] = currentTranslate;
                currentElPosition++;
            }
        } finally {
            cursor.close();
            currentElPosition = 0;
        }
    }

    public void startTestActivity(View v) {
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        intent.putExtra("words", words);
        intent.putExtra("translate", translate);
        startActivity(intent);
    }
}
