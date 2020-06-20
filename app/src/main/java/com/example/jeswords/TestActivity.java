package com.example.jeswords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeswords.data.WordsContract;
import com.example.jeswords.data.WordsDbHelper;
import com.google.android.material.snackbar.Snackbar;

public class TestActivity extends AppCompatActivity {

    private WordsDbHelper wordsDbHelper;

    private String[] words;
    private String[] translate;

    private TextView[] wordsTestTextViews;
    private EditText[] translateTestEditTexts;

    private Button nextStepButton;
    private Button passTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        wordsDbHelper = new WordsDbHelper(this);

        Intent intent = getIntent();
        words = intent.getStringArrayExtra("words");
        translate = intent.getStringArrayExtra("translate");

        wordsTestTextViews = new TextView[] {findViewById(R.id.firstTestTextView),
                findViewById(R.id.secondTestTextView),
                findViewById(R.id.thirdTestTextView),
                findViewById(R.id.fourthTestTextView),
                findViewById(R.id.fifthTestTextView),
                findViewById(R.id.sixthTestTextView),
                findViewById(R.id.seventhTestTextView),
                findViewById(R.id.eigthTestTextView),
                findViewById(R.id.ninthTestTextView),
                findViewById(R.id.tenthTestTextView)};

        translateTestEditTexts = new EditText[] {findViewById(R.id.firstTestTranslateEditText),
                findViewById(R.id.secondTestTranslateEditText),
                findViewById(R.id.thirdTestTranslateEditText),
                findViewById(R.id.fourthTestTranslateEditText),
                findViewById(R.id.fifthTestTranslateEditText),
                findViewById(R.id.sixthTestTranslateEditText),
                findViewById(R.id.seventhTestTranslateEditText),
                findViewById(R.id.eigthTestTranslateEditText),
                findViewById(R.id.ninthTestTranslateEditText),
                findViewById(R.id.tenthTestTranslateEditText)};

        nextStepButton = findViewById(R.id.nextStepButton);
        passTestButton = findViewById(R.id.passTestButton);

        fillTextViews(false);
    }

    private void fillTextViews(boolean isReverse) {
        if (!isReverse) {
            for (int i = 0; i < words.length; i++) {
                wordsTestTextViews[i].setText(words[i]);
            }
        } else {
            for (int i = 0; i < translate.length; i++) {
                wordsTestTextViews[i].setText((translate[i]));
            }
        }
    }

    private void clearEditTexts() {
        for (EditText editText: translateTestEditTexts) {
            editText.setText("");
        }
    }

    public void nextTestStep(View v) {
        for (int i = 0; i < translate.length; i++) {
            if (!translate[i].equals(translateTestEditTexts[i].getText().toString().trim().toLowerCase())) {
                Snackbar.make(findViewById(R.id.root_element), "Try again. There are some errors.", Snackbar.LENGTH_LONG).show();
                return;
            }
        }
        fillTextViews(true);
        clearEditTexts();
        passTestButton.setVisibility(View.VISIBLE);
        nextStepButton.setVisibility(View.INVISIBLE);

    }

    public void passTest(View v) {
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals(translateTestEditTexts[i].getText().toString().trim().toLowerCase())) {
                Snackbar.make(findViewById(R.id.root_element),"Try again. There are some errors.", Snackbar.LENGTH_LONG).show();
                return;
            }
        }
        SQLiteDatabase db = wordsDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WordsContract.LearnedWords.COLUMN_ISPASSED, 1);
        db.update(WordsContract.LearnedWords.TABLE_NAME, values, WordsContract.LearnedWords.COLUMN_ISPASSED + " = 1", null);
        Snackbar.make(findViewById(R.id.root_element), "You pass the test.", Snackbar.LENGTH_LONG).show();
        Intent intent = new Intent(TestActivity.this, AddWordsActivity.class);
        startActivity(intent);
    }
}
