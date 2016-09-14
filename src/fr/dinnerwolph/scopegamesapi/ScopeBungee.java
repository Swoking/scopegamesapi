package fr.dinnerwolph.scopegamesapi;

import fr.dinnerwolph.scopegamesapi.utils.SQLConnection;
import net.md_5.bungee.api.plugin.Plugin;

public class ScopeBungee
extends Plugin {
    public static ScopeBungee instance;

    public void onEnable() {
        instance = this;
        SQLConnection.Connection();
    }

    public void onDisable() {
        SQLConnection.Deconnection();
    }
}