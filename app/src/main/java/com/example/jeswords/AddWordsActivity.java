package com.example.jeswords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.jeswords.data.WordsContract;
import com.example.jeswords.data.WordsDbHelper;
import com.google.android.material.snackbar.Snackbar;

public class AddWordsActivity extends AppCompatActivity {

    private EditText[] wordEditTexts;
    private EditText[] translateEditTexts;
    private Button addWordsButton;

    private WordsDbHelper wordsDbHelper;

    TableLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        wordsDbHelper = new WordsDbHelper(this);

        wordEditTexts = new EditText[] { findViewById(R.id.firstWordEditText),
                findViewById(R.id.secondWordEditText),
                findViewById(R.id.thirdWordEditText),
                findViewById(R.id.fourthWordEditText),
                findViewById(R.id.fifthWordEditText),
                findViewById(R.id.sixthWordEditText),
                findViewById(R.id.seventhWordEditText),
                findViewById(R.id.eigthWordEditText),
                findViewById(R.id.ninthWordEditText),
                findViewById(R.id.tenthWordEditText)};

        translateEditTexts = new EditText[] { findViewById(R.id.firstTranslateEditText),
                findViewById(R.id.secondTranslateEditText),
                findViewById(R.id.thirdTranslateEditText),
                findViewById(R.id.fourthTranslateEditText),
                findViewById(R.id.fifthTranslateEditText),
                findViewById(R.id.sixthTranslateEditText),
                findViewById(R.id.seventhTranslateEditText),
                findViewById(R.id.eigthTranslateEditText),
                findViewById(R.id.ninthTranslateEditText),
                findViewById(R.id.tenthTranslateEditText)};

    }

    public void addWordsToBase(View v) {

        SQLiteDatabase db = wordsDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < wordEditTexts.length; i++) {
            values.put(WordsContract.LearnedWords.COLUMN_WORD, wordEditTexts[i].getText().toString().toLowerCase());
            values.put(WordsContract.LearnedWords.COLUMN_TRANSLATE, translateEditTexts[i].getText().toString().toLowerCase());
            db.insert(WordsContract.LearnedWords.TABLE_NAME, null, values);
        }

       // Snackbar.make(root, "Words added to DB.", Snackbar.LENGTH_LONG).show();

        Intent intent = new Intent(AddWordsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
