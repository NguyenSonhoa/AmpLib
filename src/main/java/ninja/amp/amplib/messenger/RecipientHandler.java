package ninja.amp.amplib.messenger;

/**
 * Handles sending a message to a recipient.
 */
public abstract class RecipientHandler {

    /**
     * Sends a message to the recipient.
     *
     * @param recipient The recipient.
     * @param message   The message.
     */
    public abstract void sendMessage(Object recipient, String message);
}
