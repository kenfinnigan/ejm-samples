# Multicast

On OSX, you'll probably need to enable multicast on localhost:

    sudo route add -net 230.0.0.0/4 127.0.0.1

# Ubuntu 16.04.6 LTS Note

The examples were tested successfully using JDK8. With the Oracle licensing model, getting JDK8 installed in Ubuntu can be done manually as follows:

1. Download the Java 8 SE JDK from Oracle (this requires a login)
2. Open a terminal 
3. mkdir /opt/jdk
4. sudo tar -zxf TAR_FILE_NAME_HERE -C /opt/jdk

Then, set JAVA_HOME=/opt/jdk/jdk1.8.0_202/

Or use whatever path matches your JDK.

To check your installation:

$JAVA_HOME/bin/javac -version

javac 1.8.0_202
