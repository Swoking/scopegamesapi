package fr.dinnerwolph.scopegamesapi.Bukkit.listener;

import fr.dinnerwolph.scopegamesapi.Bukkit.listener.player.InventoryInteract;
import fr.dinnerwolph.scopegamesapi.Bukkit.listener.player.PlayerCommandPreprocess;
import fr.dinnerwolph.scopegamesapi.Bukkit.listener.player.PlayerJoin;
import fr.dinnerwolph.scopegamesapi.ScopeBukkit;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {
    public ListenerManager(ScopeBukkit plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents((Listener)new PlayerJoin(), (Plugin)plugin);
        pm.registerEvents((Listener)new InventoryInteract(), (Plugin)plugin);
        pm.registerEvents((Listener)new PlayerCommandPreprocess(), (Plugin)plugin);
    }
}