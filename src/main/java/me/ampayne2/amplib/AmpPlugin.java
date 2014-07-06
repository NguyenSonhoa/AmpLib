package me.ampayne2.amplib;

import me.ampayne2.amplib.command.CommandController;
import me.ampayne2.amplib.config.ConfigManager;
import me.ampayne2.amplib.messenger.Messenger;

/**
 * The interface for an AmpJavaPlugin in case it's needed.
 */
public interface AmpPlugin {

    /**
     * Initializes the config, message, and command managers.
     */
    void enableAmpLib();

    /**
     * Destroys the config, message, and command managers.
     */
    void disableAmpLib();

    /**
     * Gets the {@link me.ampayne2.amplib.config.ConfigManager} instance.
     *
     * @return The {@link me.ampayne2.amplib.config.ConfigManager} instance.
     */
    ConfigManager getConfigManager();

    /**
     * Sets the {@link me.ampayne2.amplib.config.ConfigManager} instance.
     *
     * @param configManager The {@link me.ampayne2.amplib.config.ConfigManager} instance.
     */
    void setConfigManager(ConfigManager configManager);

    /**
     * Gets the {@link me.ampayne2.amplib.messenger.Messenger} instance.
     *
     * @return The {@link me.ampayne2.amplib.messenger.Messenger} instance.
     */
    Messenger getMessenger();

    /**
     * Sets the {@link me.ampayne2.amplib.messenger.Messenger} instance.
     *
     * @param messenger The {@link me.ampayne2.amplib.messenger.Messenger} instance.
     */
    void setMessenger(Messenger messenger);

    /**
     * Gets the {@link me.ampayne2.amplib.command.CommandController} instance.
     *
     * @return The {@link me.ampayne2.amplib.command.CommandController} instance.
     */
    CommandController getCommandController();

    /**
     * Sets the {@link me.ampayne2.amplib.command.CommandController} instance.
     *
     * @param commandController The {@link me.ampayne2.amplib.command.CommandController} instance.
     */
    void setCommandController(CommandController commandController);
}
