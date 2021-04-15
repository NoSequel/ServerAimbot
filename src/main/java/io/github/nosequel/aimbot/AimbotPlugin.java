package io.github.nosequel.aimbot;

import io.github.nosequel.aimbot.command.AimbotCommand;
import io.github.nosequel.aimbot.listener.AimbotListener;
import org.bukkit.plugin.java.JavaPlugin;

public class AimbotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final AimbotHandler aimbotHandler = new AimbotHandler();

        this.getServer().getPluginManager().registerEvents(new AimbotListener(aimbotHandler), this);
        this.getCommand("aimbot").setExecutor(new AimbotCommand(aimbotHandler));
    }

}
