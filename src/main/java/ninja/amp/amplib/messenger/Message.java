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
 * An interface for an enum of the messages in a plugin.
 */
public interface Message {

    /**
     * Gets the message string.
     *
     * @return The message.
     */
    String getMessage();

    /**
     * Sets the message string.
     *
     * @param message The message.
     */
    void setMessage(String message);

    /**
     * Gets the path to the message.
     *
     * @return The path to the message.
     */
    String getPath();

    /**
     * Gets the default message string of the message.
     *
     * @return The default message.
     */
    String getDefault();

    @Override
    String toString();

}
