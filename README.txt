Build and deploy with Maven
build-and-deploy.sh

For the server, store the server configuration files in your home directory under .springws/config/ with the following filenames:
security.server.properties

For the client, store the client configuration files in your home directory under .springws/config/ with the following filenames:
client.dev.properties
client.test.properties
client.prod.properties
security.client.dev.properties

An example copy of the configuration files is available in the config/ directory.

To send a client request
send-client-request.sh dev
send-client-request.sh test

