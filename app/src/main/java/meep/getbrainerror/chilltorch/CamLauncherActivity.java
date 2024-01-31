package meep.getbrainerror.chilltorch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;


public class CamLauncherActivity extends AppCompatActivity {

    SharedPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String torch_vfs = appPreferences.getString("pref_torch_vfs", "torch-light0");
        String brightness = appPreferences.getString("pref_torch_brightness", "1");
        Utils.ToggleTorch(torch_vfs, brightness);
        this.finishAffinity();
    }


}