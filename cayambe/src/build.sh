#!/bin/sh


OLDCP=$CLASSPATH
CLASSPATH=""

TOOLS_LIB=""
J2EE_LIB=""
STRUTS_LIB=""
TILES_LIB=""
LOG4J_LIB=""
ANT_LIB=""
SERVLET_LIB=""

ERROR=""


if [ ! -f "${JAVA_HOME}/bin/java" ]
then
        ERROR="${ERROR}\n$JAVA_HOME/bin/java does not exist\nset JAVA_HOME to the java home\n"
fi

if [ ! -d "${DEPLOY}" ]
then
	ERROR="${ERROR}\n$DEPLOY does not exist\nset DEPLOY to your Deployment Dir\n"
fi

if [ -f "${JAVA_HOME}/lib/tools.jar" ]
then
	TOOLS_LIB="${JAVA_HOME}/lib/tools.jar"
else
	ERROR="${ERROR}\n${JAVA_HOME}/lib/tools.jar does not exist\nset JAVA_HOME to the java home\n"
fi

if [ -f "${JBOSS_HOME}/client/jboss-j2ee.jar" ]
then
	J2EE_LIB=${JBOSS_HOME}/client/jboss-j2ee.jar
elif [ -f "${J2EE_HOME}/lib/j2ee.jar" ]
then
	J2EE_LIB="${J2EE_HOME}/lib/j2ee.jar"
else
	ERROR="${ERROR}\nThe j2ee.jar can not be found.  Either set the J2EE_HOME to your j2eesdk dir\nor set the JBOSS_HOME to your JBOSS dir\n"
fi

if [ -f "${JBOSS_HOME}/server/all/lib/javax.servlet.jar" ]
then
	SERVLET_LIB="${JBOSS_HOME}/server/all/lib/javax.servlet.jar"

elif [ -f "${SERVLET_HOME}/lib/servlet.jar" ]
then
	SERVLET_LIB="${SERVLET_HOME}/lib/servlet.jar"

elif [ -f "${SERVLET_HOME}/common/lib/servlet.jar" ]
then
        SERVLET_LIB="${SERVLET_HOME}/common/lib/servlet.jar"

elif [ -f "${TOMCAT_HOME}/lib/servlet.jar" ]
then
	SERVLET_LIB="${TOMCAT_HOME}/lib/servlet.jar"
else
	ERROR="${ERROR}\nThe servlet.jar can not be found.  Either set the TOMCAT_HOME to your Tomcat dir\nor set the JBOSS_HOME to your JBOSS dir\nor set the SERVLET_HOME to your servlet home dir\n"
fi

if [ -f "${STRUTS_HOME}/lib/struts.jar" ]
then
	STRUTS_LIB="${STRUTS_HOME}/lib/struts.jar"
        TILES_LIB="${STRUTS_HOME}/lib/tiles.jar"
else
	ERROR="${ERROR}\n$STRUTS_HOME/lib/struts.jar does not exist\necho set STRUTS_HOME to the Struts Home\n"
fi

if [ -f "${ANT_HOME}/lib/ant.jar" ]
then
	ANT_LIB="${ANT_HOME}/lib/ant.jar"
else
        ERROR="${ERROR}\n$ANT_HOME/lib/ant.jar does not exist\nset ANT_HOME to the Ant Home\n"
fi

if [ -f "${LOG4J_HOME}/lib/log4j.jar" ]
then
	LOG4J_LIB="${LOG4J_HOME}/lib/log4j.jar"
else
        ERROR="${ERROR}\n$LOG4J_HOME/lib/log4j.jar does not exist\nset LOG4J_HOME to the Log4j Home\n"
fi

if [ x"${ERROR}" != "x" ]
then
	echo -e "${ERROR}"
	exit 1
fi

if [ `echo $OSTYPE | grep cygwin` ]
then
  PS=";"
else
  PS=":"
fi

CP="${TOOLS_LIB}${PS}${J2EE_LIB}${PS}${SERVLET_LIB}${PS}${STRUTS_LIB}${PS}${TILES_LIB}${PS}${LOG4J_LIB}${PS}${ANT_LIB}"

echo "CLASSPATH: $CP"

$JAVA_HOME/bin/java -classpath $CP org.apache.tools.ant.Main $*

CLASSPATH=$OLDCP
