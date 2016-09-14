package fr.dinnerwolph.scopegamesapi.Bukkit.commands;

import fr.dinnerwolph.scopegamesapi.player.ScopePlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        if (args.length == 0) {
            commandSender.sendMessage("/mod <message>");
            return true;
        }
        ScopePlayer scopePlayer = new ScopePlayer((Player)commandSender);
        if (!scopePlayer.hasPerm("command.mod")) {
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\u00a76Mod\u00e9ration\u00a7f] \u00a7c" + scopePlayer.getName() + ": \u00a7b");
        for (String arg : args) {
            stringBuilder.append(arg + " ");
        }
        String message = stringBuilder.substring(0, stringBuilder.length() - 1);
        Bukkit.broadcastMessage((String)message);
        return true;
    }
}