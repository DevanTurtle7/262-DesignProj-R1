javac -classpath commons-cli-1.5.0.jar;commons-lang3-3.12.0.jar;opencsv-5.6.jar -d bin src/com/swen262/*.java src\com\swen262\database\*.java src\com\swen262\DBSearches\*.java src\com\swen262\exceptions\*.java src\com\swen262\librarySearches\*.java src\com\swen262\model\*.java src\com\swen262\personalLibrary\*.java src\com\swen262\util\*.java src\com\swen262\view\*.java
mkdir bin\com\swen262\database\data
xcopy src\com\swen262\database\data\*.* bin\com\swen262\database\data\.
java -classpath commons-cli-1.5.0.jar;commons-lang3-3.12.0.jar;opencsv-5.6.jar;bin com.swen262.Main