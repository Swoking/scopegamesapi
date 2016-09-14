package fr.dinnerwolph.scopegamesapi.Bukkit.commands;

import fr.dinnerwolph.scopegamesapi.player.ScopePlayer;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GradeCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        ScopePlayer scopePlayer = new ScopePlayer((Player)commandSender);
        if (!scopePlayer.hasPerm("command.grade")) {
            return true;
        }
        if (args.length < 1) {
            commandSender.sendMessage("/grade <user>");
            return true;
        }
        ScopePlayer target = new ScopePlayer(Bukkit.getPlayer((String)args[0]));
        Inventory inventory = Bukkit.createInventory((InventoryHolder)((Player)commandSender), (int)9, (String)target.getName());
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(target.getRankName());
        itemStack.setItemMeta(itemMeta);
        inventory.addItem(new ItemStack[]{itemStack});
        itemStack = new ItemStack(Material.NETHER_STAR);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Change Grade");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(3, itemStack);
        ((Player)commandSender).openInventory(inventory);
        return true;
    }
}