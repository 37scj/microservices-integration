FROM rabbitmq:3-management

# TCP
EXPOSE 5672
# STOMP
EXPOSE 61613
# MQTT
EXPOSE 1883
# Management
EXPOSE 15672

HEALTHCHECK CMD ["rabbitmq-diagnostics", "check_running", "--timeout", "10"]
RUN rabbitmq-plugins enable --offline rabbitmq_management rabbitmq_mqtt rabbitmq_stomp rabbitmq_shovel rabbitmq_shovel_management
#COPY enabled_plugins /etc/rabbitmq

