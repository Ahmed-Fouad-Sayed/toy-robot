/**
 * 
 */
package com.fouad.toyrobot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fouad.toyrobot.dto.CommandDto;
import com.fouad.toyrobot.enums.RotationType;
import com.fouad.toyrobot.model.Position;
import com.fouad.toyrobot.model.Robot;
import com.fouad.toyrobot.model.Table;

import lombok.Synchronized;

/**
 * @author Ahmed
 *
 *         A service for Robot related operations
 */
@Service
public class RobotService {

	@Autowired
	Table table;
	
	@Autowired
	private Robot toyRobot;

	@Synchronized
	public String handleRobotMovements(CommandDto commandDto) throws CloneNotSupportedException {
		switch (commandDto.getType()) {
		case PLACE:
			toyRobot.setPosition(commandDto.getPostion());
			toyRobot.setPlaced(true);
			break;
		case MOVE:
			Position possibleNewPosition = toyRobot.getNextPosition();
			if(isAValidMove(possibleNewPosition))
				toyRobot.setPosition(possibleNewPosition);
			break;
		case LEFT:
			toyRobot.getPosition().rotateDirection(RotationType.LEFT);
			break;
		case RIGHT:
			toyRobot.getPosition().rotateDirection(RotationType.RIGHT);
			break;
		default:
			break;
		}
		return this.toyRobot.tellPosition();
	}
	
	private boolean isAValidMove(Position newPosition){
		if(newPosition.getXAxis() <= (table.getSize()-1) && newPosition.getYAxis() <= (table.getSize()-1)){
			return true;
		}
		return false;
	}

}
