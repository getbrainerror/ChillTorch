package meep.getbrainerror.chilltorch;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            EditTextPreference editTextCameraTorchBrightnessPreference = (EditTextPreference) findPreference("pref_torch_brightness");

            if (editTextCameraTorchBrightnessPreference != null) {
                editTextCameraTorchBrightnessPreference.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                    @Override
                    public void onBindEditText(EditText editText) {
                        // Ändere den inputType hier
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    }
                });
            }
            EditTextPreference editTextMaxBrightnessPreference = (EditTextPreference) findPreference("pref_max_brightness");

            if (editTextMaxBrightnessPreference != null) {
                editTextMaxBrightnessPreference.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                    @Override
                    public void onBindEditText(EditText editText) {
                        // Ändere den inputType hier
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    }
                });
            }
        }
    }
}