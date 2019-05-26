package com.example.a2048;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import static com.example.a2048.SettingActivity.SETTINGS_REQUEST;

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


        Intent intent = new Intent(this, GameActivity.class);
        intent.setType("text/plain");
        intent.putExtra(GameActivity.EXTRA_MESSAGE, selection);

        startActivity(intent);
    }

    public void onSettingsStart(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivityForResult(intent, SETTINGS_REQUEST);
    }
}
