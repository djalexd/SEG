SEG
===

Storage for event-based graphs. The event chosen for this demo is quite
simple (containing only a timestamp and value). The core is represented by
a disruptor tied to a spring-integration channel. For demo purposes, the
channel is the simplest one there is, and should be easily reconfigured for
any other type (like jms or http, for a stand-alone server).

The demo class of this project is SEGDemo. It instantiates the disruptor, along
with input channel and handlers. These handlers store events they receive asynchronously
in a H2 memory database (sql provided). A number of handlers (2) was setup just
to demo the simplicity of concurrently handling a huge number of events.

===
Unfortunately, the code using Disruptor library blocks when its ring buffer is about
to be wrapped around (disruptor setup here has 16384 sequence numbers). We could not
wrap this for demo.