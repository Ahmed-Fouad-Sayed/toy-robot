package com.fouad.toyrobot.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Ahmed
 *
 * Holds all the possible commands for a robot
 */
public enum CommandType {
	
	PLACE("^PLACE\\s{1}\\d+\\,\\d+\\,((NORTH)|(EAST)|(WEST)|(SOUTH))$"),
	MOVE("(MOVE)"),
	LEFT("(LEFT)"),
	RIGHT("(RIGHT)"),
	REPORT("(REPORT)");

	private CommandType(String regex) {
		this.regex = regex;
	}
	
	@Setter @Getter String regex;
	
	
}
