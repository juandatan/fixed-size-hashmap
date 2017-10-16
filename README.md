# fixed-size-hashmap
---- INTRODUCTION ----
This is my implementation of a fixed-sized hashmap. I used an underlying self-balancing binary search tree, to ensure that lookup is always O(log n), and space-complexity is kept to n, where n is the current size of the hashmap (as opposed to instantiating an array, for example, of the entire size of the hashmap at its instantiation). I ensured that the binary search tree is balanced not upon every add/remove but at regular intervals so that the amortized runtime of adding/removing remains close to constant.

---- HOW TO COMPILE AND RUN ----
First, navigate to the /src folder. Then, in the command line:
1. Run "javac cli/*.java" to compile the cli package.
2. Run "javac hashmap/*.java" to compile the hashmap package. This should work and the FixedSizeHashMapTester should also 
compile if the CLASSPATH of your local system points to the right directory where your junit.jar file lives; if it does 
not work, then simply run "javac hashmap/FixedSizeHashMap.java" to compile the program without the JUnit tests.
3. Run "java cli.Main" to start the program.

Available commands in the command line interface:
1. [construct <size>] - constructs a new FixedSizeHashMap of size <size>. Must be run before FixedSizeHashMap functionality can be peformed.
2. [set <key> <value>] - adds <value> to the hashmap with key <key>.
3. [get <key>] - retrieves, but does not remove, the <value> associated with <key> in the hashmap.
4. [delete <key>] - removes from the hashmap, and returns, the <value> associated with <key> in the hashmap.
5. [load] - outputs the load of the hashmap (defined as current size / capacity).
6. [quit] - exits the program.

---- TESTS ----
JUnit tests have been included in the hashmap package. To test (assuming FixedSizeHashMapTester is successfully compiled):
1. Navigate to the /src folder.
2. Run "java -cp .:<path to junit.jar file>:<path to hamcrest-core.jar file> org.junit.runner.JUnitCore hashmap.FixedSizeHashMapTester"

The JUnit tests all pass when run on my system. 
