package ninja.amp.amplib.command.commands;

import ninja.amp.amplib.AmpJavaPlugin;
import ninja.amp.amplib.command.Command;
import ninja.amp.amplib.messenger.DefaultMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * A command that reloads the plugin.
 */
public class ReloadCommand extends Command {
    private final AmpJavaPlugin plugin;

    public ReloadCommand(AmpJavaPlugin plugin) {
        super(plugin, "reload");
        this.plugin = plugin;
        String pluginName = plugin.getName();
        setDescription("Reloads the " + pluginName + " plugin");
        setPermission(new Permission(pluginName.toLowerCase() + ".reload", PermissionDefault.OP));
        setPlayerOnly(false);
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        plugin.onDisable();
        plugin.onEnable();
        plugin.getMessenger().sendMessage(sender, DefaultMessage.RELOAD);
    }
}
