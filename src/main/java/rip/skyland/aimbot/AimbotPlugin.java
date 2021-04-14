package rip.skyland.aimbot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import rip.skyland.aimbot.listener.AimbotListener;

public class AimbotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new AimbotListener(), this);
    }

}
