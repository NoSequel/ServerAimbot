# Aimbot
This is a server-sided aimbot plugin for 1.8 NMS.

## How does it work
* 1. It gets all nearby entities within a 3 block range xyz=(3, 3, 3)
* 2. It gets the player the nearest to the player.
* 3. It gets the angle between the target and the player itself.
* 4. It changes the player's pitch & yaw accordingly by sending a ``PacketPlayOutPosition``

## Why is there no 1.7 support?
As far as I know, there is no possible way to send a ``PacketPlayOutPosition`` without updating the X, Y, and Z coordinates through 1.7 NMS, which is why I decided to only make 1.8 support.

## What is the purpose in making this plugin?
Nothing - I only made it for fun, and out of boredom.