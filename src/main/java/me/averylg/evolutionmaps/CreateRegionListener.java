package me.averylg.evolutionmaps;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CreateRegionListener implements Listener {

    @EventHandler
    public void onBlockRightClick(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand() == null) {
            return;
        }
        if (player.getInventory().getItemInMainHand().getItemMeta().equals(Main.REGION_TOOL.getItemMeta())) {
            event.setCancelled(true);
            Block block = event.getBlock();
            if (Main.REGION_TOOL.isRegionComplete()) {
                player.sendMessage(ChatColor.RED + "Cannot add any more coordinates to the tool!");
            } else {
                Main.REGION_TOOL.addRegionCorner(block.getLocation());
                player.sendMessage(ChatColor.GREEN + "Added Corner:\n",
                        "  X: " + block.getLocation().getBlockX() + "\n",
                        "  Y: " + block.getLocation().getBlockY() + "\n",
                        "  Z: " + block.getLocation().getBlockZ() + "\n");
            }
        }
    }
}
