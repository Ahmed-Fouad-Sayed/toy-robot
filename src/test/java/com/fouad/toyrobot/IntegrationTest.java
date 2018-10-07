/**
 * 
 */
package com.fouad.toyrobot;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * @author Ahmed
 * 
 * Holds integration tests for robot-movement end points
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToyRobotApplication.class)
@WebAppConfiguration
public class IntegrationTest {

	private final String MOVE_COMMAND = "MOVE";
	private final String REPORT_COMMAND = "REPORT";
	private final String LEFT_COMMAND = "LEFT";
	private final String RIGHT_COMMAND = "RIGHT";
	
	@Autowired
    private GenericWebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void getContext() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        assertNotNull(mockMvc);
    }
	
    
    /**
	 * MOVE
	   REPORT
	   Output: ROBOT MISSING
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception{
		String finalOutput = "ROBOT MISSING";
		
		//MOVE
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(MOVE_COMMAND))
				.andExpect(status().isOk());
		
		//REPORT
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(REPORT_COMMAND))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string(equalTo(finalOutput)));
	}
    
    /**
     * 
     * PLACE 0,0,NORTH
	   MOVE
       REPORT
       Output: 0,1,NORTH
     * @throws Exception
     */
	@Test
	public void test2() throws Exception{
		String placeCommand = "PLACE 0,0,NORTH";
		String afterPlacementOutput = "0,0,NORTH";
		String finalOutput = "0,1,NORTH";
		
		//PLACE 0,0,NORTH
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(placeCommand))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(afterPlacementOutput)));
		//MOVE
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(MOVE_COMMAND))
				.andExpect(status().isOk());
		
		//REPORT
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(REPORT_COMMAND))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string(equalTo(finalOutput)));
	}
	
	
	/**
	 * 
	 * PLACE 0,0,NORTH
	   LEFT
       REPORT
       Output: 0,0,WEST
	 * 
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception{
		String placeCommand = "PLACE 0,0,NORTH";
		String afterPlacementOutput = "0,0,NORTH";
		String finalOutput = "0,0,WEST";
		
		//PLACE 0,0,NORTH
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(placeCommand))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(afterPlacementOutput)));
		//LEFT
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(LEFT_COMMAND))
				.andExpect(status().isOk());
		
		//REPORT
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(REPORT_COMMAND))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string(equalTo(finalOutput)));
	}
	
	/**
	 * 
	 * PLACE 1,2,EAST
	   MOVE
       MOVE
       LEFT
       MOVE
       REPORT
       Output: 3,3,NORTH
	 * 
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception{
		String placeCommand = "PLACE 1,2,EAST";
		String afterPlacementOutput = "1,2,EAST";
		String finalOutput = "3,3,NORTH";
		
		//PLACE 0,0,NORTH
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(placeCommand))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(afterPlacementOutput)));
		//MOVE
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(MOVE_COMMAND))
				.andExpect(status().isOk());
		
		//MOVE
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(MOVE_COMMAND))
				.andExpect(status().isOk());
		
		//LEFT
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(LEFT_COMMAND))
				.andExpect(status().isOk());
		
		//MOVE
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(MOVE_COMMAND))
				.andExpect(status().isOk());
		
		//REPORT
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(REPORT_COMMAND))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string(equalTo(finalOutput)));
	}
	
	/**
	 * 
	 * PLACE 0,0,EAST
	   MOVE
       LEFT
       LEFT
       MOVE
       RIGHT
       RIGHT
       REPORT
       Output: 0,0,EAST
	 * 
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception{
		String placeCommand = "PLACE 0,0,EAST";
		String afterPlacementOutput = "0,0,EAST";
		String finalOutput = "0,0,EAST";
		
		//PLACE 0,0,NORTH
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(placeCommand))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(afterPlacementOutput)));
		//MOVE
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(MOVE_COMMAND))
				.andExpect(status().isOk());
				
		//LEFT
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(LEFT_COMMAND))
				.andExpect(status().isOk());
		
		//LEFT
		mockMvc.perform(post("/robot-movement")
						.contentType(MediaType.TEXT_PLAIN)
						.content(LEFT_COMMAND))
						.andExpect(status().isOk());
		//MOVE
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(MOVE_COMMAND))
				.andExpect(status().isOk());

		//RIGHT
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(RIGHT_COMMAND))
				.andExpect(status().isOk());
		
		//RIGHT
		mockMvc.perform(post("/robot-movement")
						.contentType(MediaType.TEXT_PLAIN)
						.content(RIGHT_COMMAND))
						.andExpect(status().isOk());

		
		//REPORT
		mockMvc.perform(post("/robot-movement")
				.contentType(MediaType.TEXT_PLAIN)
				.content(REPORT_COMMAND))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string(equalTo(finalOutput)));
	}
	
	
}
