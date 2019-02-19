APP_MAINCLASS=com.byxf.nms.NmsOmsMain

APP_HOME=`pwd`

export JAVA_HOME=/home/coreapp/jdk1.8.0_144
export JRE_HOME=$JAVA_HOME/jre
export CLASS_PATH=$JAVA_HOME/lib:$JRE_HOME/lib
export PATH=$JAVA_HOME/bin:$PATH


CLASSPATH=$APP_HOME/resources
for i in "$APP_HOME"/lib/*.jar; do
  CLASSPATH="$CLASSPATH":"$i"
done
java -Djava.awt.headless=true  -classpath $CLASSPATH $APP_MAINCLASS $* > /dev/null &