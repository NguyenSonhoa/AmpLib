package me.ampayne2.amplib.command.commands;

import me.ampayne2.amplib.AmpJavaPlugin;
import me.ampayne2.amplib.command.Command;
import me.ampayne2.amplib.messenger.DefaultMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * A command that lists all plugin commands.
 */
public class HelpCommand extends Command {
    private final AmpJavaPlugin plugin;

    public HelpCommand(AmpJavaPlugin plugin) {
        super(plugin, "help");
        this.plugin = plugin;
        String pluginName = plugin.getName();
        setDescription("Lists all " + pluginName + " commands");
        setPermission(new Permission(pluginName.toLowerCase() + ".help", PermissionDefault.TRUE));
        setPlayerOnly(false);
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        int pageNumber = 1;
        if (args.length == 1) {
            try {
                pageNumber = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                plugin.getMessenger().sendMessage(sender, DefaultMessage.ERROR_NUMBERFORMAT);
                return;
            }
        }
        plugin.getCommandController().getPageList().sendPage(pageNumber, sender);
    }
}
