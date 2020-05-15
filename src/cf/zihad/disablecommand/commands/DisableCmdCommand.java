package cf.zihad.disablecommand.commands;

import cf.zihad.disablecommand.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class DisableCmdCommand implements CommandExecutor {
    private final Plugin plugin;

    public DisableCmdCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandStr, String[] args) {
        if (args.length == 1) {
            ArrayList<String> disabledCommands = (ArrayList<String>) this.plugin.getConfig().getStringList("disabledCommands");
            if (disabledCommands.contains(args[0].toLowerCase())) {
                disabledCommands.remove(args[0].toLowerCase());
                this.plugin.getConfig().set("disabledCommands", disabledCommands);
                this.plugin.saveConfig();
                commandSender.sendMessage("Enabled " + args[0] + " command!");
            } else {
                disabledCommands.add(args[0].toLowerCase());
                this.plugin.getConfig().set("disabledCommands", disabledCommands);
                this.plugin.saveConfig();
                commandSender.sendMessage("Disabled " + args[0] + " command!");
            }

            return true;
        }
        return false;
    }
}