package fr.melv1no.clearlagtimer;

import fr.melv1no.clearlagtimer.command.ClearLagTimerCMD;
import fr.melv1no.clearlagtimer.task.CountdownTask;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class ClearLagTimer extends JavaPlugin {
    public int IntervalTimer;
    public int CountTimer;
    public ArrayList<Integer> alertInterval;
    public boolean broadcastFeatureIsEnable = false;
    private CountdownTask countdownTask;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        checkDependencies();
        checkClearLagConfig();
        if(getConfig().getBoolean("broadcast-alert.enable")){
            broadcastFeatureIsEnable = true;
            Bukkit.getConsoleSender().sendMessage("La Feature d'alerte ClearLag est activée !");
             alertInterval = new ArrayList<>();
            for(int i : this.getConfig().getIntegerList("broadcast-alert.alertInterval")){
                alertInterval.add(i);
            }
        }
        countdownTask = new CountdownTask(this);
        countdownTask.Countdown();
        Bukkit.getConsoleSender().sendMessage("ClearLagTimer Activé !");

         Bukkit.getPluginCommand("clearlag").setExecutor(new ClearLagTimerCMD(this));

    }
    private void checkClearLagConfig() {
        File file = new File("plugins/ClearLag/config.yml");
        if(!file.exists()){
            Bukkit.getConsoleSender().sendMessage("e[ClearLagTimer] §cUne erreur s'est produite ! Impossible de lire le fichier de configuration de CleagLag ");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        this.IntervalTimer = fileConfiguration.getInt("auto-removal.autoremoval-interval");
        this.CountTimer =  IntervalTimer;

    }
    private void checkDependencies() {
        Bukkit.getConsoleSender().sendMessage("§e[ClearLagTimer] Recherche du plugin ClearLag");
        if (Bukkit.getServer().getPluginManager().getPlugin("ClearLag") != null) {
            if (Bukkit.getServer().getPluginManager().getPlugin("ClearLag").isEnabled()) {
                Bukkit.getConsoleSender().sendMessage("§e[ClearLagTimer] §aBingo ! §eLe plugin ClearLag a été trouvé !");
            }
        } else {
            Bukkit.getConsoleSender().sendMessage("§e[ClearLagTimer] §cUne erreur s'est produite ! Impossible de trouver le plugin ClearLag!");
            this.getServer().getPluginManager().disablePlugin(this);
        }
    }
    @Override
    public void onDisable() {

    }
    public String getConfStr(String confPath) {
        return this.getConfig().getString(confPath).replace("&","§");
    }
}

