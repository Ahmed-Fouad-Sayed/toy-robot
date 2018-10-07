/**
 * 
 */
package com.fouad.toyrobot.model;

import org.springframework.stereotype.Component;

import com.fouad.toyrobot.enums.Direction;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ahmed
 * 
 *         Holds robot related information
 *
 */
@Component
public class Robot {

	@Getter
	@Setter
	private Position position;
	@Getter
	@Setter
	private boolean placed = false;
	
	public Robot(){
		this.position = new Position(0, 0, Direction.NORTH);
	}

	public String tellPosition() {
		if (!placed) {
			return "ROBOT MISSING";
		}

		return this.position.getXAxis() + "," + this.position.getYAxis() + "," + this.position.getDirection();
	}

	public Position getNextPosition() throws CloneNotSupportedException {
		Position newPostion = this.position.clone();
		if (placed) {
			switch (this.position.getDirection()) {
			case EAST:
				newPostion.incrementX();
				break;
			case WEST:
				newPostion.decrementX();
				break;
			case NORTH:
				newPostion.incrementY();
				break;
			case SOUTH:
				newPostion.decrementY();
				break;
			}
		}
		return newPostion;
	}

}
