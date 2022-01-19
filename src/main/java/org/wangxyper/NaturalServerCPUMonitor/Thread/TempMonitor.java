package org.wangxyper.NaturalServerCPUMonitor.Thread;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.List;

public class TempMonitor {
    public static void init(){

    }
    public static void start(BossBar bar, HardwareAbstractionLayer hardware){
        new Thread(()->{
            try {
                while (true){
                    bar.removeAll();
                    List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
                    for (Player player : players){
                        bar.addPlayer(player);
                    }
                    double temp = hardware.getSensors().getCpuTemperature();
                    double voltage = hardware.getSensors().getCpuVoltage();
                    if (temp > 90){
                        double overheat = temp - 90;
                        Bukkit.getServer().getLogger().warning("CPU Overheat:"+overheat);
                        Bukkit.broadcastMessage(ChatColor.RED+"CPU Overheat:"+overheat);
                    }
                    bar.setTitle("CPU Temp:"+temp+"CPU Power voltage:"+voltage);
                    bar.setProgress(temp/100);
                    bar.setColor(getBarColorWithTemp(temp));
                    Thread.sleep(300);
                }
            }catch (Exception ignored){}
        }).start();
    }
    public static BarColor getBarColorWithTemp(double temp){
        if (temp>=80){
            return BarColor.RED;
        }else if (temp >=60){
            return BarColor.YELLOW;
        }else if (temp>=30){
            return BarColor.GREEN;
        }else if(temp>0){
            return BarColor.BLUE;
        }else{
            return BarColor.PINK;
        }
    }
}
