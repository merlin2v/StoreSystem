/*
 
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natha
 */
package storesystem;
 import java.util.*;
 import java.io.*;
import static storesystem.Client.Registry;
import static storesystem.Client.StoreInventory;
import storesystem.api.*;
 
public class ClientInterface {

    public static final int colwidth = 20;
   /**
    * Starts up the user interface
    * @param args 
    */
    public static void main(String[] args)  {
        
        Scanner console = new Scanner(System.in) ;
     
        Set<String> listOfItems = Registry.getListOfItems();
        int page = 0;
        String [] arr = new String[listOfItems.size()];
        arr = listOfItems.toArray(arr);

        ShoppingCart cart = new ShoppingCart();

        System.out.println("Welcome to Team 3 shopping center!\nYour satisfaction is ours pleasure!\n");    
        starline(colwidth);
        listSelectionMenu(arr, page, cart, console);
        // need to add throw exception when client try to fuck around
   
            
        String sep = "====================================================";
        System.out.println("Thank you for shopping at Team 3 SHOPPING CENTER!");
    }
    /**
     * prints out the list
     * @param arr the array of items available
     * @param page the page number to print
`    * @return the range of items to select from
     */
    public static Item[] printList(String[] arr, int page){
        return printList(arr, page, false);
    }
    /**
     * prints out the list
     * @param arr the array of items available
     * @param page the page number to print
     * @param silent whether to print out the list or not
     * @return the range of items to select from
     */
    public static Item[] printList(String[] arr, int page,boolean silent){
        int itemStart = page * 9;
        Item[] items = new Item[9];
        for (int i = itemStart; i < 9 + itemStart; i++) {
            if (i <arr.length){
                String name =arr[i];
                Item item = Registry.getItem(name);
                items[ i - itemStart ]=item;
                double cost = item.getPrice();
                if( item instanceof ItemPack) {
                    int cnt = ((ItemPack) item).PackCount;
                    if (!silent) System.out.printf("%1s. %2s \t %4spk $%3.2f\n", (i+1),name,cost,cnt);
                    break;
                }else{
                    if (!silent) System.out.printf("%1s. %2s \t $%3.2f\n", (i+1),name, cost);
                }
            } else {
                if (!silent) System.out.println("");
            }
        }
        return items;
    }
       // makes starline
    public static void starline(int colwidth){
        for (int i=0;i<=1+colwidth*3; i++){
            System.out.print("*");
        }
        System.out.println();
    }    

    /**
     * prints the list selection menu
     * @param arr  the array of availible strings for the list
     * @param page the page the list is on (from 1 to 9) 
     * @param cart the cart object that is being used
     * @param console  the input line to receive from the user
     */
    public static void listSelectionMenu(String[] arr, int page, ShoppingCart cart, Scanner console, boolean silent) {
        
        if (silent) System.out.println("Items available are below"); 
        Item[] items = printList(arr,page,silent);

        // Prompt the user for the command
        if (silent) System.out.print("Enter the command\n'C' to get Cart & Check Out,\t'N' to Next Page,\t'P' to Previous Page,\t'Q' to quit: ");
        char cmd = 't';
        cmd = console.next().toLowerCase().charAt(0);
        Item item;
        if(Character.isDigit(cmd) && cmd != '0'){
            item = items[Integer.parseInt(cmd+"")];
            System.out.println("How many would you like? ");
            int quantity = console.nextInt();
            ItemOrder io = new ItemOrder(item, quantity);
            cart.add(io);
            YesNoCheck c;
            do { 
                System.out.println("Would you like to get something else? Yes or No");
                String input = console.nextLine();
                c = new YesNoCheck(input);
                if(c.isYes()) listSelectionMenu(arr, page, cart, console); 
                else if(c.isNo()) goToCart(arr, page, cart, console); 
                else System.out.println("not valid input");
            }while(!c.answered());
        }else{
        // Take appropriate action           
            switch(cmd) {
               case 'c':
                    goToCart(arr, page, cart, console); 
                    break;
               case 'd':
                    
                    break;
               case 'n':
                   //first test for previous page
                   if ((1 + page) * 9 < arr.length) {
                       page++;
                       //listSelectionMenu(arr, page, cart, console);
                   }else{
                       System.out.println("There is no next page to go to");
                   }
                   listSelectionMenu(arr, page, cart, console);
                  break;
               case 'p':
                   //first test for previous page
                   if (page!=0) {
                       page--;
                   }else{
                       System.out.println("There is no previous page to go back to");
                   }
                   listSelectionMenu(arr, page, cart, console);
                  break;
               case 'q':
                  break;
               default:
                  System.out.println("Invalid command");
            }
            starline(colwidth);
        }
    }

    /**
     * prints the list selection menu
     * @param arr  the array of availible strings for the list
     * @param page the page the list is on (from 1 to 9) 
     * @param cart the cart object that is being used
     * @param console the input line to receive from the user
     * @param silent whether or not to hide the prints
     */
    public static void listSelectionMenu(String[] arr, int page, ShoppingCart cart, Scanner console) {
        listSelectionMenu(arr, page, cart, console, false);
    }

    public static void goToCart(String[] arr, int page, ShoppingCart cart, Scanner console) {
        //TODO this 
    }


    public void addToCart(int quantity, String itemName, double price ){ 
        /*Item temp = new Item(itemName, price, quantity);
        totalPrice += (price * quantity);
        itemCount += quantity;
        cart[itemCount] = temp;
        if(itemCount==capacity){
            increaseSize();
        }*/
    }
}
