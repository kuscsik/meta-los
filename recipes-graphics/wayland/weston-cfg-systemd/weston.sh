#!/bin/sh
# Weston

mkdir -p $XDG_RUNTIME_DIR
chmod 0700 $XDG_RUNTIME_DIR

. /etc/profile

# See how we were called.
case "$1" in
  start)
    echo "Starting Weston"
    echo "/usr/bin/weston-launch "
        openvt -s -w -- /usr/bin/weston-launch
    ;;

  stop)
    echo "Stopping Weston"
    pid_weston=`pidof weston-launch`
    kill -9 $pid_weston
    ;;

  status)
    if pidof weston >/dev/null
    then
        echo "Weston: running"
        echo " /usr/bin/weston-launch"
    else
        echo "Weston: not running"
        exit 3
    fi
    exit 0
    ;;

  restart)
    $0 stop
    sleep 1
    $0 start
    ;;

  *)
    echo  "Usage: weston.sh {start|stop|status|restart}"
    exit 1
    ;;
esac

exit 0

