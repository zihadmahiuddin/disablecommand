package cf.zihad.disablecommand.commands;

import cf.zihad.disablecommand.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigCommand implements CommandExecutor {
    private final Plugin plugin;

    public ReloadConfigCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandStr, String[] args) {
        if (args.length == 0) {
            this.plugin.reloadConfig();
            commandSender.sendMessage("Config reloaded!");
            return true;
        }
        return false;
    }
}