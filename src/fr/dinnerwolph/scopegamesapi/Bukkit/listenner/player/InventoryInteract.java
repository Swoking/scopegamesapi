package fr.dinnerwolph.scopegamesapi.Bukkit.listener.player;

import fr.dinnerwolph.scopegamesapi.player.ScopePlayer;
import fr.dinnerwolph.scopegamesapi.utils.RankList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryInteract
implements Listener {
    @EventHandler
    public void interact(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        try {
            ScopePlayer scopePlayer = new ScopePlayer(Bukkit.getPlayer((String)event.getInventory().getName()));
            ItemStack item = event.getCurrentItem();
            if (item.getItemMeta().getDisplayName().equals("Change Grade")) {
                event.setCancelled(true);
                Inventory inventory = Bukkit.createInventory((InventoryHolder)scopePlayer, (int)18, (String)event.getInventory().getName());
                ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("Administrateur");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Resp.Dev");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Dev");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Resp.Modo");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Modo");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Builder");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Helper");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Youtuber");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Scopers");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Hero");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Vip+");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Vip");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                itemMeta.setDisplayName("Member");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(new ItemStack[]{itemStack});
                player.openInventory(inventory);
            } else if (item.getItemMeta().getDisplayName().equals("Administrateur")) {
                scopePlayer.setRank(RankList.ADMINISTRATEUR.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Resp.Dev")) {
                scopePlayer.setRank(RankList.RESPDEV.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Dev")) {
                scopePlayer.setRank(RankList.DEV.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Resp.Modo")) {
                scopePlayer.setRank(RankList.RESPMODO.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Modo")) {
                scopePlayer.setRank(RankList.MODO.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Builder")) {
                scopePlayer.setRank(RankList.BUILDER.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Helper")) {
                scopePlayer.setRank(RankList.HELPER.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Youtuber")) {
                scopePlayer.setRank(RankList.YOUTUBER.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Scopers")) {
                scopePlayer.setRank(RankList.SCOPERS.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Hero")) {
                scopePlayer.setRank(RankList.HERO.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Vip+")) {
                scopePlayer.setRank(RankList.VIPPLUS.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Vip")) {
                scopePlayer.setRank(RankList.VIP.getRankId());
                player.getOpenInventory().close();
            } else if (item.getItemMeta().getDisplayName().equals("Member")) {
                scopePlayer.setRank(RankList.MEMBER.getRankId());
                player.getOpenInventory().close();
            } else if (item.getType() == Material.NETHER_STAR) {
                event.setCancelled(true);
            }
        }
        catch (Exception e) {
            // empty catch block
        }
    }
}