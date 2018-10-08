# toy-robot

Is an application simulating a robot movement on a square table of dimension 5 x 5 units.

## Exposed Endpoints

### POST /robot-movement

A RESTful API controling the robot movement through accepting plain text commands represeting the required action (including reporting the current state of the robot).


** Important hint about REST API design:

Currently the API offers a single end point for controlling the toy robot. Alternatively, it could be separated into 4 end points

```javascript
POST /robot/position - with post body (x:int, y:int, direction:String)
PUT /robot/move
POST /robot/rotate - with post body (rotationType:String --LEFT|RIGHT)
GET /robot/position
```

#### Consumes/Produces

text/plain

#### Sample

* Sample Request 1
PLACE 0,0,EAST
* Sample Response 1
0,0,EAST

* Sample Request 2
MOVE
* Sample Response 2
1,0,EAST

* Sample Request 3
REPORT
* Sample Response 3
1,0,EAST

#### Test Cases

* Skipping PLACE command
```javascript
MOVE
REPORT
Output: ROBOT MISSING
```

* Moving 1 step towards NORTH
```javascript
PLACE 0,0,NORTH
MOVE
REPORT
Output: 0,1,NORTH
```

* Rotating LEFT from NORTH to WEST 
```javascript
PLACE 0,0,NORTH
LEFT
REPORT
Output: 0,0,WEST
```
* Moving 2 steps towards EAST starting from point (1,2), and rotate LEFT towards NORTH, and move one more step
```javascript
PLACE 1,2,EAST
MOVE
MOVE
LEFT
MOVE
REPORT
Output: 3,3,NORTH
```
* Move 1 step towards EAST, and rotate LEFT twice towards WEST, and move one step then rotate back towards EAST 
```javascript
PLACE 0,0,EAST
MOVE
LEFT
LEFT
MOVE
RIGHT
RIGHT
REPORT
Output: 0,0,EAST
```
* Starting at (0,0) facing EAST, keep moving trying to exceed the edge limit, and rotate LEFT towards NORTH doing the same, till the robot reaches the NORTH EAST most corner 
```javascript
PLACE 0,0,EAST
MOVE
MOVE
MOVE
MOVE
MOVE
MOVE
MOVE
LEFT
MOVE
MOVE
MOVE
MOVE
MOVE
MOVE
MOVE
REPORT
Output: 4,4,NORTH
```

## Requirements
* Java 8
* JUnit and Mockito for testing
* Maven as dependency package manager

## Compile, Test, Run, and Package
* Compile: mvn compile
* Test: mvn test
* Run: mvn spring-boot:run
* Packaging: mvn package

## Postman colleciton

https://www.getpostman.com/collections/eee8aeebdc91860f5355