package meep.getbrainerror.chilltorch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            getSupportActionBar().setHomeButtonEnabled(true);
        }

        SeekBar seekBarBrightness = findViewById(R.id.seekBarBrightness);
        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Utils.Vibrate(getApplicationContext(), 250L);

                String torch_vfs = appPreferences.getString("pref_torch_vfs", "torch-light0");

                Utils.SetTorchBrightness(torch_vfs, String.valueOf(progress));
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        String torch_vfs = appPreferences.getString("pref_torch_vfs", "torch-light0");
        Utils.TurnTorchOff(torch_vfs);
    }

    public void onTorchToggleClick(View view) {
        String torch_vfs = appPreferences.getString("pref_torch_vfs", "torch-light0");
        String brightness = appPreferences.getString("pref_torch_brightness", "1");
        Utils.ToggleTorch(torch_vfs, brightness);
    }

    public void onSettingsButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

}