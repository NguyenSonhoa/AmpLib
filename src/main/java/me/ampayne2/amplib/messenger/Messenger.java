package me.ampayne2.amplib.messenger;

import me.ampayne2.amplib.AmpJavaPlugin;
import me.ampayne2.amplib.config.DefaultConfigType;
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
     * Must be created after the {@link me.ampayne2.amplib.config.ConfigManager}!
     *
     * @param plugin The {@link me.ampayne2.amplib.AmpJavaPlugin} instance.
     */
    public Messenger(AmpJavaPlugin plugin) {
        this.plugin = plugin;
        this.debug = plugin.getConfig().getBoolean("debug", false);
        this.log = plugin.getLogger();
        registerMessages(EnumSet.allOf(DefaultMessage.class));
    }

    /**
     * Adds the {@link me.ampayne2.amplib.messenger.Message} defaults to the message config and loads them.
     *
     * @param messageEnum The {@link me.ampayne2.amplib.messenger.Message}s to register.
     * @return The {@link me.ampayne2.amplib.messenger.Messenger} instance.
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
     * Sends a {@link me.ampayne2.amplib.messenger.Message} to a recipient.
     *
     * @param recipient The recipient of the message.
     * @param message   The {@link me.ampayne2.amplib.messenger.Message}.
     * @param replace   Strings to replace any occurences of %s in the message with.
     * @return True if the message was sent, else false.
     */
    public boolean sendMessage(Object recipient, Message message, String... replace) {
        return sendRawMessage(recipient, DefaultMessage.PREFIX + (replace == null ? message.getMessage() : String.format(message.getMessage(), (Object[]) replace)));
    }

    /**
     * Sends a raw message to a recipient.
     *
     * @param recipient The recipient of the message. Type of recipient must be registered.
     * @param message   The message.
     * @return True if the message was sent, else false.
     */
    public boolean sendRawMessage(Object recipient, String message) {
        if (recipient != null && message != null) {
            for (Class<?> recipientClass : recipientHandlers.keySet()) {
                if (recipientClass.isAssignableFrom(recipient.getClass())) {
                    recipientHandlers.get(recipientClass).sendMessage(recipient, message);
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
    public void log(Level level, String... messages) {
        for (String message : messages) {
            log.log(level, message);
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
     * @param message the message to debug.
     */
    public void debug(String message) {
        if (debug) {
            log.log(Level.INFO, message);
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
