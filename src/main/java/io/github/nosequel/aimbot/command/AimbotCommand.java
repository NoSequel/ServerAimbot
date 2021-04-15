package io.github.nosequel.aimbot.command;

import io.github.nosequel.aimbot.AimbotHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AimbotCommand implements CommandExecutor {

    private final AimbotHandler aimbotHandler;

    public AimbotCommand(AimbotHandler aimbotHandler) {
        this.aimbotHandler = aimbotHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("aimbot.toggle")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return false;
        }

        if (args.length <= 1) {
            sender.sendMessage(new String[]{
                    ChatColor.RED + "/aimbot status <player>",
                    ChatColor.RED + "/aimbot toggle <player>"
            });
            return false;
        }

        final Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "No player by name \"" + args[1] + "\" found.");
            return false;
        }

        if (args[0].equalsIgnoreCase("status")) {
            sender.sendMessage(ChatColor.RED + target.getName() + "'s aimbot is currently " + (this.aimbotHandler.isToggled(target) ? "enabled" : "disabled"));
        } else if (args[0].equalsIgnoreCase("toggle")) {
            this.aimbotHandler.toggle(target);
            sender.sendMessage(ChatColor.RED + "You have enabled " + target.getName() + "'s aimbot.");
        }

        return true;
    }
}