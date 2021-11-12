package fr.melv1no.clearlagtimer.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import fr.melv1no.clearlagtimer.ClearLagTimer;

public class ClearLagTimerCMD implements CommandExecutor {
    private ClearLagTimer instance;
    public ClearLagTimerCMD(ClearLagTimer instance){
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        sender.sendMessage(instance.getConfStr("countdown-message").replace("{time}", String.valueOf(convertSeconds(instance.CountTimer))));
        return false;
    }
    public static String convertSeconds(int seconds) {
        int h = seconds/ 3600;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        String sh = (h > 0 ? String.valueOf(h) + " " + "h" : "");
        String sm = (m < 10 && m > 0 && h > 0 ? "0" : "") + (m > 0 ? (h > 0 && s == 0 ? String.valueOf(m) : String.valueOf(m) + " " + "minutes") : "");
        String ss = (s == 0 && (h > 0 || m > 0) ? "" : (s < 10 && (h > 0 || m > 0) ? "0" : "") + String.valueOf(s) + " " + "secondes");
        return sh + (h > 0 ? " " : "") + sm + (m > 0 ? " " : "") + ss;
    }
}
