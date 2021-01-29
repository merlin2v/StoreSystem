/*
*  
 */

package storesystem;
import java.util.*;
import java.io.*;
import static storesystem.Client.Registry;
import static storesystem.Client.StoreInventory;
import storesystem.api.*;
import storesystem.api.deals.*;
 
/**
 * The main client interface
 * @author Dat
 */
public class ClientInterface {

    private static final int COL_WIDTH = 20;
   /**
    * Starts up the user interface
    * @param args the application arguments
    */
    public static void runApplication(String[] args)  {
        
        Scanner console = new Scanner(System.in) ;
     
        Set<String> listOfItems = Registry.getListOfItems();
        int page = 0;
        String [] arr = new String[listOfItems.size()];
        arr = listOfItems.toArray(arr);

        ShoppingCart cart = new ShoppingCart(StoreInventory);

        System.out.println("Welcome to Team 3 shopping center!\nYour satisfaction is ours pleasure!\n");    
        starline(COL_WIDTH);
        listSelectionMenu(arr, page, cart, console);
        String sep = "====================================================";
        System.out.println("Thank you for shopping at Team 3 SHOPPING CENTER!");
    }
    /**
     * prints out the list
     * @param arr the array of items available
     * @param page the page number to print
     * @return the range of items to select from
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
                    if (!silent) System.out.printf("%1s. %2s \t %3spk $%4.2f\n", ((i - itemStart)+1),name,cnt,cost);
                    break;
                }else{
                    if (!silent) System.out.printf("%1s. %2s \t $%3.2f\n", ((i - itemStart)+1),name, cost);
                }
            } else {
                if (!silent) System.out.println("");
            }
        }
        return items;
    }
    /**
     * makes starline
     * @param colwidth width of the starline
     */
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
     * @param silent if true, list will not print
     */
    public static void listSelectionMenu(String[] arr, int page, ShoppingCart cart, Scanner console, boolean silent) {
        
        if (!silent) System.out.println("Items available are below"); 
        Item[] items = printList(arr,page,silent);

        // Prompt the user for the command
        if (!silent) System.out.print("Enter the command\n'C' to get Cart & Check Out,\t'N' to Next Page,\t'P' to Previous Page,\t'Q' to quit: ");
        char cmd = 't';
        cmd = console.nextLine().toLowerCase().charAt(0);
        Item item;
        if(Character.isDigit(cmd) && cmd != '0'){
            item = items[Integer.parseInt(cmd+"")-1];
            if (item==null) {
                System.out.println("not a valid selection");
                System.out.println("");
                System.out.println("Press Enter to Continue...");
                console.nextLine();
                listSelectionMenu(arr, page, cart, console);
                return;
            }
            
            System.out.println("How many would you like? ");
            boolean isDone =false;
            Integer quantity = null;
            while (!isDone) {
                String line=console.nextLine();
                try{
                    quantity = Integer.parseInt(line);
                    isDone =true;
                }catch(NumberFormatException e){
                    System.out.println("Please enter a valid number");
                }
                
            }
            ItemOrder io = new ItemOrder(item, quantity);
            try {
                cart.addItems(io);
            } catch (NotEnoughInOfStockException ex) {
                if (ex.StockQuantity==0) {
                    System.out.println("Sorry we don't have any of that item in stock at the moment.");
                }else{
                    System.out.println("Sorry we currently only have ("+ex.StockQuantity+") in stock");
                }
                System.out.println("");
                System.out.println("Press Enter to Continue...");
                console.nextLine();
                listSelectionMenu(arr, page, cart, console);//
                return;
            }
            YesNoCheck c;
            do { 
                System.out.println("Would you like to get something else? Yes or No");
                String input = console.nextLine();
                c = new YesNoCheck(input);
                if(c.isYes()) {
                    listSelectionMenu(arr, page, cart, console); 
                    return;
                }
                else if(c.isNo()) {
                    goToCart(arr, page, cart, console); ///FLAG
                    return;
                }
                else System.out.println("not valid input");
                
            }while(!c.answered());
        }else{
        // Take appropriate action           
            switch(cmd) {
               case 'c':
                    goToCart(arr, page, cart, console); 
                    return;
               case 'd':
                    
                    break;
               case 'n':
                   //first test for previous page
                   if ((1 + page) * 9 < arr.length) {
                       page++;
                       //listSelectionMenu(arr, page, cart, console);
                   }else{
                        System.out.println("There is no next page to go to");
                        System.out.println("");
                        System.out.println("Press Enter to Continue...");
                        console.nextLine();
                   }
                   listSelectionMenu(arr, page, cart, console);
                   return;
                  
               case 'p':
                   //first test for previous page
                   if (page!=0) {
                       page--;
                   }else{
                        System.out.println("There is no previous page to go back to");
                        System.out.println("");
                        System.out.println("Press Enter to Continue...");
                        console.nextLine();
                   }
                   listSelectionMenu(arr, page, cart, console);
                  return;
               case 'q':
                  break;
               default:
                  System.out.println("Invalid command");
                  listSelectionMenu(arr, page, cart, console);
                  return;
            }
        }
    }

