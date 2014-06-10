package me.ampayne2.amplib.command;

import me.ampayne2.amplib.AmpJavaPlugin;
import me.ampayne2.amplib.messenger.Messenger;
import me.ampayne2.amplib.messenger.PageList;

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