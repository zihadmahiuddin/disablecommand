package cf.zihad.disablecommand;

import cf.zihad.disablecommand.commands.DisabledCmdsCommand;
import cf.zihad.disablecommand.commands.ReloadConfigCommand;
import cf.zihad.disablecommand.events.OnCommandExecuted;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import cf.zihad.disablecommand.commands.DisableCmdCommand;

public class Plugin extends JavaPlugin {
  @Override
  public void onEnable() {
    getLogger().info("Disable Command (disablecommand) plugin enabled!");

    // Command Handlers
    getCommand("disableCmd").setExecutor(new DisableCmdCommand(this));
    getCommand("disabledCmds").setExecutor(new DisabledCmdsCommand(this));
    getCommand("reloadConfig").setExecutor(new ReloadConfigCommand(this));

    // Event Listeners
    Bukkit.getPluginManager().registerEvents(new OnCommandExecuted(this), this);

    // Copy the default config.yml to the data directory of the plugin
    this.saveDefaultConfig();
  }

  @Override
  public void onDisable() {
    getLogger().info("Disable Command (disablecommand) plugin disabled!");
  }
}
