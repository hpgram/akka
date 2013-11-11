akka
====

To run the server on port 9999
```
mvn -D jetty.port=8081 clean jetty:run
```

Open [http://localhost:8081/reactiveprogramming/client]()

Description
===========
Simple chat service with UI which allows for users to log in and send messages to other participants.

Future plans
=============
* Private Sessions
* Using [EventSourced](https://github.com/eligosource/eventsourced), persist the messages so that the user can log back in and restore the previously received messages.
   
