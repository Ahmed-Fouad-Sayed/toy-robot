/**
 * 
 */
package com.fouad.toyrobot.model;

import java.io.Serializable;

import com.fouad.toyrobot.enums.Direction;
import com.fouad.toyrobot.enums.RotationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ahmed 
 * 
 * Holds the position information
 *
 */
@AllArgsConstructor
public class Position implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8886445708227472538L;
	
	@Getter @Setter private int xAxis;
	@Getter @Setter private int yAxis;
	@Getter @Setter private Direction direction;

	public void incrementX(){
		this.xAxis ++;
	}
	
	public void decrementX(){
		this.xAxis --;
	}
	
	public void incrementY(){
		this.yAxis ++;
	}
	
	public void decrementY(){
		this.yAxis --;
	}
	
	public void rotateDirection(RotationType rotationType){
		switch(rotationType){
			case LEFT:
				if((this.direction.getIndex()-1) < 0)
					this.direction = Direction.WEST;
				else
					this.direction = Direction.getDirectionFromIndex(this.direction.getIndex()-1);
				break;
			case RIGHT:
				if((this.direction.getIndex()+1) > 3)
					this.direction = Direction.NORTH;
				else
					this.direction = Direction.getDirectionFromIndex(this.direction.getIndex()+1);
				break;
		}
	}
	
	public Position clone() throws CloneNotSupportedException{
		return (Position)super.clone();
	}
}
