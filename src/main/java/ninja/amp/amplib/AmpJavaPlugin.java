package ninja.amp.amplib;

import ninja.amp.amplib.command.CommandController;
import ninja.amp.amplib.config.ConfigManager;
import ninja.amp.amplib.messenger.Messenger;
import ninja.amp.amplib.messenger.RecipientHandler;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides access to AmpLib - Extend this instead of JavaPlugin.
 */
public class AmpJavaPlugin extends JavaPlugin implements AmpPlugin {
    private ConfigManager configManager = null;
    private Messenger messenger = null;
    private CommandController commandController = null;

    @Override
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

    @Override
    public void disableAmpLib() {
        commandController.destroy();
        commandController = null;
        messenger.destroy();
        messenger = null;
        configManager.destroy();
        configManager = null;
    }

    @Override
    public ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public Messenger getMessenger() {
        return messenger;
    }

    @Override
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public CommandController getCommandController() {
        return commandController;
    }

    @Override
    public void setCommandController(CommandController commandController) {
        this.commandController = commandController;
    }
}
