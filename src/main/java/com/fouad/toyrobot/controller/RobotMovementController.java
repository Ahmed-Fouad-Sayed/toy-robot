/**
 * 
 */
package com.fouad.toyrobot.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fouad.toyrobot.dto.CommandDto;
import com.fouad.toyrobot.exception.InvalidCommandException;
import com.fouad.toyrobot.request.handler.CommandHandler;
import com.fouad.toyrobot.service.RobotService;

/**
 * @author Ahmed
 * 
 *         A Rest Controller for robot commands
 *
 */
@RestController
@RequestMapping("/robot-movement")
public class RobotMovementController {

	@Autowired
	RobotService robotService;


	@PostMapping(consumes="text/plain", produces="text/plain")
	public ResponseEntity<String> processRobotCommand(@NotNull @RequestBody String command) throws InvalidCommandException, CloneNotSupportedException {
		CommandDto parsedCommand = CommandHandler.parseCommand(command);
		String result = robotService.handleRobotMovements(parsedCommand);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
