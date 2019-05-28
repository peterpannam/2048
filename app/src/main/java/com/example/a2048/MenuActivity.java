package com.example.a2048;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import static com.example.a2048.SettingActivity.SETTINGS_REQUEST;

/**
 * Educational logic game
 * @author Peter Pannam, 13322446
 */
public class MenuActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    private String colourChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        colourChoice = "RED";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("detroy message", "activity destroyed");
        stopService(new Intent(this, backgroundMusic.class));
    }

    /**
     * Checks code coming back from settings activity then makes changes accordingly
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                Log.i("message", "callback received");
                colourChoice = data.getStringExtra(EXTRA_MESSAGE);
            }
        }
    }

    /**
     * starts a new game
     */
    public void onGameStart(View view) {
        Spinner spinner = findViewById(R.id.difficultyspinner);
        String selection = spinner.getSelectedItem().toString().toUpperCase();

        Intent intent = new Intent(this, GameActivity.class);
        intent.setType("text/plain");
        intent.putExtra(GameActivity.EXTRA_MESSAGE, selection);
        intent.putExtra(GameActivity.COLOUR_STRING, colourChoice);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onSettingsStart(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivityForResult(intent, SETTINGS_REQUEST);
    }
}
