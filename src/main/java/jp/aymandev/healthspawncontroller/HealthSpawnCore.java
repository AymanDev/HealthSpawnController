package jp.aymandev.healthspawncontroller;

import jp.aymandev.healthspawncontroller.config.ConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class HealthSpawnCore extends JavaPlugin {

  private static HealthSpawnCore instance;
  private static ConfigLoader configLoader;

  @Override
  public void onEnable() {
    instance = this;
    try {
      configLoader = new ConfigLoader(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static HealthSpawnCore getInstance() {
    return instance;
  }

  public static ConfigLoader getConfigLoader() {
    return configLoader;
  }
}
