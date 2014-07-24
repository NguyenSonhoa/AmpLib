package ninja.amp.amplib.messenger;

/**
 * Default messages included with AmpLib
 */
public enum DefaultMessage implements Message {
    PREFIX("Prefix", ""),
    RELOAD("Reload", "Reloaded."),

    COMMAND_NOTAPLAYER("Command.NotAPlayer", "&4You must be a player to use this command."),
    COMMAND_NOPERMISSION("Command.NoPermission", "&4You do not have permission to use this command."),
    COMMAND_INVALID("Command.Invalid", "&4%s is not a sub command of %s."),
    COMMAND_USAGE("Command.Usage", "&4Usage: %s"),

    ERROR_NUMBERFORMAT("Error.NumberFormat", "&4Value must be a positive integer."),
    ERROR_BOOLEANFORMAT("Error.BooleanFormat", "&4Value must be true or false.");

    private String message;
    private final String path;
    private final String defaultMessage;

    private DefaultMessage(String path, String defaultMessage) {
        this.message = defaultMessage;
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
