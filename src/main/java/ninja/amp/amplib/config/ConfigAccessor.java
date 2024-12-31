/*
 * This file is part of AmpLib.
 *
 * Copyright (c) 2014-2017 <http://github.com/ampayne2/>
 *
 * AmpLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AmpLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AmpLib.  If not, see <http://www.gnu.org/licenses/>.
 */
package ninja.amp.amplib.config;

import ninja.amp.amplib.AmpJavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

/**
 * Used to access a YamlConfiguration file.
 */
public class ConfigAccessor {

    private final AmpJavaPlugin plugin;
    private ConfigType configType;
    private File configFile;
    private FileConfiguration fileConfiguration;

    /**
     * Creates a new ConfigAccessor.
     *
     * @param plugin     The {@link ninja.amp.amplib.AmpJavaPlugin} instance.
     * @param configType The {@link ConfigType} of the configuration file.
     * @param parent     The parent file.
     */
    public ConfigAccessor(AmpJavaPlugin plugin, ConfigType configType, File parent) {
        this.plugin = plugin;
        this.configType = configType;
        this.configFile = new File(parent, configType.getFileName());
    }

    /**
     * Reloads the configuration file from disk.
     *
     * @return The ConfigAccessor instance.
     */
    @SuppressWarnings("deprecation")
    public ConfigAccessor reloadConfig() {
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        InputStream defConfigStream = plugin.getResource(configType.getFileName());
        if (defConfigStream != null) {
            try {
                YamlConfiguration defConfig = new YamlConfiguration();
                defConfig.load(defConfigStream.toString());
                fileConfiguration.setDefaults(defConfig);
            } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not load default config from " + configType.getFileName(), e);
            }
        }
        return this;
    }

    /**
     * Gets the config.
     *
     * @return The FileConfiguration.
     */
    public FileConfiguration getConfig() {
        if (fileConfiguration == null) {
            reloadConfig();
        }
        return fileConfiguration;
    }

    /**
     * Saves the config to disk.
     *
     * @return The ConfigAccessor instance.
     */
    public ConfigAccessor saveConfig() {
        if (fileConfiguration != null) {
            try {
                getConfig().save(configFile);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, e);
            }
        }
        return this;
    }

    /**
     * Generates the default config if it hasn't already been generated.
     *
     * @return The ConfigAccessor instance.
     */
    public ConfigAccessor saveDefaultConfig() {
        if (!configFile.exists()) {
            plugin.saveResource(configType.getFileName(), false);
        }
        return this;
    }

    /**
     * Gets the {@link ConfigType}.
     *
     * @return The ConfigAccessor's {@link ConfigType}.
     */
    public ConfigType getConfigType() {
        return configType;
    }

    /**
     * Destroys the ConfigAccessor.
     */
    public void destroy() {
        configType = null;
        configFile = null;
        fileConfiguration = null;
    }

}
