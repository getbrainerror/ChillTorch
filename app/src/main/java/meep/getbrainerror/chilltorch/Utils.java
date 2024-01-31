package meep.getbrainerror.chilltorch;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;

public class Utils {
    public static void ToggleTorch(String vfsDirectory, String brightness) {
        String command = "if [ $(cat /sys/class/leds/" + vfsDirectory + "/brightness) -gt 0 ]; then echo 0 > /sys/class/leds/" + vfsDirectory + "/brightness; else echo " + brightness + " > /sys/class/leds/" + vfsDirectory + "/brightness; fi";
        Log.i("toggle-command", command);
        executeRootCommand(command);
    }

    public static void SetTorchBrightness(String vfsDirectory, String brightness) {
        String command = "echo " + brightness + " > /sys/class/leds/" + vfsDirectory + "/brightness";
        Log.i("set-brightness-command", command);
        executeRootCommand(command);
    }
    public static void TurnTorchOff(String vfsDirectory) {
        String command = "echo 0 > /sys/class/leds/" + vfsDirectory + "/brightness";
        Log.i("set-brightness-command", command);
        executeRootCommand(command);
    }

    public static void executeRootCommand(String command) {
        Process process = null;
        DataOutputStream outputStream = null;
        try {
            process = Runtime.getRuntime().exec("su");
            outputStream = new DataOutputStream(process.getOutputStream());
            outputStream.writeBytes(command + "\n");
            outputStream.writeBytes("exit\n");
            outputStream.flush();

            int exitCode = process.waitFor();
            Log.i("su-exit", String.valueOf(exitCode));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void Vibrate(Context context, long duration) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        VibrationEffect vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(vibrationEffect);
        }
    }
}
