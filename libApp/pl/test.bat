cd testcases

javac -classpath ..\classes;..\..\bl\classes;..\..\jdbcdl\classes;. *.java

java -classpath ..\classes;..\..\bl\classes;..\..\iClient\classes;..\..\..\naf\nafclient\build\libs\nafclient.jar;..\..\..\naf\nafcommon\build\libs\nafcommon.jar;..\..\..\itext\dist\*;. AuthorModelTestCase
