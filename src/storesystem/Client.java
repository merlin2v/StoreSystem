/*
 * 
 */
package storesystem;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import storesystem.api.*;
import storesystem.api.deals.PriceReductionOnMutiSameItemDeal;

/**
 * The start of the program 
 * @author Nathan
 */
public class Client {

    public static Inventory StoreInventory;
    public static ItemRegistry Registry;
    
    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        initialize(); //initialize the variables 
        if (args.length!= 0 && args[0].equals("server")) {
            // run server stuff
            serverConsole();
        }
        // TODO code application logic here
        ClientInterface.main(args);
        //save();
    }
    /**
     * prints out the list
     * @param arr the array of items available
     * @param page the page number to print
     */
    public static void printList(String[] arr, int page){
        
        int itemStart = page * 9;
        if (/* code to test max pages*/page>10 ) {
            throw new IllegalArgumentException("page must be ");
        }
        for (int i = itemStart; i < 9 + itemStart; i++) {
            if (i<arr.length) {
                String name = arr[i];
                Item item = Registry.getItem(name);
                double cost = item.getPrice();
                if (item instanceof ItemPack) {
                    int cnt = ((ItemPack) item).PackCount;
                    System.out.printf("%1$s.  %2$s \t %4$spk $%3$.2f\n",(i+1),name,cost,cnt);
                    break;
                }
                System.out.printf("%1$s.  %2$s \t $%3$.2f\n",(i+1),name,cost);
            }else{
                System.out.println("");
            }
            
        }
    }
    
    /**
     * Saves the objects
     */
    public static void save(){
        File dir = new File("data/");
        dir.mkdir();
        File reg = new File("data/registry.obj");
        try {
            Registry.save(reg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        File dir2 = new File("data/inventory.obj");
        try {
            StoreInventory.save(dir2);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**initializes the 
     * help used from:
     * https://www.tutorialspoint.com/java/java_serialization.htm
     * 
     */
    public static void initialize(){
        File regf = new File("data/registry.obj");
        if (regf.exists()) {
            try {
                Registry=ItemRegistry.loadRegistry(regf);
            } catch (IOException ex) {
                System.out.println("attempting to get default reg path");
                Registry=ItemRegistry.getDefaultRegistry();
            }
        }else{
            Registry=ItemRegistry.getDefaultRegistry();
        }
        
        File invf = new File("data/inventory.obj");
        
        if (invf.exists()) {
            try {
                StoreInventory = Inventory.loadInventory(invf, Registry);
            } catch (IOException ex) {
                System.out.println("malformatted Inventory object");
                System.out.println("creating new object");
                StoreInventory = new Inventory(Registry);
            }
        }else{
            System.out.println("Inventory not found ");
            StoreInventory = new Inventory(Registry);
        }
        
    }
    /**
     * This is a barebones method to allow basic additions and removals to be made 
     * to the {@link ItemRegistry} and the {@link Inventory} objects. <br>
     * It includes some console line commands such as:
     * <ul>
     *      <li> help - a list of all available commands and how to use them. </li>
     *      <li> stop - exits the console</li>
     *      <li> register - registers item</li>
     *          <ul>
     *              <li> {@code register tissue 5.0}</li>
     *              <li> {@code register pancake}</li>
     *          </ul>
     *      <li>inv - adds or removes from inventory
     *          <ul>
     *          <li> Ex: {@code inv add tissue}<br>
     *               will add 1 tissue to inventory</li>
     *          <li> Ex: {@code inv sub tissue 15} <br>
     *               will remove 15 tissues from inventory</li>
     *          </ul>
     *      <li> save - saves items</li>
     *      <li> init - reinitializes variables</li>
     *      <li> item - test for an item</li>
     *      <li> wrap - registers an item with extra data<br>
     *           Ex: {@code wrap cola pack 4}</li>
     * </ul>
     * 
     */
    public static void serverConsole(){
        Scanner scn = new Scanner(System.in);
        boolean run = true;
        System.out.println("type 'help' for list of commands");
        while(run){
            String line = scn.nextLine();
            Scanner lnscn = new Scanner(line);
            String command = lnscn.next();
            command = command.toLowerCase();
            switch(command){
                case "stop" ->  {
                    run = false;
                }
                case "help" ->  {
                    System.out.println(
                            "help menu\n"
                                    + "stop - exits the console\n"
                                    + "register - registers item\n"
                                    + "\tEX: register tissue 5.0\n"
                                    + "\t    register pancake\n"
                                    + "inv - adds or removes from inventory\n"
                                    + "\t Ex: inv add tissue\n"
                                    + "\t\t will add 1 tissue to inventory\n"
                                    + "\t Ex: inv sub tissue 15\n"
                                    + "\t\t will remove 15 tissues from inventory\n"
                                    + "save - saves items\n"
                                    + "init - reinitializes variables\n"
                                    + "item - test for an item\n"
                                    + "wrap - registers an item with extra data\n" 
                                    + "\t Ex: wrap cola pack 4\n"
                    );
                }
                case "register" -> {//register tissue 5.0
                    String name = lnscn.next();
                    if(lnscn.hasNextDouble()){
                        double price = lnscn.nextDouble();
                        Registry.registerItem(name, price);
                    }else{
                        Registry.registerItem(name);
                    }
                    System.out.println("item '"+name+"' has been registered");
                }
                case "inv" -> {//inv add tissue 2
                    String subcmd = lnscn.next();
                    String name = lnscn.next();
                    if (!Registry.isItemRegistered(name)) {
                        System.err.println("Could not find item '"+name+"'");
                        break;
                    }
                    Item i = Registry.getItem(name);
                    if (subcmd.equals("add")) {
                        if (lnscn.hasNextInt()) {
                            int q = lnscn.nextInt();
                            StoreInventory.addItems(i, q);
                            System.out.println("item '"+name+"' added '"+q+"' to Inventory");
                        }else{
                            StoreInventory.addItem(i);
                            System.out.println("item '"+name+"' added '1' to Inventory");
                        }
                    }else if(subcmd.equals("sub")){
                        if (lnscn.hasNextInt()) {
                            int q = lnscn.nextInt();
                            StoreInventory.removeItems(i, q);
                            System.out.println("item '"+name+"' removed '"+q+"' from Inventory");
                        }else{
                            StoreInventory.removeItem(i);
                            System.out.println("item '"+name+"' removed '1' from Inventory");
                        }
                    }else if(subcmd.equals("get")){
                        ItemOrder io = StoreInventory.getItemOrder(i);
                        System.out.println("Current inventory: \n\t'"+name+"' quantity:"+ io.getQuantity() );
                        break;
                    }else{
                        System.err.println("Invalid sub-command. Try 'add', 'sub' or 'get'");
                        break;
                    }
                    ItemOrder io = StoreInventory.getItemOrder(i);
                    System.out.println("Current inventory updated: \n\t'"+name+"' quantity:"+ io.getQuantity() );
                }
                case "item" -> {
                    String name = lnscn.next();
                    System.out.println("Item: "+Registry.isItemRegistered(name));
                }
                case "save" -> {
                    save();
                    System.out.println("saved");
                }
                case "load" -> {
                    initialize();
                    System.out.println("re-loaded");
                }
                case "createdefault" -> {
                    assignDefaultRegInv();
                }
                case "list" -> {
                    String subcmd = lnscn.next();
                    if (subcmd.equals("inv")) {
                        Set<String> listOfItems = Registry.getListOfItems();
                        for (String item : listOfItems) {
                            Item i = Registry.getItem(item);
                            if (i!=null) {
                                ItemOrder io = StoreInventory.getItemOrder(i);
                                if (io!=null) {
                                    System.out.println(item +" Q: "+io.getQuantity());
                                }
                            }
                        }
                    }else if (subcmd.equals("reg")) {
                        Set<String> listOfItems = Registry.getListOfItems();
                        for (String item : listOfItems) {
                            Item i = Registry.getItem(item);
                            System.out.println(item +" $"+i.getPrice());
                        }
                    }else{
                        System.err.println("Please use `inv` or `reg`");
                    }
                }
                case "wrap" -> {
                    String name = lnscn.next();
                    String type = lnscn.next();
                    if (Registry.isItemRegistered(name)) {
                        System.err.println("item '"+name+"' is already registered");
                        break;
                    }
                    WrappedItem witem = null;
                    switch(type){
                        case "wrap" -> {
                            ItemPack p = new ItemPack();//
                            p.PackCount = lnscn.nextInt();
                            witem = p;
                        }
                        default-> {
                            System.err.println("type '"+type+"' is not a valid type");
                            break;
                        }
                    }
                    if (witem==null) {
                        break;
                    }
                    Registry.registerWrappedItem(name,witem);
                    System.out.println("registered '"+name+"' of type '"+type+"'");
                    
                }
                default -> {
                    System.err.println("Sorry, command '"+command+"' is not a valid command\n"
                    + "\t type 'help' for a list of commands");
                }
            }
        }
    }
    
    /**
     * run this in the server console using {@code createdefault}
     */
    public static void assignDefaultRegInv(){
        tryRegisterItem("hat",12);
        tryRegisterItem("table",52.89);
        tryRegisterItem("chair",32.54);
        tryRegisterItem("chocolate", 2.99);
        tryRegisterItem("candle",7.59);
        tryRegisterItem("hourglass",8.45);
        tryRegisterItem("lamp",17.89);
        tryRegisterItem("cookie",5.99);
        tryRegisterItem("towel",16.77);
        tryRegisterItem("booklet",7);
        tryRegisterItem("bag",0.05);
        tryRegisterItem("mat",14.95);
        tryRegisterItemPack("cola",12.99,12);
        tryRegisterItemPack("pencils",4.99,20);
        tryRegisterItem("fan",27.99);
        tryRegisterItem("cap",4.59);
        tryRegisterItem("vase",32.95);
        tryRegisterItem("bowl",12.54);
        
        tryAddInv("hat",12);
        tryAddInv("table",2);
        tryAddInv("chair",7);
        tryAddInv("chocolate", 20);
        tryAddInv("candle",7);
        tryAddInv("hourglass",4);
        tryAddInv("lamp",4);
        tryAddInv("cookie",10);
        tryAddInv("towel",8);
        tryAddInv("booklet",10);
        tryAddInv("bag",60);
        tryAddInv("mat",6);
        tryAddInv("cola",3);
        tryAddInv("pencils",20);
        tryAddInv("fan",5);
        tryAddInv("cap",12);
        tryAddInv("vase",5);
        tryAddInv("bowl",7);
        
        tryAddDeal("bag",10,0.4);
    }
    private static void tryAddDeal(String name, int amount, double discount){
        if (Registry.hasItem(name)){
            Item i = Registry.getItem(name);
            Registry.addDeal(new PriceReductionOnMutiSameItemDeal(i, amount, discount));
        }
    }
    
    private static void tryAddInv(String name, int amount){
        if (Registry.hasItem(name)){
            Item i = Registry.getItem(name);
            StoreInventory.addItems(i,amount);
        }
    }
    private static void tryRegisterItem(String name, double cost){
        if (!Registry.hasItem(name)) Registry.registerItem(name, cost);
    }
    private static void tryRegisterItemPack(String name, double cost, int peices){
        ItemPack pk = new ItemPack();
        pk.PackCount = peices;
        pk.setPrice(cost);
        if (!Registry.hasItem(name)) Registry.registerWrappedItem(name, pk);
    }
}
