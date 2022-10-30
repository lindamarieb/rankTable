# rankTable

Command-line application that will calculate the ranking table for a league.

## Input/output
The input and output will be text - either using stdin/stdout or taking filenames on the command line.
The input contains results of games, one per line. See "Sample input" for details.
The output will be ordered from most to least points, following the format specified in "Expected output".
 
## The rules
In this league, a draw (tie) is worth 1 point and a win is worth 3 points. A loss is worth 0 points.
If two or more teams have the same number of points, they should have the same rank and be
printed in alphabetical order (as in the tie for 3rd place in the sample data).

## Input
The input is provided via text files. The filename must be specified on the command line.
The input contains results of games, one per line. See "Sample input" for details.

### Sample input
- Lions 3, Snakes 3
- Tarantulas 1, FC Awesome 0
- Lions 1, FC Awesome 1
- Tarantulas 3, Snakes 1
- Lions 4, Grouches 0

## Output
The output will be ordered from most to least points, following the format specified in "Expected output".

### Expected output
1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pts

## Dependencies
Maven (including Java 8, Junit and exec-maven-plugin)

## Usage
To build, test and run the app use Maven as follows:

```
mvn clean compile test exec:java -Dexec.args="success1.txt"
```

These commands can also be executed separately.

To compile
```
mvn clean compile
```

To run unit tests
```
mvn test
```

To execute the app without Maven
```
java -classpath target/classes RankTable success1.txt
```

Where success1.txt is the name if the file with containing the match result text.

Test files for success and failure cases are included in the root folder.
