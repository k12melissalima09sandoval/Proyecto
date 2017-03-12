SET JAVA_HOME = "C:\Program Files\Java\jre1.8.0_73\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
cd C:\Users\MishaPks\Documents\NetBeansProjects\[Compi2]Proyecto1_201020210\src\Analizadores\Consola
java -jar C:\Users\MishaPks\Documents\NetBeansProjects\[Compi2]Proyecto1_201020210\lib\java-cup-11b.jar -parser ConsolaSintactico -symbols SimbolosConsola Consola_Sintactico.cup