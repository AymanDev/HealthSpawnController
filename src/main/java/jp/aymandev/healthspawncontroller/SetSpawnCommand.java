package jp.aymandev.healthspawncontroller;

import jp.aymandev.healthspawncontroller.config.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (commandSender.isOp()) {
      Player player = Bukkit.getPlayer(strings[0]);

      if (player == null) {
        commandSender.sendMessage("Not found user!");
        return true;
      }

      PlayerData playerData = HealthSpawnCore.getConfigLoader().getPlayerData(player.getUniqueId());
      if (playerData == null) {
        playerData = new PlayerData(player);
      }

      try {
        double x = Double.parseDouble(strings[1]);
        double y = Double.parseDouble(strings[2]);
        double z = Double.parseDouble(strings[3]);
        playerData.setX(x);
        playerData.setY(y);
        playerData.setZ(z);

        HealthSpawnCore.getConfigLoader().savePlayerData(playerData, player);
        commandSender.sendMessage("Respawn point was changed!");
      } catch (NumberFormatException e) {
        commandSender.sendMessage("Coordinates value was wrong!");
        e.printStackTrace();
      }
      return true;
    }

    commandSender.sendMessage("You don't have permissions to use this command!");
    return true;
  }
}
