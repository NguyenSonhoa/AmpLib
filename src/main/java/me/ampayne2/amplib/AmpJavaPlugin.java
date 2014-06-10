package me.ampayne2.amplib;

import me.ampayne2.amplib.command.CommandController;
import me.ampayne2.amplib.config.ConfigManager;
import me.ampayne2.amplib.messenger.Messenger;
import me.ampayne2.amplib.messenger.RecipientHandler;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides access to AmpLib - Extend this instead of JavaPlugin.
 */
public class AmpJavaPlugin extends JavaPlugin {
    private ConfigManager configManager = null;
    private Messenger messenger = null;
    private CommandController commandController = null;

    /**
     * Initializes the config, message, and command managers.
     */
    public void enableAmpLib() {
        configManager = new ConfigManager(this);
        messenger = new Messenger(this).registerRecipient(CommandSender.class, new RecipientHandler() {
            @Override
            public void sendMessage(Object recipient, String message) {
                ((CommandSender) recipient).sendMessage(message);
            }
        }).registerRecipient(Server.class, new RecipientHandler() {
            @Override
            public void sendMessage(Object recipient, String message) {
                ((Server) recipient).broadcastMessage(message);
            }
        });
        commandController = new CommandController(this);
    }

    /**
     * Destroys the config, message, and command managers.
     */
    public void disableAmpLib() {
        commandController.destroy();
        commandController = null;
        messenger.destroy();
        messenger = null;
        configManager.destroy();
        configManager = null;
    }

    /**
     * Gets the {@link me.ampayne2.amplib.config.ConfigManager} instance.
     *
     * @return The {@link me.ampayne2.amplib.config.ConfigManager} instance.
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * Sets the {@link me.ampayne2.amplib.config.ConfigManager} instance.
     *
     * @param configManager The {@link me.ampayne2.amplib.config.ConfigManager} instance.
     */
    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Gets the {@link me.ampayne2.amplib.messenger.Messenger} instance.
     *
     * @return The {@link me.ampayne2.amplib.messenger.Messenger} instance.
     */
    public Messenger getMessenger() {
        return messenger;
    }

    /**
     * Sets the {@link me.ampayne2.amplib.messenger.Messenger} instance.
     *
     * @param messenger The {@link me.ampayne2.amplib.messenger.Messenger} instance.
     */
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    /**
     * Gets the {@link me.ampayne2.amplib.command.CommandController} instance.
     *
     * @return The {@link me.ampayne2.amplib.command.CommandController} instance.
     */
    public CommandController getCommandController() {
        return commandController;
    }

    /**
     * Sets the {@link me.ampayne2.amplib.command.CommandController} instance.
     *
     * @param commandController The {@link me.ampayne2.amplib.command.CommandController} instance.
     */
    public void setCommandController(CommandController commandController) {
        this.commandController = commandController;
    }
}