    /**
     * prints the list selection menu
     * @param arr  the array of availible strings for the list
     * @param page the page the list is on (from 1 to 9) 
     * @param cart the cart object that is being used
     * @param console the input line to receive from the user
     */
    public static void listSelectionMenu(String[] arr, int page, ShoppingCart cart, Scanner console) {
        listSelectionMenu(arr, page, cart, console, false);
    }

    /**
     * prints out the receipt and asks if the user want's to complete transaction.
     * @param arr - the list array
     * @param page - the page of the list
     * @param cart - the shopping cart object
     * @param console - the input Scanner
     */
    public static void goToCart(String[] arr, int page, ShoppingCart cart, Scanner console) {
    	//completeTransaction compTrans = new completeTransaction();
    	//Deal deal = new Deal();
    	
    	double tax, price, total, deals;
    	int itemQty;
    	String itemName, itemToRemove;
    	//to print items name , quantities and price
        starline(COL_WIDTH);
        System.out.println("  Receipt");
        starline(COL_WIDTH);
    	List<ItemOrder> itemInCart = cart.getItemOrders();
    	for (ItemOrder itemOrder : itemInCart) {
	    System.out.printf("%d x \t%s \t%.2f\n",itemOrder.getQuantity(),itemOrder.getName(),itemOrder.getCost());
		//to remove item inCart
	   
	    //System.out.printf("%s \t%d \t-%.2f",itemOrder.getName(),itemOrder.getQuantity(),itemOrder.getRemoveItem());
        }
        // tax
        CartDeals findDeals = cart.findDeals();
        for (DealObject deal : findDeals.getDealObjects()) {
            if (deal.isReduction()) {
                System.out.printf("\t\t-%.2f\n",deal.getReceivable());
            }
        }
    	
        System.out.printf("Tax: %.2f \tTotal (Tax not included): %.2f\n" , cart.calculateTax(), cart.getTotalCost()); 
    	//deal
	   // System.out.printf("%s \t%.2f\n", itemOrder.getName(), itemOrder.getCost() /*- itemOrder.getDeal()*/);
	    //final cost
        System.out.printf("Final Total %.2f\n", cart.calculateFinalTotal());	
    	starline(COL_WIDTH);
        
        System.out.println("Are you ready to check out? Yes or No");
        YesNoCheck c;
            do { 
                String input = console.nextLine();
                c = new YesNoCheck(input);
                if(c.isYes()) {
                    cart.completeTransaction();
                    System.out.println("Transaction Complete!");
                    return;
                }
                else if(c.isNo()) {
                    listSelectionMenu(arr, page, cart, console); 
                    return;
                }
                else System.out.println("not valid input");
                
            }while(!c.answered());
    }

}
