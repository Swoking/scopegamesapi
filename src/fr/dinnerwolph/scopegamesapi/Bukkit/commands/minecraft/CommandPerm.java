package fr.dinnerwolph.scopegamesapi.Bukkit.commands.minecraft;

import fr.dinnerwolph.scopegamesapi.player.ScopePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandPerm
extends Command {
    CommandPerm() {
        super("");
    }

    public boolean testPermissionSilent(CommandSender target) {
        ScopePlayer scopePlayer = new ScopePlayer((Player)((ScopePlayer)target));
        if (scopePlayer.hasPerm(this.getPermission())) {
            return true;
        }
        return false;
    }
}