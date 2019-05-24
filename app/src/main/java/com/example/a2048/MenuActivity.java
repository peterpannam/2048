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
        final Spinner spinner = view.findViewById(R.id.difficulty);
        //String selection = spinner.getSelectedItem().toString().toUpperCase();
        //Log.i("bug testing", selection);
        //level = Difficulty.valueOf(selection.toUpperCase());

        Intent intent = new Intent(this, MainActivity.class);
        //intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_TEXT, selection);
        //String chooserTitle = getString(R.string.chooser);
        //Intent chosenIntent = Intent.createChooser(intent, chooserTitle);

        startActivity(intent);
    }
}
