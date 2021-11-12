package play.trootcraft.fr.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import play.trootcraft.fr.ClearLagTimer;

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
                                p.sendMessage(instance.getConfStr("broadcast-alert.broadcast-message").replace("{time}", String.valueOf(i)));
                            }
                        }
                    }
                }
            }
        }, 20L, 20L);
    }


}
