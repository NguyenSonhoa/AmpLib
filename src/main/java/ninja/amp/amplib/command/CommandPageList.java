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
package ninja.amp.amplib.command;

import ninja.amp.amplib.AmpJavaPlugin;
import ninja.amp.amplib.messenger.Messenger;
import ninja.amp.amplib.messenger.PageList;

import java.util.ArrayList;
import java.util.List;

/**
 * A PageList that lists all of the commands and their description.
 */
public class CommandPageList extends PageList {

    public CommandPageList(AmpJavaPlugin plugin) {
        super(plugin, "Commands", 8);

        List<String> strings = new ArrayList<>();
        for (CommandGroup command : plugin.getCommandController().getCommands()) {
            for (CommandGroup child : command.getChildren(true)) {
                strings.add(Messenger.PRIMARY_COLOR + ((Command) child).getCommandUsage());
                strings.add(Messenger.SECONDARY_COLOR + "-" + ((Command) child).getDescription());
            }
        }
        setStrings(strings);
    }

}