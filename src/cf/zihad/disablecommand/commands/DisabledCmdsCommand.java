package cf.zihad.disablecommand.commands;

import cf.zihad.disablecommand.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class DisabledCmdsCommand implements CommandExecutor {
    private final Plugin plugin;

    public DisabledCmdsCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandStr, String[] args) {
        if (args.length == 0) {
            StringBuilder output = new StringBuilder();
            ArrayList<String> disabledCommands = (ArrayList<String>) this.plugin.getConfig().getStringList("disabledCommands");
            if (disabledCommands.size() == 0) {
                output.append("There's no disabled commands on this server!");
            } else {
                output.append("The following commands are disabled on this server:");
                for (String disabledCommand : disabledCommands) {
                    output.append("\n");
                    output.append(disabledCommand);
                }
            }
            commandSender.sendMessage(output.toString());
            return true;
        }
        return false;
    }
}