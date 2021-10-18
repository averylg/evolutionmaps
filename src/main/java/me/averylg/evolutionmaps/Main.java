package me.averylg.evolutionmaps;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    public static RegionTool REGION_TOOL;

    @Override
    public void onEnable() {
        // Plugin startup logic
        REGION_TOOL = new RegionTool();
        Bukkit.getPluginManager().registerEvents(new CreateRegionListener(), this);
        getCommand("region").setExecutor(new SpawnRegionToolCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        REGION_TOOL.clearRegionCorners();
        System.out.println("Region Tool cleared on Shutdown!");
    }

}
