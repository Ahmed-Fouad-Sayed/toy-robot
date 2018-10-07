/**
 * 
 */
package com.fouad.toyrobot.enums;

import lombok.Getter;

/**
 * @author Ahmed
 * 
 * Holds all possible directions
 *
 */
public enum Direction {
	
	NORTH(0),
	EAST(1),
	SOUTH(2),	
	WEST(3);
	
	private Direction(int index) {
		this.index = index;
	}
	
	@Getter private int index;
		
	public static Direction getDirectionFromIndex(int index){
		for(Direction dir : Direction.values()){
			if(dir.getIndex() == index)
				return dir;
		}
		return null;
	}

}
