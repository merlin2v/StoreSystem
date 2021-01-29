/**
 * This package contains all the Parts to run the back-end of the Store System.
 * Custom Clients should be able to use this API to create their own unique 
 * client by using the classes found in this package.
 * The classes that should be created is the {@link ItemRegistry} object to
 * create and store a list of items as well as the {@link Inventory} to store a
 * store's current Inventory. <br>
 * Both of these are designed to be capable of creating more than one of these 
 * items if necessary as they do not rely on static variables.
 */
package storesystem.api;