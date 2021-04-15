package io.github.nosequel.aimbot;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AimbotHandler {

    private final List<UUID> toggled = new ArrayList<>();

    /**
     * Toggle the status of a player's aimbot.
     *
     * @param player the player to toggle it for
     */
    public void toggle(Player player) {
        if (!this.toggled.remove(player.getUniqueId())) {
            this.toggled.add(player.getUniqueId());
        }
    }

    /**
     * Check if a player's aimbot is toggled.
     *
     * @param player the player to check it for
     * @return whether the aimbot is toggled or not
     */
    public boolean isToggled(Player player) {
        return this.toggled.contains(player.getUniqueId());
    }
}