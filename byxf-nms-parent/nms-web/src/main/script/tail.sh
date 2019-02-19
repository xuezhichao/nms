APP_HOME=/home/coreapp/logs
if [ -n "$1" ]
then
  tail -f $APP_HOME/$1.log
else
  tail -f $APP_HOME/nms-web.log
fi
