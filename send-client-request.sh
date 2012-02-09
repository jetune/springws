#!/bin/sh

case "$1" in
  dev)
    echo "Sending a client request to the dev server."
    ;;
  test)
    echo "Sending a client request to the test server."
    ;;
  prod)
    echo "Sending a client request to the prod server."
    ;;
  *)
    echo "Usage: %s {dev|test|prod}\n" "$0"
    exit 1
    ;;
esac

java -Denv=$1 -cp config:lib/* no.nki.springws.client.utils.Client

