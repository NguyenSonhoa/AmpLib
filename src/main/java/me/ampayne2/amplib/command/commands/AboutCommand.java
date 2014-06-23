package me.ampayne2.amplib.command.commands;

import me.ampayne2.amplib.AmpJavaPlugin;
import me.ampayne2.amplib.command.Command;
import me.ampayne2.amplib.messenger.Messenger;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.List;

/**
 * A command that lists some information about the plugin.
 */
public class AboutCommand extends Command {
    private final String header;
    private final List<String> info = new ArrayList<>();

    public AboutCommand(AmpJavaPlugin plugin) {
        super(plugin, "");
        String pluginName = plugin.getName();
        setDescription("Lists some information about " + pluginName);
        setPermission(new Permission(pluginName.toLowerCase() + ".about", PermissionDefault.TRUE));
        setPlayerOnly(false);
        header = Messenger.HIGHLIGHT_COLOR + "<-------<| " + Messenger.PRIMARY_COLOR + "About " + pluginName + " " + Messenger.HIGHLIGHT_COLOR + "|>------->";
        info.add(Messenger.SECONDARY_COLOR + "Author: " + StringUtils.join(plugin.getDescription().getAuthors(), ", "));
        info.add(Messenger.SECONDARY_COLOR + "Version: " + plugin.getDescription().getVersion());
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        sender.sendMessage(header);
        for (String message : info) {
            sender.sendMessage(message);
        }
    }

    /**
     * Adds more info to the about command.
     *
     * @param message The info message to add.
     */
    public void addInfo(String message) {
        info.add(message);
    }
}
