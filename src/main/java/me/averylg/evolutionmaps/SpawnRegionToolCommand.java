package me.averylg.evolutionmaps;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;

public class SpawnRegionToolCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (label.equalsIgnoreCase("region")) {
                if (args.length == 0) {
                    player.sendMessage("How to use /region command:\n");
                    player.sendMessage("  /region tool: Gives you the region creation tool.\n");
                    player.sendMessage("  /region clear: Resets the tool.\n");
                    player.sendMessage("  /region print: Prints the corners of your region.\n");
                    player.sendMessage("  /region process: Puts the block ordinals within the region into the blocks.csv file.\n");
                } else if (args[0].equalsIgnoreCase("tool")) {
                    player.getInventory().setItemInMainHand(Main.REGION_TOOL);
                } else if (args[0].equalsIgnoreCase("clear")) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().equals(Main.REGION_TOOL.getItemMeta())) {
                        Main.REGION_TOOL.clearRegionCorners();
                        player.sendMessage(ChatColor.GOLD + "Corners cleared!");
                    } else {
                        player.sendMessage("You must have the Region Creation Tool to access this command!");
                    }
                } else if (args[0].equalsIgnoreCase("process")) {
                    if (Main.REGION_TOOL.isRegionComplete()) {
                        player.sendMessage("Processing!");
                        try {
                            this.generateCSVFile(player);
                            player.sendMessage(ChatColor.GREEN + "Done!");
                        } catch (Exception e) {
                            e.printStackTrace();
                            player.sendMessage(ChatColor.RED + "Unable to produce file. Check console for errors!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You must select two corners to process a region!");
                    }
                } else if (args[0].equalsIgnoreCase("print")) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().equals(Main.REGION_TOOL.getItemMeta())) {
                        RegionTool tool = Main.REGION_TOOL;
                        if (tool.getRegionCorners().isEmpty()) {
                            player.sendMessage("Tool is empty!");
                        } else {
                            for (Location loc : tool.getRegionCorners()) {
                                player.sendMessage(ChatColor.GREEN + "Corner:\n",
                                        "  X: " + loc.getBlockX() + "\n",
                                        "  Y: " + loc.getBlockY() + "\n",
                                        "  Z: " + loc.getBlockZ() + "\n");
                            }
                            if (tool.isRegionComplete()) {
                                player.sendMessage("You have a region that you can process!");
                            }
                        }

                    }
                } else {
                    player.sendMessage("How to use /region command:\n");
                    player.sendMessage("  /region tool: Gives you the region creation tool.\n");
                    player.sendMessage("  /region clear: Resets the tool.\n");
                    player.sendMessage("  /region print: Prints the corners of your region.\n");
                    player.sendMessage("  /region process: Puts the block ordinals within the region into the blocks.csv file.\n");
                }
            }

            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Only players can access this command!");
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    private void generateCSVFile(Player player) throws IOException {
        FileWriter fw = new FileWriter("blocks.csv");
        RegionTool rt = Main.REGION_TOOL;
        Location blockLoc;
        fw.append(rt.getMaxX() - rt.getMinX() + ",");
        fw.append(rt.getMaxY() - rt.getMinY() + ",");
        fw.append(rt.getMaxZ() - rt.getMinZ() + "\n");
        for (int x = rt.getMinX(); x <= rt.getMaxX(); x++) {
            for (int y = rt.getMinY(); y <= rt.getMaxY(); y++) {
                for (int z = rt.getMinZ(); z <= rt.getMaxZ(); z++) {
                    blockLoc = new Location(player.getWorld(), x, y, z);
                    fw.append("" + blockLoc.getBlock().getType().ordinal());
                    fw.append("\n");
                }
            }
        }
        fw.close();
    }
}
