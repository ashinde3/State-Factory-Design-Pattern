Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:
The project includes 4 states i.e. UNPOPULAR, MILDLY_POPULAR, HIGHLY_POPULAR and ULTRA_POPULAR that extends abstractState class which implements state interface.

State interface has declarations of actions/methods i.e add video, remove video, calculate metrics and ad request respectively.

Results class is responsible for adding video, removing video, calculating metrics i.e popularity score and processing ad requests i.e getting add length.

Result class also implements FileDisplayInterface and StdoutDisplayInterface which is responsible for writing the output to file and stdout respectively.

ChannelContext class has been created that handles the input file processing i.e. processing input line by line, sets the current state to UNPOPULAR and the validations for every input line.

## Justification for Data Structures used in this project:

1. List: ArrayList is used to store States(UNPOPULAR, MILDLY_POPULAR, HIGHLY_POPULAR and ULTRA_POPULAR)
Time complexity: O(n)

2. HashMap: HashMap is used to store and remove videos and its equivalent metrics.
Time complexity: O(1) in average case

3. String of arrays used to split the sentence in Result class
Time complexity: O(n^2)
