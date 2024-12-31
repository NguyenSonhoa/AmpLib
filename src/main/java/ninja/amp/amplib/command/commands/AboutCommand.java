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
package ninja.amp.amplib.command.commands;

import ninja.amp.amplib.AmpJavaPlugin;
import ninja.amp.amplib.command.Command;
import ninja.amp.amplib.messenger.Messenger;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.List;

/**
 * A command that lists some information about the plugin.
 */
public class AboutCommand extends Command {

    private final String header;
    private final List<String> info = new ArrayList<>();

    public AboutCommand(AmpJavaPlugin plugin) {
        super(plugin, "");
        String pluginName = plugin.getName();
        setDescription("Lists some information about " + pluginName);
        setPermission(new Permission(pluginName.toLowerCase() + ".about", PermissionDefault.TRUE));
        setPlayerOnly(false);
        header = Messenger.HIGHLIGHT_COLOR + "<-------<| " + Messenger.PRIMARY_COLOR + "About " + pluginName + " " + Messenger.HIGHLIGHT_COLOR + "|>------->";
        info.add(Messenger.SECONDARY_COLOR + "Author: " + StringUtils.join(plugin.getDescription().getAuthors(), ", "));
        info.add(Messenger.SECONDARY_COLOR + "Version: " + plugin.getDescription().getVersion());
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        sender.sendMessage(header);
        info.forEach(sender::sendMessage);
    }

    /**
     * Adds more info to the about command.
     *
     * @param message The info message to add.
     */
    public void addInfo(String message) {
        info.add(message);
    }

}
