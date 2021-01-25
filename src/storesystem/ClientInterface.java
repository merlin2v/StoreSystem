/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natha
 */
 import java.util.*;
 import java.io.*;
 
public class ClientInterface {
  public static final int colwidth = 20; 
    public static void main(String[] args) throws FileNotFoundException {
     Scanner console = new Scanner(System.in) ; 
  //  public static Inventory StoreInventory;
  //  public static ItemRegistry Registry;
    
    
    
    System.out.print("Welcome to Team 3 shopping center!\nYour satisfaction is ours pleasure!\n");
    
    starline(colwidth);
    
   
    System.out.println("Items are available below"); 
   
   //HashMap<Integer, String, Double> itemList = new HashMap<Integer, String, Double>();
   
   
//   itemList.put(1, "Tisue", 1.0);
//   itemList.put(2, "Coca-cola 12pk", 5.19);
//   itemList.put(3, "Frosted Flakes", 3.79);
//   itemList.put(4, "Sour Patch", 2.99);
//   itemList.put(5, "Coffee", 6.89);
//   itemList.put(6, "Doritos", 3.99);
//   itemList.put(7, "Tisue", 1.0);
//   itemList.put(8, "Tisue", 1.0);
//   itemList.put(9, "Tisue", 1.0);
    

System.out.println("What product would you like to buy?");
 System.out.println("Please enter Item number: ");
    int number = console.nextInt();
  System.out.println("How many would you like? ");


System.out.println("Would you like to get something else?");
// if yes go back to itemList
// else 
//return total
starline(colwidth);
System.out.printf("Total price of item(s) with 10% tax " + itemName + price);

 }
 
 
 
  public static void starline(int colwidth){
      for (int i=0;i<=1+colwidth*3; i++){
         System.out.print("*");
      }
      System.out.println();
   }        

}
