package org.wangxyper.NaturalServerCPUMonitor;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;
import org.wangxyper.NaturalServerCPUMonitor.Thread.TempMonitor;
import org.wangxyper.NaturalServerCPUMonitor.commands.CommandGeTSystemInfo;
import oshi.SystemInfo;
public final class Main extends JavaPlugin {
    public static BossBar bar = Bukkit.createBossBar("", BarColor.PINK, BarStyle.SEGMENTED_10, BarFlag.CREATE_FOG);
    @Override
    public void onEnable() {
        // Plugin startup logic
        TempMonitor.init();
        TempMonitor.start(bar,new SystemInfo().getHardware());
        getServer().getPluginCommand("systeminfo").setExecutor(new CommandGeTSystemInfo());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
