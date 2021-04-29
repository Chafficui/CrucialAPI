package io.github.chafficui.CrucialAPI.Interfaces;

import org.bukkit.entity.Player;

import java.util.UUID;

public class CrucialPlayer {
    private final UUID UUID;

    /**
     * @param uuid the uuid of the wanted player.
     */
    public CrucialPlayer(UUID uuid){
        this.UUID = uuid;
    }

    /**
     * @param player the player to get a CrucialPlayer for.
     */
    public CrucialPlayer(Player player){
        this.UUID = player.getUniqueId();
    }

    /**
     * @return the UUID of the CrucialPlayer Object.
     */
    public java.util.UUID getUUID() {
        return UUID;
    }
}
