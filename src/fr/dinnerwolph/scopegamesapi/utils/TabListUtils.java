package fr.dinnerwolph.scopegamesapi.utils;

import com.mojang.authlib.GameProfile;
import fr.dinnerwolph.scopegamesapi.player.ScopePlayer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TabListUtils {
    static List<GameProfile> profileList = new ArrayList<GameProfile>();
    static MinecraftServer server = MinecraftServer.getServer();
    static WorldServer world = server.getWorldServer(0);
    static PlayerInteractManager manager = new PlayerInteractManager((World)world);

    public static void addItem(GameProfile gameProfile) {
        profileList.add(gameProfile);
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            ScopePlayer scopePlayer1 = new ScopePlayer(player1);
            for (GameProfile profile1 : profileList) {
                EntityPlayer player = new EntityPlayer(server, world, profile1, manager);
                PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[]{player});
                scopePlayer1.getHandle().playerConnection.sendPacket((Packet)packet);
            }
        }
    }

    public static void removeItem(GameProfile gameProfile) {
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            ScopePlayer scopePlayer1 = new ScopePlayer(player1);
            EntityPlayer player = new EntityPlayer(server, world, gameProfile, manager);
            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[]{player});
            scopePlayer1.getHandle().playerConnection.sendPacket((Packet)packet);
        }
    }

    public static void removePlayer(Player player) {
        CraftPlayer cp = (CraftPlayer)player;
        EntityPlayer ep = cp.getHandle();
        PacketPlayOutPlayerInfo pack = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[]{ep});
        cp.getHandle().playerConnection.sendPacket((Packet)pack);
    }
}