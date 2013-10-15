akka
====

To run the server on port 9999
```
mvn -D jetty.port=9999 clean jetty:run
```

Requirements

1) helloworld
  - Async request and receive messages
  - echo message back to the sender
  - provide REST endpoint for client to call 
         POST /reactiveprogramming/sendecho 
           {
             "target": "Dean", 
             "message": "hello"
          } 
  - expect success of this call to return HTTP/1.1 200 OK
       { 
          "status": "ack",
          "message": "Dean says hello"
       }
- if any failure, return HTTP failed status (500 etc)

2) expect load test: 10 users 10 request per sec


3) chat server
If you get here
provide client interface (command line is fine), this is what it should look like:

> connect("Dean")
Dean> 
> connect("Jim")
Jim>
> connect("Alex")
Alex> tell("Dean", "what's up")  //target message
Dean> Alex said: what's up 

Alex> yell("hello, everyone") //broadcast message
Dean> Alex said: hello, everyone
Jim> Alex said: hello, everyone
Alex> I said: hello, everyone
   
