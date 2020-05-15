package cf.zihad.disablecommand.events;

import cf.zihad.disablecommand.Plugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;

public class OnCommandExecuted implements Listener {
    private final Plugin plugin;

    public OnCommandExecuted(Plugin plugin) {
        this.plugin = plugin;
    }

    // Prevent execution of disabled commands from command blocks and command block minecarts
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockRedstone(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        Material blockType = block.getType();
        ArrayList<String> disabledCommands = (ArrayList<String>) plugin.getConfig().getStringList("disabledCommands");
        if (blockType == Material.COMMAND_BLOCK ||
                blockType == Material.CHAIN_COMMAND_BLOCK ||
                blockType == Material.REPEATING_COMMAND_BLOCK) {
            BlockState blockState = block.getState();
            if (blockState instanceof CommandBlock) {
                if (event.getNewCurrent() != 0 && event.getOldCurrent() == 0) {
                    String command = ((CommandBlock) block.getState()).getCommand().substring(1).split(" ")[0].toLowerCase();
                    if (disabledCommands.contains(command)) {
                        event.setNewCurrent(0);
                    }
                }
            }
        }
        else if (blockType == Material.COMMAND_BLOCK_MINECART) {
            BlockState blockState = block.getState();
            if (blockState instanceof CommandMinecart) {
                if (event.getNewCurrent() != 0 && event.getOldCurrent() == 0) {
                    String command = ((CommandMinecart) block.getState()).getCommand().substring(1).split(" ")[0].toLowerCase();
                    if (disabledCommands.contains(command)) {
                        event.setNewCurrent(0);
                    }
                }
            }
        }
    }

    // Prevent execution of disabled commands from the server console
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerCommand(ServerCommandEvent event) {
        ArrayList<String> disabledCommands = (ArrayList<String>) plugin.getConfig().getStringList("disabledCommands");
        String command = event.getCommand().split(" ")[0].toLowerCase();
        if (disabledCommands.contains(command)) {
            event.setCancelled(true);
            event.getSender().sendMessage("Sorry, this command is disabled!");
        }
    }

    // Prevent execution of disabled commands from by players
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        ArrayList<String> disabledCommands = (ArrayList<String>) plugin.getConfig().getStringList("disabledCommands");
        String command = event.getMessage().substring(1).split(" ")[0].toLowerCase();
        if (disabledCommands.contains(command)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Sorry, this command is disabled!");
        }
    }
}
