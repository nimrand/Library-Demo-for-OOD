Source Code - Basic Installation & Setup

OS

Max OS X or Linux is recommended.  Windows is also possible, but may require
some additional setup.

Prerequisites:

1. JRE 1.8 or 1.9 - download from http://java.com/download
2. Java SE 8 or 9 - download from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html (Java SE 8)
or http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html (Java SE 9)
3. SBT (Scala Build Tool) - download from http://www.scala-sbt.org/download.html

Windows: In addition, on Windows you will need to configure your default path to include the Java SE's bin folder, so that
javac is visible from the default path.



Running the Application

To launch the web application, simply run this command from the root
source code directory (i.e., same directory as this readme):

sbt run

Note: This will cause the SBT to download additional components needed to
run the code.

It may take a 10 minutes to download all the needed dependencies.  Once the
web server is ready, it will display a message like "(Server started,
use Enter to stop and go back to the console...)".  At this point,
you can connect to the web application on port 9000
(.e.g.: http://localhost:9000).  When you first connect, the server will
compile the source code (which may take several more minutes).  This is
a onee-time only compile.  Once the home page is displayed, you can
interact with the application as normal.



Running Automatic Tests

To run the automated application test script, run the command:

sbt test



See the provided design document for additional details about the installation
and usage of this system.