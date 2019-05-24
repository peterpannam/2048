package com.example.a2048;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

public class MenuActivity extends AppCompatActivity {
    Difficulty level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void onGameStart(View view) {
        Spinner spinner = findViewById(R.id.difficultyspinner);
        String selection = spinner.getSelectedItem().toString().toUpperCase();


        Intent intent = new Intent(this, MainActivity.class);
        intent.setType("text/plain");
        intent.putExtra(MainActivity.EXTRA_MESSAGE, selection);
        //String chooserTitle = getString(R.string.chooser);
        //Intent chosenIntent = Intent.createChooser(intent, chooserTitle);

        startActivity(intent);
    }
}
