# Team 5 Store System
a store system

UML Diagram

![](./Diagram.svg)

The classes:  
+ `ShoppingCart`  
+ `Item`         - probably the most simple one to do. This doesn't have any methods and creation will be done in the ItemRegistry  
+ `ItemOrder`    - Adds a quantity property to Items  
+ `Client`       - client will be a little tricky as you will need to know how to use the API  

+ `Count<T>`     - a class to make arithmetic with ItemOrders easier ItemOrder will implement this class   
+ `ItemRegistry` - this will be a record of all valid Item objects. This will need to be able to load existing Items as well as remove items. This will also keep track of Deal Objects.  
+ `Inventory`  - This will contain the record of all the Items we have. will need to be able to load the information and should verify the items with ItemRegistry.   
+ `Deal<T>`    - bare implementation   
+ `PriceReductionOnMutiSameItemDeal` - implementation of the deal shown in the book  
 Custom Exceptions: (these are fairly easy to make)  
+ `ItemNotFoundException`         - thrown when an item is not found  
+ `NotFoundException`             - thrown when something is not found  
+ `DealNotFoundException`         - thrown when an deal is not found  
+ `CompletedTransactionException` - thrown when attempting to change or resubmit a ShoppingCart that already has been completed  
