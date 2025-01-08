The main feature of your application is to demonstrate a depot worker processing customers in the queue collecting their parcels. For purposes of a common denominator set of classes, your  program should contain at least the following classes:  

Parcel class - to contain the structure (attributes & methods) of a parcel 

Customer class - to the blueprint of customer objects 

QueofCustomers  class - to enable maintaining a queue/list of customer of objects 

ParcelMap class - uses a map data structure to store parcel objects.  

Log class - to create a single log instance for containing log of events and related data. You can use the inbuilt StringBuffer class to add these events to an instance of it. 

Worker class - to contain the logic for a worker processing a customer, calculating the fee, and releasing the parcel;  

Manager class -  this is your driver class where you instantiate QueueofCustomers, ParcelMap and your GUI. This is where you ideally read in your data files using suitable methods.  

Again, develop your application iteratively, starting with console i/o before progressing to building a GUI. Once you have the above classes setup with suitable details (attributes, methods), commit them to your git repository with a suitable message (e.g., class structure version 1). Then do check if they function as expected using console i/o.  You should choose the most appropriate data structures (chosen from lists, maps, sets) for maintaining collections of your objects. When making your decision, imagine that you have a large number of parcels and customers.

Here is a sample dataset that you can use as input if you wish, or you can come up with your own data files to check that your program is working correctly.  Sample customer data is here Download hereand sample parcel data is here Download here.

Your classes should have suitable instance variables and methods. The calculation of the fee must be in a  calculateFee method so that you can test it easily. This method can be in either the Worker or Manager class.  

The GUI should show the list of parcels still to be processed, the current queue of customers, and the details of the parcel currently being processed by the worker. Each panel should be updated whenever the data which is displayed in that panel is altered.  Do several git commits as you build your GUI. For example, when you build a GUI to display Parcels, commit your new code version to git, do the same when you get to display customers and calculate fees. You should ensure to have at least 4 git commits over the period of your implementation.  

In your implementation, ensure to use the Singleton pattern in a Log class which is used to record every event (i.e. customer joins queue, customer is removed from queue, processing details are displayed etc.) The Log file is finally written to a text file and should be easy to read. Additionally, you should use the MVC pattern to structure your GUI components. The GUI must be built using Swing components (buttons, panels, Frames, etc) to support responsive use of the features of your system. You must not use JavaFX or GUI generators. All GUI code must be written by yourself. 
