package me.ampayne2.amplib.config;

/**
 * Default custom config types included with AmpLib.
 */
public enum DefaultConfigType implements ConfigType {
    MESSAGE("Messages.yml");

    private final String fileName;

    private DefaultConfigType(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
