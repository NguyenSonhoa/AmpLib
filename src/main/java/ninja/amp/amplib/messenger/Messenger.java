package ninja.amp.amplib.messenger;

import ninja.amp.amplib.AmpJavaPlugin;
import ninja.amp.amplib.config.DefaultConfigType;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages message sending, logging, and debugging.
 */
public class Messenger {
    private final AmpJavaPlugin plugin;
    private boolean debug;
    private Logger log;
    private Map<Class<?>, RecipientHandler> recipientHandlers = new HashMap<>();

    public static ChatColor PRIMARY_COLOR = ChatColor.WHITE;
    public static ChatColor SECONDARY_COLOR = ChatColor.WHITE;
    public static ChatColor HIGHLIGHT_COLOR = ChatColor.WHITE;

    /**
     * Creates a new message manager.
     * <br>
     * Must be created after the {@link ninja.amp.amplib.config.ConfigManager}!
     *
     * @param plugin The {@link ninja.amp.amplib.AmpJavaPlugin} instance.
     */
    public Messenger(AmpJavaPlugin plugin) {
        this.plugin = plugin;
        this.debug = plugin.getConfig().getBoolean("debug", false);
        this.log = plugin.getLogger();
        registerMessages(EnumSet.allOf(DefaultMessage.class));
    }

    /**
     * Adds the {@link ninja.amp.amplib.messenger.Message} defaults to the message config and loads them.
     *
     * @param messageEnum The {@link ninja.amp.amplib.messenger.Message}s to register.
     * @return The {@link ninja.amp.amplib.messenger.Messenger} instance.
     */
    public Messenger registerMessages(EnumSet<? extends Message> messageEnum) {
        FileConfiguration messageConfig = plugin.getConfigManager().getConfig(DefaultConfigType.MESSAGE);
        for (Message message : messageEnum) {
            messageConfig.addDefault(message.getPath(), message.getMessage());
        }
        messageConfig.options().copyDefaults(true);
        plugin.getConfigManager().getConfigAccessor(DefaultConfigType.MESSAGE).saveConfig();
        for (Message message : messageEnum) {
            message.setMessage(ChatColor.translateAlternateColorCodes('&', messageConfig.getString(message.getPath())));
        }
        return this;
    }

    /**
     * Registers a recipient with a RecipientHandler.
     *
     * @param recipientClass   The recipient's class.
     * @param recipientHandler The RecipientHandler.
     * @return The Messenger instance.
     */
    public Messenger registerRecipient(Class recipientClass, RecipientHandler recipientHandler) {
        recipientHandlers.put(recipientClass, recipientHandler);
        return this;
    }

    /**
     * Sends a {@link ninja.amp.amplib.messenger.Message} to a recipient.
     *
     * @param recipient The recipient of the message.
     * @param message   The {@link ninja.amp.amplib.messenger.Message}.
     * @param replace   Strings to replace any occurences of %s in the message with.
     * @return True if the message was sent, else false.
     */
    public boolean sendMessage(Object recipient, Message message, Object... replace) {
        return sendRawMessage(recipient, DefaultMessage.PREFIX + (replace == null ? message.getMessage() : String.format(message.getMessage(), (Object[]) replace)));
    }

    /**
     * Sends a raw message to a recipient.
     *
     * @param recipient The recipient of the message. Type of recipient must be registered.
     * @param message   The message.
     * @return True if the message was sent, else false.
     */
    public boolean sendRawMessage(Object recipient, Object message) {
        if (recipient != null && message != null) {
            for (Class<?> recipientClass : recipientHandlers.keySet()) {
                if (recipientClass.isAssignableFrom(recipient.getClass())) {
                    recipientHandlers.get(recipientClass).sendMessage(recipient, message.toString());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Logs one or more messages to the console.
     *
     * @param level    the level to log the message at.
     * @param messages the message(s) to log.
     */
    public void log(Level level, Object... messages) {
        for (Object message : messages) {
            log.log(level, message.toString());
        }
    }

    /**
     * Decides whether or not to print the stack trace of an exception.
     *
     * @param e the exception to debug.
     */
    public void debug(Exception e) {
        if (debug) {
            e.printStackTrace();
        }
    }

    /**
     * Decides whether or not to print a debug message.
     *
     * @param message The message to debug.
     */
    public void debug(Object message) {
        if (debug) {
            log.log(Level.INFO, message.toString());
        }
    }

    /**
     * Gets the logger.
     *
     * @return The logger.
     */
    public Logger getLogger() {
        return log;
    }

    /**
     * Destroys the Messenger.
     */
    public void destroy() {
        debug = false;
        log = null;
        recipientHandlers.clear();
        recipientHandlers = null;
    }
}
