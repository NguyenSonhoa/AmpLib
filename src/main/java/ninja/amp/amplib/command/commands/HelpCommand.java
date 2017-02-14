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
import ninja.amp.amplib.messenger.PageList;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * A command that lists all plugin commands.
 */
public class HelpCommand extends Command {

    private final AmpJavaPlugin plugin;

    public HelpCommand(AmpJavaPlugin plugin) {
        super(plugin, "help");
        this.plugin = plugin;
        String pluginName = plugin.getName();
        setDescription("Lists all " + pluginName + " commands");
        setPermission(new Permission(pluginName.toLowerCase() + ".help", PermissionDefault.TRUE));
        setPlayerOnly(false);
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        int pageNumber = 1;
        if (args.length == 1) {
            pageNumber = PageList.getPageNumber(args[0]);
        }
        plugin.getCommandController().getPageList().sendPage(pageNumber, sender);
    }

}
