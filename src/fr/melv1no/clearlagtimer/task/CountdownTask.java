package fr.melv1no.clearlagtimer.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import fr.melv1no.clearlagtimer.ClearLagTimer;

public class CountdownTask {
    private ClearLagTimer instance;
    public CountdownTask(ClearLagTimer instance){
        this.instance = instance;
    }
    int countdownTask;

    public void Countdown(){
        countdownTask = instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
            public void run() {
                instance.CountTimer--;
                if (instance.CountTimer == 0) {
                    Bukkit.getScheduler().cancelTask(countdownTask);
                    instance.CountTimer = instance.IntervalTimer;
                    Countdown();
                }
                if(instance.broadcastFeatureIsEnable) {
                    for (int i : instance.alertInterval) {
                        if (instance.CountTimer == i) {
                            for (Player p : instance.getServer().getOnlinePlayers()) {
                                p.sendMessage(instance.getConfStr("broadcast-alert.broadcast-message").replace("{time}", convertSeconds(i)));
                            }
                        }
                    }
                }
            }
        }, 20L, 20L);
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
