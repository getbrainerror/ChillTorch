package meep.getbrainerror.chilltorch;

import android.content.SharedPreferences;
import android.service.quicksettings.TileService;
import android.view.MenuItem;
import android.widget.PopupMenu;

import androidx.preference.PreferenceManager;

public class FlashlightTileService extends TileService {

    public FlashlightTileService(){
    }

    @Override
    public void onClick() {
        super.onClick();
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String torch_vfs = appPreferences.getString("pref_torch_vfs", "torch-light0");
        String brightness = appPreferences.getString("pref_torch_brightness", "1");
        Utils.ToggleTorch(torch_vfs, brightness);
    }
    @Override
    public void onTileAdded() {
        super.onTileAdded();

    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
    }
}
