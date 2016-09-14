package fr.dinnerwolph.scopegamesapi.player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.dinnerwolph.scopegamesapi.ScopeBukkit;
import fr.dinnerwolph.scopegamesapi.mysql.grade.GradeSQL;
import fr.dinnerwolph.scopegamesapi.mysql.money.ScopeCoinsSQL;
import fr.dinnerwolph.scopegamesapi.mysql.money.ScopeSQL;
import fr.dinnerwolph.scopegamesapi.mysql.perm.PermSQL;
import fr.dinnerwolph.scopegamesapi.utils.SQLConnection;
import fr.dinnerwolph.scopegamesapi.utils.ServerList;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ScopePlayer
extends CraftPlayer {
    private UUID uuid;
    private CraftPlayer craftPlayer;

    public ScopePlayer(Player craftPlayer) {
        super((CraftServer)Bukkit.getServer(), ((CraftPlayer)craftPlayer).getHandle());
        this.uuid = craftPlayer.getUniqueId();
        this.craftPlayer = (CraftPlayer)craftPlayer;
    }

    public ScopePlayer(UUID uuid) {
        super((CraftServer)Bukkit.getServer(), ((CraftPlayer)Bukkit.getOfflinePlayer((UUID)uuid)).getHandle());
        this.uuid = uuid;
        this.craftPlayer = (CraftPlayer)Bukkit.getOfflinePlayer((UUID)uuid);
    }

    public Integer getScope() {
        return SQLConnection.scopesql.getScopes(this.uuid);
    }

    public void addScope(int money) {
        SQLConnection.scopesql.addScopes(this.uuid, money);
    }

    public void removeScope(int money) {
        SQLConnection.scopesql.removeScopes(this.uuid, money);
    }

    public Integer getScopeCoins() {
        return SQLConnection.scopecoinssql.getScopes(this.uuid);
    }

    public void addScopeCoins(int money) {
        SQLConnection.scopecoinssql.addScopes(this.uuid, money);
    }

    public void removeScopeCoins(int money) {
        SQLConnection.scopecoinssql.removeScopes(this.uuid, money);
    }

    public void createAccount() {
        SQLConnection.scopesql.createAccount(this.uuid);
        SQLConnection.scopecoinssql.createAccount(this.uuid);
        SQLConnection.gradesql.createAccount(this.uuid);
    }

    public void sendTo(ServerList serverList) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverList.getServer());
        this.craftPlayer.sendPluginMessage((Plugin)ScopeBukkit.getInstance(), "BungeeCord", out.toByteArray());
    }

    public String getRankName() {
        return SQLConnection.gradesql.getRank(this.uuid);
    }

    public void setRank(Integer rank) {
        SQLConnection.gradesql.setRank(this.uuid, rank);
    }

    public boolean hasPerm(String permission) {
        return SQLConnection.permSQL.hasPermission(this.uuid, permission);
    }

    public String getPrefix() {
        return SQLConnection.gradesql.getRankPrefix(this.uuid);
    }

    public boolean isBanned() {
        return SQLConnection.gradesql.Baned(this.uuid);
    }

    public void sendMessageWithBungee(String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("message");
        out.writeUTF(message);
        this.craftPlayer.sendPluginMessage((Plugin)ScopeBukkit.getInstance(), "scopegames", out.toByteArray());
    }
}