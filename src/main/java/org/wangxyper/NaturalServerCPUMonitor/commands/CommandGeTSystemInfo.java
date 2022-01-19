package org.wangxyper.NaturalServerCPUMonitor.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import oshi.SystemInfo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

public class CommandGeTSystemInfo implements CommandExecutor {
    public static ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SystemInfo info = new SystemInfo();
        sender.sendMessage("System Info");
        sender.sendMessage("CPU Family Name"+ info.getHardware().getProcessor().getFamily());
        sender.sendMessage("CPU Name"+info.getHardware().getProcessor().getName());
        sender.sendMessage("CPU Load"+info.getHardware().getProcessor().getSystemLoadAverage());
        sender.sendMessage("CPU temp"+info.getHardware().getSensors().getCpuTemperature());
        sender.sendMessage("Memory system available"+info.getHardware().getMemory().getAvailable());
        sender.sendMessage("Memory swap total"+info.getHardware().getMemory().getSwapTotal());
        sender.sendMessage("System:"+info.getHardware().getComputerSystem().getModel());
        sender.sendMessage("Server version"+ Bukkit.getServer().getVersion());
        sender.sendMessage("Server name"+Bukkit.getServer().getName());
        sender.sendMessage("Online players"+Bukkit.getServer().getOnlinePlayers().size());
        sender.sendMessage("All alive threads:");
        List<String> names = new ArrayList<>();
        threadMXBean.setThreadCpuTimeEnabled(true);
        long[] ids = threadMXBean.getAllThreadIds();
        for(long id:ids){
            names.add(threadMXBean.getThreadInfo(id).getThreadName());
        }
        for (String name:names){
            sender.sendMessage(name);
        }
        return true;
    }
}
