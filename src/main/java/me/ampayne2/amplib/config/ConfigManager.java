package me.ampayne2.amplib.config;

import me.ampayne2.amplib.AmpJavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains custom plugin configs.
 */
public class ConfigManager {
    private final AmpJavaPlugin plugin;
    private Map<ConfigType, ConfigAccessor> configs = new HashMap<>();

    /**
     * Creates a new config manager.
     *
     * @param plugin The {@link me.ampayne2.amplib.AmpJavaPlugin} instance.
     */
    public ConfigManager(AmpJavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        registerConfigTypes(EnumSet.allOf(DefaultConfigType.class));
    }

    /**
     * Adds a {@link me.ampayne2.amplib.config.ConfigAccessor} for each {@link ConfigType}.
     *
     * @param configTypes The {@link ConfigType}s to register.
     */
    public void registerConfigTypes(EnumSet<? extends ConfigType> configTypes) {
        File dataFolder = plugin.getDataFolder();
        for (ConfigType configType : configTypes) {
            addConfigAccessor(new ConfigAccessor(plugin, configType, dataFolder).saveDefaultConfig());
        }
    }

    /**
     * Adds a {@link me.ampayne2.amplib.config.ConfigAccessor} to the config manager.
     *
     * @param configAccessor The {@link me.ampayne2.amplib.config.ConfigAccessor}.
     */
    public void addConfigAccessor(ConfigAccessor configAccessor) {
        configs.put(configAccessor.getConfigType(), configAccessor);
    }

    /**
     * Gets a certain {@link me.ampayne2.amplib.config.ConfigAccessor}.
     *
     * @param configType The {@link ConfigType} of the config.
     * @return The {@link me.ampayne2.amplib.config.ConfigAccessor}.
     */
    public ConfigAccessor getConfigAccessor(ConfigType configType) {
        return configs.get(configType);
    }

    /**
     * Gets a certain FileConfiguration.
     *
     * @param configType The {@link ConfigType} of the config.
     * @return The FileConfiguration.
     */
    public FileConfiguration getConfig(ConfigType configType) {
        return configs.get(configType).getConfig();
    }

    /**
     * Destroys the ConfigManager.
     */
    public void destroy() {
        for (ConfigAccessor config : configs.values()) {
            config.destroy();
        }
        configs.clear();
        configs = null;
    }
}
