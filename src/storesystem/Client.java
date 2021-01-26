/*
 * 
 */
package storesystem;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import storesystem.api.*;

/**
 *
 * @author 
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


    }
    
    /**
     * Saves the objects
     */
    public static void save(){
        File dir = new File("data/registry.obj");
        dir.mkdir();
        try {
            Registry.save(dir);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        File dir2 = new File("data/inventory.obj");
        dir2.mkdir();
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
                StoreInventory = Inventory.loadInventory(invf);
            } catch (IOException ex) {
                System.out.println("malformatted Inventory object");
                System.out.println("creating new object");
                StoreInventory = new Inventory();
            }
        }else{
            System.out.println("Inventory not found ");
            StoreInventory = new Inventory();
        }
        
    }
    
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
                                    + "init - reinitializes variables"
                                    + "item - test for an item"
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
                }
                case "inv" -> {//inv add tissue 2
                    String subcmd = lnscn.next();
                    String name = lnscn.next();
                    if (!Registry.isItemRegistered(name)) {
                        System.err.println("Could not find item '"+name+"'");
                    }
                    Item i = Registry.getItem(name);
                    if (subcmd.equals("add")) {
                        if (lnscn.hasNextInt()) {
                            int q = lnscn.nextInt();
                            StoreInventory.addItem(i, q);
                        }else{
                            StoreInventory.addItem(i);
                        }
                    }else if(subcmd.equals("sub")){
                        if (lnscn.hasNextInt()) {
                            int q = lnscn.nextInt();
                            StoreInventory.removeItem(i, q);
                        }else{
                            StoreInventory.removeItem(i);
                        }
                    }
                    ItemOrder io = StoreInventory.getItemOrder(i);
                    System.out.println("Current inventory updated: \n\t'"+name+"' quantity:"+ io.Quantity );
                }
                case "item" -> {
                    String name = lnscn.next();
                    System.out.println("Item: "+Registry.isItemRegistered(name));
                }
                case "save" -> {
                    save();
                }
                case "load" -> {
                    initialize();
                }
            }
        }
    }
    
}
