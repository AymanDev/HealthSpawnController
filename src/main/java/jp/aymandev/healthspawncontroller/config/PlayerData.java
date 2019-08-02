package jp.aymandev.healthspawncontroller.config;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerData {
  private double x;
  private double y;
  private double z;
  private double currentHealth;

  PlayerData() {}

  public PlayerData(Player player) {
    Location playerLocation = player.getLocation();
    this.x = playerLocation.getX();
    this.y = playerLocation.getY();
    this.z = playerLocation.getZ();
    this.currentHealth = player.getHealth();
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getZ() {
    return z;
  }

  public void setZ(double z) {
    this.z = z;
  }

  public double getCurrentHealth() {
    return currentHealth;
  }

  public void setCurrentHealth(double currentHealth) {
    this.currentHealth = currentHealth;
  }
}
