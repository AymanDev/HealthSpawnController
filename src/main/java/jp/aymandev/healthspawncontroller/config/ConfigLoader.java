package jp.aymandev.healthspawncontroller.config;

import com.google.gson.Gson;
import jp.aymandev.healthspawncontroller.HealthSpawnCore;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.UUID;

public class ConfigLoader {

  private File configFolder;
  private Gson gson;

  public ConfigLoader(HealthSpawnCore pluginInstance) throws Exception {
    configFolder = pluginInstance.getDataFolder();

    if (!configFolder.exists()) {
      boolean result = configFolder.mkdir();

      if (!result) {
        throw new Exception("Can't create plugin folder!");
      }
    }
  }

  /**
   * Saving plkayer data to file
   *
   * @param playerData Player data which have to save
   * @param player
   */
  public void savePlayerData(PlayerData playerData, Player player) {
    String json = gson.toJson(playerData);
    String fileName = player.getUniqueId() + ".json";

    File configFile = new File(configFolder, fileName);
    try {
      FileWriter fileWriter = new FileWriter(configFile);
      fileWriter.write(json);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * * Player data getter
   *
   * @param uuid of the player
   * @return {PlayerData} object parsed from JSON
   */
  public PlayerData getPlayerData(UUID uuid) {
    File playerConfig = new File(configFolder, uuid.toString() + ".json");

    if (!playerConfig.exists()) {
      return null;
    }

    try {
      Reader fileReader = new FileReader(playerConfig);
      return gson.fromJson(fileReader, PlayerData.class);
    } catch (FileNotFoundException e) {
      return null;
    }
  }
}
