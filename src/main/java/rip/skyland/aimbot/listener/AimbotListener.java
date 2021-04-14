package rip.skyland.aimbot.listener;

import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final Optional<Player> players = player.getNearbyEntities(3, 0, 3).stream()
                .filter(entity -> entity instanceof Player)
                .map(Player.class::cast).findFirst();

        if (players.isPresent()) {
            final Player target = players.get();
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
        }
    }
}