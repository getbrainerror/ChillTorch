package meep.getbrainerror.chilltorch;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Utils {
    public static void ToggleTorch(String vfsDirectory, String brightness){
        String command = "if [ $(cat /sys/class/leds/" + vfsDirectory + "/brightness) -gt 0 ]; then echo 0 > /sys/class/leds/"+ vfsDirectory +"/brightness; else echo " + brightness + " > /sys/class/leds/"+ vfsDirectory +"/brightness; fi\n";

        Log.i("lol",command);
        executeRootCommand(command);
    }
    public static boolean executeRootCommand(String command) {
        Process process = null;
        DataOutputStream outputStream = null;

        try {
            process = Runtime.getRuntime().exec("su");
            outputStream = new DataOutputStream(process.getOutputStream());

            // Schreibe den Befehl in das Shell-Programm
            outputStream.writeBytes(command + "\n");
            outputStream.writeBytes("exit\n");
            outputStream.flush();

            // Warte darauf, dass der Prozess abgeschlossen ist

            int exitCode = process.waitFor();
            Log.i("su-exit",String.valueOf(exitCode));
            return (exitCode == 0);

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

        return false;
    }
}
