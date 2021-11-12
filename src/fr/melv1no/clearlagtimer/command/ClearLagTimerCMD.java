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
        sender.sendMessage(instance.getConfStr("countdown-message").replace("{time}", String.valueOf(instance.CountTimer)));
        return false;
    }
}
