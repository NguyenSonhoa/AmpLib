package ninja.amp.amplib.command.commands;

import ninja.amp.amplib.AmpJavaPlugin;
import ninja.amp.amplib.command.Command;
import ninja.amp.amplib.messenger.PageList;
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
            pageNumber = PageList.getPageNumber(args[0]);
        }
        plugin.getCommandController().getPageList().sendPage(pageNumber, sender);
    }
}
