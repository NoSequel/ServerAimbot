package io.github.nosequel.aimbot.listener;

import io.github.nosequel.aimbot.AimbotHandler;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

public class AimbotListener implements Listener {

    private final AimbotHandler aimbotHandler;

    /**
     * Constructor to make a new aimbot listener object.
     * <p>
     * This class handles the aimbot itself,
     * through the {@link PlayerMoveEvent}.
     *
     * @param aimbotHandler the handler to get the status from
     */
    public AimbotListener(AimbotHandler aimbotHandler) {
        this.aimbotHandler = aimbotHandler;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        if (this.aimbotHandler.isToggled(player)) {
            for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
                if (entity instanceof Player) {
                    final Player target = (Player) entity;
                    final Location point1 = target.getLocation();
                    final Location point2 = player.getLocation();

                    final double dx = point1.getX() - point2.getX();
                    final double dz = point1.getZ() - point2.getZ();
                    float angle = (float) Math.toDegrees(Math.atan2(dz, dx)) + 270;

                    if (angle < 0) {
                        angle += 360;
                    } else if (angle > 360) {
                        angle -= 360;
                    }

                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPosition(
                            0,
                            0,
                            0,
                            angle,
                            point1.getPitch(),
                            new HashSet<>(Arrays.asList(
                                    PacketPlayOutPosition.EnumPlayerTeleportFlags.X,
                                    PacketPlayOutPosition.EnumPlayerTeleportFlags.Y,
                                    PacketPlayOutPosition.EnumPlayerTeleportFlags.Z
                            ))
                    ));

                    break;
                }
            }
        }
    }
}