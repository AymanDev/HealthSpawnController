package jp.aymandev.healthspawncontroller;

import jp.aymandev.healthspawncontroller.config.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.UUID;

public class PlayerListener implements Listener {

  /**
   * When player first join on this server creating data for him and saving it
   *
   * @param event {PlayerJoinEvent} called when player joined the server
   */
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    UUID playerUUID = player.getUniqueId();
    PlayerData playerData = HealthSpawnCore.getConfigLoader().getPlayerData(playerUUID);
    Location location = player.getWorld().getSpawnLocation();

    if (playerData == null) {
      player.setMaxHealth(100);
      player.setHealth(100);
      playerData = new PlayerData(player);

      HealthSpawnCore.getConfigLoader().savePlayerData(playerData, player);
    } else {
      player.setMaxHealth(100);
      player.setHealth(playerData.getCurrentHealth());
    }
  }

  /**
   * When player was respawned after death
   *
   * @param event {PlayerRespawnEvent} called when player respawned
   */
  @EventHandler
  public void onPlayerRespawn(PlayerRespawnEvent event) {
    Player player = event.getPlayer();
    UUID playerUUID = player.getUniqueId();
    PlayerData playerData = HealthSpawnCore.getConfigLoader().getPlayerData(playerUUID);

    player.setHealth(100);
    player.setMaxHealth(100);

    if (playerData != null) {
      Location spawnLocation =
          new Location(player.getWorld(), playerData.getX(), playerData.getY(), playerData.getZ());
      player.teleport(spawnLocation);
    }
  }

  /**
   * When player left the server saving data to config
   *
   * @param event {PlayerQuitEvent} called when player left the server
   */
  @EventHandler
  public void onPlayerLeft(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    UUID playerUUID = player.getUniqueId();
    PlayerData playerData = HealthSpawnCore.getConfigLoader().getPlayerData(playerUUID);

    if (playerData != null) {
      playerData.setCurrentHealth(player.getHealth());
      HealthSpawnCore.getConfigLoader().savePlayerData(playerData, player);
    }
  }
}
