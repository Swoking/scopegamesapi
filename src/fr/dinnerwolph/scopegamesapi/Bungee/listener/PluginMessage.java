package fr.dinnerwolph.scopegamesapi.Bungee.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.dinnerwolph.scopegamesapi.ScopeBungee;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PluginMessage
implements Listener {
    @EventHandler
    public void onReceived(PluginMessageEvent event) {
        String subchannel;
        ByteArrayDataInput input;
        if (event.getTag().equals("scopegames") && (subchannel = (input = ByteStreams.newDataInput((byte[])event.getData())).readUTF()).equals("message")) {
            String msg = input.readUTF();
            ScopeBungee.instance.getProxy().broadcast(msg);
        }
    }
}