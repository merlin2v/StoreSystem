/*
 
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natha
 */
package storesystem.api;
 import java.util.*;
 import java.io.*;
 
public class ClientInterface {
public static Inventory StoreInventory;
public static ItemRegistry Registry;
public static final int colwidth = 20;
   
      public static void main(String[] args)  {
      Scanner console = new Scanner(System.in) ;
     
    Set<String> listOfItems = Registry.getListOfItems();
    int page = 0;
    String [] arr = new String[listOfItems.size()];
    arr = listOfItems.toArray(arr);
    
    ShoppingCart cart = new ShoppingCart();
    Item item = new Item();
         
       
    System.out.println("Welcome to Team 3 shopping center!\nYour satisfaction is ours pleasure!\n");    
    starline(colwidth);
    System.out.println("Items available are below"); 
    createList(arr,page);

    System.out.println("What product would you like to buy?");
    String name = console.next();
    
    System.out.println("How many would you like? ");
    int quantity = console.nextInt();
    // make a while loop
    
   do { 
   System.out.println("Would you like to get something else? Yes or No");
   String input = console.nextLine();
    YesNoCheck c = new YesNoCheck(input);
   if(c.isYes())
  createList(arr,page); 
   else if(c.isNo())
   
   //No -> go to cart
   addToCart();
   else 
   System.out.println("not valid input");
   
   // need to add throw exception when client try to fuck around
   
         char cmd = 't';   
         while (cmd != 'q'){
            // Prompt the user for the command
            System.out.print("Enter the command\n'C' to get Cart & Check Out,\t'N' to Next Page,\t'P' to Previous Page,\t'Q' to quit: ");
            cmd = console.next().toLowerCase().charAt(0);
            System.out.println(sep); 
            // Take appropriate action           
            switch(cmd) {
               case 'c':
                  System.out.println(addToCart);
                  break;
               case 'd':
                
                  break;
               case 'n':
                  
                  break;
               case 'p':
             //     
                  break;
               case 'q':
                  break;
               default:
                  System.out.println("Invalid command");

 }
 starline(colwidth);
 }
 String sep = "====================================================";
 System.out.println("Thank you for shopping at Team 3 SHOPPING CENTER!");
 }
 
 public static void createList(String[] arr, int page){
    int itemStart = page * 9;
    for (int i = itemStart; i < 9+ itemStart; i++) {
    if (i <arr.length){
    String name =arr[i];
    Item item = Registry.getItem(name);
    double cost = item.getPrice();
    if( item instanceof ItemPack) {
    int cnt = ((ItemPack) item).PackCount;
    System.out.printf("%1s. %2s \t %4spk $%3.2f\n", (i+1),name,cost,cnt);
    break;
    }
   System.out.println("%1s. %2s \t $%3.2f\n",(i+1),name,cost);
   } else {
   System.out.println("");
   }
   
  }
}
       // makes starline
  public static void starline(int colwidth){
      for (int i=0;i<=1+colwidth*3; i++){
         System.out.print("*");
      }
      System.out.println();
   }    


public void addToCart(int quantity, String itemName, double price, ){ 

    Item temp = new Item(itemName, price, quantity);
    totalPrice += (price * quantity);
    itemCount += quantity;
    cart[itemCount] = temp;
    if(itemCount==capacity){
        increaseSize();
    }
}
}
