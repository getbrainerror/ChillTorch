package meep.getbrainerror.chilltorch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_settings);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}