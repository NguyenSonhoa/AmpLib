package me.ampayne2.amplib.command;

import me.ampayne2.amplib.AmpJavaPlugin;
import org.bukkit.command.CommandSender;

/**
 * A command that can contain child commands and be executed.
 */
public abstract class Command extends CommandGroup {
    private String commandUsage;
    private String description;

    /**
     * Creates a new Command.
     *
     * @param plugin The {@link me.ampayne2.amplib.AmpJavaPlugin} instance.
     * @param name   The name of the command.
     */
    public Command(AmpJavaPlugin plugin, String name) {
        super(plugin, name);
    }

    /**
     * Gets the command's command usage.
     *
     * @return The command usage.
     */
    public String getCommandUsage() {
        return commandUsage;
    }

    /**
     * Sets the command's command usage.
     *
     * @param commandUsage The command usage.
     */
    public void setCommandUsage(String commandUsage) {
        this.commandUsage = commandUsage;
    }

    /**
     * Gets the command's description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the command's description.
     *
     * @param description The description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public abstract void execute(String command, CommandSender sender, String[] args);
}
