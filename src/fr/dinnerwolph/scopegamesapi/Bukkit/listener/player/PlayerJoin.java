package fr.dinnerwolph.scopegamesapi.Bukkit.listener.player;

import com.mojang.authlib.GameProfile;
import fr.dinnerwolph.scopegamesapi.player.ScopePlayer;
import java.lang.reflect.Field;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin
implements Listener {
    @EventHandler
    public void onPlayeJoinEvent(PlayerJoinEvent event) {
        ScopePlayer scopePlayer = new ScopePlayer(event.getPlayer());
        EntityPlayer eh = scopePlayer.getHandle();
        PacketPlayOutPlayerInfo test = new PacketPlayOutPlayerInfo();
        try {
            Field profileField = eh.getClass().getSuperclass().getDeclaredField("bH");
            profileField.setAccessible(true);
            profileField.set((Object)eh, (Object)new GameProfile(eh.getUniqueID(), "\u00a77" + eh.getName()));
        }
        catch (Exception e) {
            e.printStackTrace();
            Bukkit.broadcastMessage((String)"Not Work.");
        }
        scopePlayer.createAccount();
    }
}