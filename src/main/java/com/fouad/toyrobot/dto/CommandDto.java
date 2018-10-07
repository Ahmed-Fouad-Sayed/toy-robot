/**
 * 
 */
package com.fouad.toyrobot.dto;

import java.io.Serializable;

import com.fouad.toyrobot.enums.CommandType;
import com.fouad.toyrobot.model.Position;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ahmed
 *
 * Represent the Data Transfer Object of command entity
 */
public class CommandDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8058520616032226876L;

	@Getter @Setter private CommandType type;
	//Optional -- Only used for PLACE command
	@Getter @Setter private Position postion;
	
	public CommandDto() {
	}
	
	public CommandDto(CommandType type){
		this.type = type;
	}
	
	public CommandDto(CommandType type, Position position){
		this.type = type;
		this.postion = position;
	}
}
