#!/bin/bash
ENV=dev
VERSION=1.0
JMX_PORT=18710
JAVA_HOME=/data02/home/jbossapp/java/jdk1.8.0_101

DIR=`dirname $0`
cd ${DIR}
ENV_CONF="env.sh"
if [ -r "$ENV_CONF" ]; then
    . "$ENV_CONF"
fi

# For heap dumps
ulimit -c unlimited

JDK_OPTS="-server -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark"
JDK_OPTS="${JDK_OPTS} -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:"log/gc.log" -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=10M"

# Overriding the default Java security properties due to Java 8u71 removing support for MD5 in certificates and HPSA uses a MD5 cert.
# See http://www.oracle.com/technetwork/java/javase/8u71-relnotes-2773756.html ...
# New Features and Changes
#
# The following are some of the notable new features and changes in this release:
#
# *MD5 now disabled for X509 Certificate validating*
# MD5 must not be used for digital signatures where collision resistance is required. To prevent the use of X.509 certificates that include an MD5-based digital signature algorithm, MD5 has been added to the jdk.certpath.disabledAlgorithms security property. Applications should upgrade or replace certificates that include an MD5-based digital signature.
#
# Reversing this change is possible by removing MD5 from the jdk.certpath.disabledAlgorithms security property in the java.security file. This is not recommended.
#
# JDK-8141287 (not public)
#
# See also http://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/JSSERefGuide.html#DisabledAlgorithms
# See also http://stackoverflow.com/questions/7615645/ssl-handshake-alert-unrecognized-name-error-since-upgrade-to-java-1-7-0
JDK_OPTS="${JDK_OPTS} -Djsse.enableSNIExtension=false -Djava.security.properties==java.security"

JMX_OPTS="-Djava.net.preferIPv4Stack=true -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=${JMX_PORT} -Dcom.sun.management.jmxremote.rmi.port=${JMX_PORT} -Dcom.sun.management.jmxremote.rmi.port=${JMX_PORT} -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
export JDK_OPTS="${JDK_OPTS} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=hd-`date +%Y%m%d%H%M`.hprof ${JMX_OPTS}"
echo "JDK_OPTS=${JDK_OPTS}"
echo "Starting RestApi version=${VERSION}, environment=${ENV}"
nohup ${JAVA_HOME}/bin/java ${JDK_OPTS} -jar restapi-${VERSION}.jar server application-${ENV}.yml 2>&1 > log/restApi.log &

JAVA_PID=$!
echo $JAVA_PID > .java_pid
tail -100f log/restApi.log
