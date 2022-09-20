javadoc -d .\make\ -sourcepath .\src\main\java -subpackages org.example

javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\org\example\HeapSort.java

mkdir .\make\jar
jar cf .\make\jar\HeapSort.jar -C .\make\bin .