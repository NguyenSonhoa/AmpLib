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
package ninja.amp.amplib;

import ninja.amp.amplib.command.CommandController;
import ninja.amp.amplib.config.ConfigManager;
import ninja.amp.amplib.messenger.Messenger;
import ninja.amp.amplib.messenger.RecipientHandler;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides access to AmpLib - Extend this instead of JavaPlugin.
 */
public class AmpJavaPlugin extends JavaPlugin implements AmpPlugin {

    private ConfigManager configManager;
    private Messenger messenger;
    private CommandController commandController;

    @Override
    public void enableAmpLib() {
        configManager = new ConfigManager(this);
        messenger = new Messenger(this).registerRecipient(CommandSender.class, new RecipientHandler() {
            @Override
            public void sendMessage(Object recipient, String message) {
                ((CommandSender) recipient).sendMessage(message);
            }
        }).registerRecipient(Server.class, new RecipientHandler() {
            @Override
            public void sendMessage(Object recipient, String message) {
                ((Server) recipient).broadcastMessage(message);
            }
        });
        commandController = new CommandController(this);
    }

    @Override
    public void disableAmpLib() {
        commandController.destroy();
        commandController = null;
        messenger.destroy();
        messenger = null;
        configManager.destroy();
        configManager = null;
    }

    @Override
    public ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public Messenger getMessenger() {
        return messenger;
    }

    @Override
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public CommandController getCommandController() {
        return commandController;
    }

    @Override
    public void setCommandController(CommandController commandController) {
        this.commandController = commandController;
    }

}
