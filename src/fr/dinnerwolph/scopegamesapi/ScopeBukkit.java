package fr.dinnerwolph.scopegamesapi;

import fr.dinnerwolph.scopegamesapi.Bukkit.commands.GradeCommand;
import fr.dinnerwolph.scopegamesapi.Bukkit.commands.ModCommand;
import fr.dinnerwolph.scopegamesapi.Bukkit.listener.ListenerManager;
import fr.dinnerwolph.scopegamesapi.utils.SQLConnection;
import fr.dinnerwolph.scopegamesapi.utils.UpdateUtils;
import java.io.IOException;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

public class ScopeBukkit
extends JavaPlugin {
    private static ScopeBukkit instance;

    public void onEnable() {
        instance = this;
        SQLConnection.Connection();
        new ListenerManager(this);
        this.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        this.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "scopegames");
        this.getCommand("grade").setExecutor((CommandExecutor)new GradeCommand());
        this.getCommand("mod").setExecutor((CommandExecutor)new ModCommand());
        try {
            new UpdateUtils((Plugin)this);
        }
        catch (IOException exception) {
            // empty catch block
        }
    }

    public void onDisable() {
        SQLConnection.Deconnection();
    }

    public static ScopeBukkit getInstance() {
        return instance;
    }
}