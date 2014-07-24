package ninja.amp.amplib.config;

import ninja.amp.amplib.AmpJavaPlugin;
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
     * @param plugin The {@link ninja.amp.amplib.AmpJavaPlugin} instance.
     */
    public ConfigManager(AmpJavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        registerConfigTypes(EnumSet.allOf(DefaultConfigType.class));
    }

    /**
     * Adds a {@link ninja.amp.amplib.config.ConfigAccessor} for each {@link ConfigType}.
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
     * Adds a {@link ninja.amp.amplib.config.ConfigAccessor} to the config manager.
     *
     * @param configAccessor The {@link ninja.amp.amplib.config.ConfigAccessor}.
     */
    public void addConfigAccessor(ConfigAccessor configAccessor) {
        configs.put(configAccessor.getConfigType(), configAccessor);
    }

    /**
     * Gets a certain {@link ninja.amp.amplib.config.ConfigAccessor}.
     *
     * @param configType The {@link ConfigType} of the config.
     * @return The {@link ninja.amp.amplib.config.ConfigAccessor}.
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
