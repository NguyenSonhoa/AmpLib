package ninja.amp.amplib;

import ninja.amp.amplib.command.CommandController;
import ninja.amp.amplib.config.ConfigManager;
import ninja.amp.amplib.messenger.Messenger;

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
     * Gets the {@link ninja.amp.amplib.config.ConfigManager} instance.
     *
     * @return The {@link ninja.amp.amplib.config.ConfigManager} instance.
     */
    ConfigManager getConfigManager();

    /**
     * Sets the {@link ninja.amp.amplib.config.ConfigManager} instance.
     *
     * @param configManager The {@link ninja.amp.amplib.config.ConfigManager} instance.
     */
    void setConfigManager(ConfigManager configManager);

    /**
     * Gets the {@link ninja.amp.amplib.messenger.Messenger} instance.
     *
     * @return The {@link ninja.amp.amplib.messenger.Messenger} instance.
     */
    Messenger getMessenger();

    /**
     * Sets the {@link ninja.amp.amplib.messenger.Messenger} instance.
     *
     * @param messenger The {@link ninja.amp.amplib.messenger.Messenger} instance.
     */
    void setMessenger(Messenger messenger);

    /**
     * Gets the {@link ninja.amp.amplib.command.CommandController} instance.
     *
     * @return The {@link ninja.amp.amplib.command.CommandController} instance.
     */
    CommandController getCommandController();

    /**
     * Sets the {@link ninja.amp.amplib.command.CommandController} instance.
     *
     * @param commandController The {@link ninja.amp.amplib.command.CommandController} instance.
     */
    void setCommandController(CommandController commandController);
}
