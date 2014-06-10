package me.ampayne2.amplib.messenger;

/**
 * Default messages included with AmpLib
 */
public enum DefaultMessage implements Message {
    PREFIX("Prefix", ""),
    RELOAD("Reload", "Reloaded."),

    COMMAND_NOTAPLAYER("Command.NotAPlayer", "&4You must be a player to use this command."),
    COMMAND_NOPERMISSION("Command.NoPermission", "&4You do not have permission to use this command."),
    COMMAND_INVALID("Command.Invalid", "&4%s is not a sub command of %s."),
    COMMAND_USAGE("Command.Usage", "&4Usage: %s");

    private String message;
    private final String path;
    private final String defaultMessage;

    private DefaultMessage(String path, String defaultMessage) {
        this.path = path;
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getDefault() {
        return defaultMessage;
    }

    @Override
    public String toString() {
        return message;
    }
}
