/**
 * 
 */
package com.fouad.toyrobot.request.handler;

import com.fouad.toyrobot.dto.CommandDto;
import com.fouad.toyrobot.enums.CommandType;
import com.fouad.toyrobot.enums.Direction;
import com.fouad.toyrobot.exception.InvalidCommandException;
import com.fouad.toyrobot.model.Position;

/**
 * @author Ahmed
 *
 *         Contains all command related validations
 */
public class CommandHandler {

	private CommandHandler() {
	}

	public static CommandDto parseCommand(String command) throws InvalidCommandException {
		for (CommandType commandType : CommandType.values()) {
			if (command.matches(commandType.getRegex())) {
				CommandDto commandDto = new CommandDto(commandType);
				if (commandType.equals(CommandType.PLACE)) {
					String[] args = command.split(" ")[1].split(",");
					Position position = new Position(Integer.valueOf(args[0]), Integer.valueOf(args[1]),
							Direction.valueOf(args[2]));
					commandDto.setPostion(position);
				}
				return commandDto;
			}
		}
		throw new InvalidCommandException("Invalid Command!");
	}

}
