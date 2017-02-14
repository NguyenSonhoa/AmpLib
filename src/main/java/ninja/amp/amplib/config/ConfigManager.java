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
        configs.values().forEach(ConfigAccessor::destroy);
        configs.clear();
        configs = null;
    }

}
