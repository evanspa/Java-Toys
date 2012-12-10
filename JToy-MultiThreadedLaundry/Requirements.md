## Functional Requirements of Laundry System Simulator

+ Wet clothes producers - what they do is they are in a constant loop of adding
wet clothes to the dryer.  They work in spurts...meaning a producer will be
doing nothing, and then, all of a sudden, will "wake up" and will put a handful
of wet clothes into the dryer.  The producer then goes back to sleep...and will
wake up again at some random point and repeat this.

+ The dryer machine can never get full.  It can hold an unlimited amount of
clothing articles.

+ The laundry oracle will be monitoring the dryer machine.  When it senses that
there are dry clothes, it will notify the clothes-folders.  The laundry oracle
does not constantly check the dryer machine...instead it mostly sleeps, and will
periodically "wake up" and look at the contents of the machine to see if there
are any clothes ready to be folded.  There have to be at least 5 articles of
clothing or more that are dry for the oracle to bother with notifying the
clothing-folders.

+ There is just one dryer machine, and just one laundry system oracle.

+ A clothing-folder mostly sleeps - only when it is notified by the oracle that
there are clothes to be folded will it wake up.  The oracle simply tells the
clothing-folder there are clothes that are dry and ready to be removed from
the dryer machine and folded; it doesn't tell the folder how many articles or
which ones.  The clothing-folder will have to inspect the dryer machine herself
to determine which clothing articles are dry.  The oracle and clothing-folder
use the same algorithm/process for determining which items are dry and which
ones are not.

+ Only one entity can "access" the dryer machine at a time.  If the oracle
is inspecting its contents, then producers and folders must wait.  If a folder
is removing dry clothes from the machine and folding them, the oracle and
producers must wait.

+ The oracle, folders, and producers have equal privilege when it comes to
accessing the dryer machine.  I.e. nobody has a higher-priority over another when
it comes to waiting in line to access the dryer machine.

+ Folders only access the dryer machine to get the dry clothes out; they should
folder the clothes "on the side" so that the dryer machine can be accessed by 
other resources as quickly as possible.  So, although only 1 resource is allowed
to access the dryer at a time, folders and producers should be able to 
"do their work" in parallel.

## Non-functional Requirements of Laundry System Simulator

+ Strict separation between simulation logic and view.  It should be possible
to create both graphical and non-graphical views of the simulator without the
simulator logic itself being aware.  

+ Loose coupling between entities from the standpoint of memory
management.  E.g., clothing-folder objects should NOT have an instance member
reference to the dryer machine (and vice-versa).  The reference count of the
dryer machine instance should be orthogonal to the number of producers,
folders, and the oracle.   

+ Obviously from the functional requirements there is going to be quite a
bit of "event raising" and "notifying of interested parties."  Produce a 
design such that the implementation of such event-raising and notification
is pluggable.  E.g., it could be that the entire simulation will run 
within a single JVM.  However, we may want want to run the simulation over
several JVMs (on different physical machines potentially) - the strategy in
which events will be raised and listeners notified will very likely be quite
different in these 2 contexts.  We want to make sure the design can facilitate
this elegantly and seamlessly.
