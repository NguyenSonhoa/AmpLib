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
package ninja.amp.amplib.messenger;

/**
 * Default messages included with AmpLib
 */
public enum DefaultMessage implements Message {
    PREFIX("Prefix", ""),
    RELOAD("Reload", "Reloaded."),

    COMMAND_NOTAPLAYER("Command.NotAPlayer", "&4You must be a player to use this command."),
    COMMAND_NOPERMISSION("Command.NoPermission", "&4You do not have permission to use this command."),
    COMMAND_INVALID("Command.Invalid", "&4%s is not a sub command of %s."),
    COMMAND_USAGE("Command.Usage", "&4Usage: %s"),

    ERROR_NUMBERFORMAT("Error.NumberFormat", "&4Value must be a positive integer."),
    ERROR_BOOLEANFORMAT("Error.BooleanFormat", "&4Value must be true or false.");

    private String message;
    private final String path;
    private final String defaultMessage;

    DefaultMessage(String path, String defaultMessage) {
        this.message = defaultMessage;
        this.path = path;
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getDefault() {
        return defaultMessage;
    }

    @Override
    public String toString() {
        return message;
    }

}
