package fr.dinnerwolph.scopegamesapi.Bukkit.listener.player;

import fr.dinnerwolph.scopegamesapi.player.ScopePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocess
implements Listener {
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        ScopePlayer scopePlayer = new ScopePlayer(event.getPlayer());
        String[] cmd = event.getMessage().substring(1).split(" ", 2);
        if (!scopePlayer.hasPerm("command." + cmd[0])) {
            scopePlayer.sendMessage("\u00a7cVous n'avez pas la permission de faire cette commande.");
            event.setCancelled(true);
        }
    }
}