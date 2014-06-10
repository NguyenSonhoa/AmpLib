package me.ampayne2.amplib.command;

import me.ampayne2.amplib.AmpJavaPlugin;
import me.ampayne2.amplib.messenger.DefaultMessage;
import me.ampayne2.amplib.messenger.PageList;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Contains and controls commands.
 */
public class CommandController implements TabExecutor {
    private final AmpJavaPlugin plugin;
    private Set<CommandGroup> commands = new HashSet<>();
    private PageList pageList = null;

    /**
     * Creates a new command controller.
     *
     * @param plugin The {@link me.ampayne2.amplib.AmpJavaPlugin} instance.
     */
    public CommandController(AmpJavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        for (CommandGroup command : commands) {
            if (command.getName().equalsIgnoreCase(cmd.getName())) {
                String subCommand = args.length > 0 ? args[0] : "";
                if (command.hasChildCommand(subCommand)) {
                    if (subCommand.equals("")) {
                        command.execute(subCommand, sender, args);
                    } else {
                        String[] newArgs;
                        if (args.length == 1) {
                            newArgs = new String[0];
                        } else {
                            newArgs = new String[args.length - 1];
                            System.arraycopy(args, 1, newArgs, 0, args.length - 1);
                        }
                        command.execute(subCommand, sender, newArgs);
                    }
                } else {
                    plugin.getMessenger().sendMessage(sender, DefaultMessage.COMMAND_INVALID, "\"" + subCommand + "\"", "\"" + command.getName() + "\"");
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        for (CommandGroup command : commands) {
            if (command.getName().equalsIgnoreCase(cmd.getName())) {
                if (args.length > 0) {
                    int commandAmount = 1;
                    for (String arg : args) {
                        if (command.hasChildCommand(arg)) {
                            command = command.getChildCommand(arg);
                            commandAmount++;
                        }
                    }
                    String[] newArgs;
                    if (args.length == 1) {
                        newArgs = new String[0];
                    } else {
                        newArgs = new String[args.length - commandAmount];
                        System.arraycopy(args, commandAmount, newArgs, 0, args.length - commandAmount);
                    }
                    return command.getTabCompleteList(newArgs);
                }
                return command.getTabCompleteList(args);
            }
        }
        return new ArrayList<>();
    }

    /**
     * Gets the commands in the CommandController.
     *
     * @return The commands.
     */
    public Set<CommandGroup> getCommands() {
        return commands;
    }

    /**
     * Adds a command to the CommandController.
     *
     * @param command The command to add.
     */
    public void addCommand(CommandGroup command) {
        commands.add(command);
        plugin.getCommand(command.getName()).setExecutor(this);
        pageList = new CommandPageList(plugin);
    }

    /**
     * Gets the PageList of commands in the CommandController.
     *
     * @return The PageList of commands.
     */
    public PageList getPageList() {
        return pageList;
    }

    /**
     * Destroys the CommandController.
     */
    public void destroy() {
        for (CommandGroup command : commands) {
            plugin.getCommand(command.getName()).setExecutor(null);
        }
        commands.clear();
        pageList = null;
    }
}
