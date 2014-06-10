package me.ampayne2.amplib.messenger;

/**
 * An interface for an enum of the messages in a plugin.
 */
public interface Message {

    /**
     * Gets the message string.
     *
     * @return The message.
     */
    String getMessage();

    /**
     * Sets the message string.
     *
     * @param message The message.
     */
    void setMessage(String message);

    /**
     * Gets the path to the message.
     *
     * @return The path to the message.
     */
    String getPath();

    /**
     * Gets the default message string of the message.
     *
     * @return The default message.
     */
    String getDefault();

    @Override
    String toString();
}
