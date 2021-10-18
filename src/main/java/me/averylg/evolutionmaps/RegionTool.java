package me.averylg.evolutionmaps;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class RegionTool extends ItemStack {

    private List<Location> regionCorners;

    public RegionTool() {
        super(Material.DIAMOND_AXE);
        ItemMeta meta = this.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 100, true);
        meta.setLocalizedName("RegionCreationTool");

        this.setItemMeta(meta);

        this.regionCorners = new LinkedList<>();
    }

    public void clearRegionCorners() {
        this.regionCorners = new LinkedList<>();
    }

    public void addRegionCorner(Location loc) {
        regionCorners.add(loc);
    }

    public boolean isRegionComplete() {
        return this.regionCorners.size() == 2;
    }

    public List<Location> getRegionCorners() {
        return this.regionCorners;
    }

    public int getMinX() {
        if (!this.isRegionComplete()) {
            throw new NoSuchElementException("The region must be complete to get Minimum and Maximum Coordinates!");
        }
        int min = this.regionCorners.get(0).getBlockX();
        if (this.regionCorners.get(1).getBlockX() < min) {
            return this.regionCorners.get(1).getBlockX();
        } else {
            return min;
        }
    }

    public int getMaxX() {
        if (!this.isRegionComplete()) {
            throw new NoSuchElementException("The region must be complete to get Minimum and Maximum Coordinates!");
        }
        int max = this.regionCorners.get(0).getBlockX();
        if (this.regionCorners.get(1).getBlockX() > max) {
            return this.regionCorners.get(1).getBlockX();
        } else {
            return max;
        }
    }

    public int getMinY() {
        if (!this.isRegionComplete()) {
            throw new NoSuchElementException("The region must be complete to get Minimum and Maximum Coordinates!");
        }
        int min = this.regionCorners.get(0).getBlockY();
        if (this.regionCorners.get(1).getBlockY() < min) {
            return this.regionCorners.get(1).getBlockY();
        } else {
            return min;
        }
    }

    public int getMaxY() {
        if (!this.isRegionComplete()) {
            throw new NoSuchElementException("The region must be complete to get Minimum and Maximum Coordinates!");
        }
        int max = this.regionCorners.get(0).getBlockY();
        if (this.regionCorners.get(1).getBlockY() > max) {
            return this.regionCorners.get(1).getBlockY();
        } else {
            return max;
        }
    }

    public int getMinZ() {
        if (!this.isRegionComplete()) {
            throw new NoSuchElementException("The region must be complete to get Minimum and Maximum Coordinates!");
        }
        int min = this.regionCorners.get(0).getBlockZ();
        if (this.regionCorners.get(1).getBlockZ() < min) {
            return this.regionCorners.get(1).getBlockZ();
        } else {
            return min;
        }
    }

    public int getMaxZ() {
        if (!this.isRegionComplete()) {
            throw new NoSuchElementException("The region must be complete to get Minimum and Maximum Coordinates!");
        }
        int max = this.regionCorners.get(0).getBlockZ();
        if (this.regionCorners.get(1).getBlockZ() > max) {
            return this.regionCorners.get(1).getBlockZ();
        } else {
            return max;
        }
    }

}
