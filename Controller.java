package edu.miracostacollege.cs112.ic27_epafueleconomy.controller;


import edu.miracostacollege.cs112.ic27_epafueleconomy.model.Model;
import edu.miracostacollege.cs112.ic27_epafueleconomy.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.Collections;


/**
* The <code>Controller</code> is a Singleton object that relays all commands between the Model and View
* (and vice versa).  There is only one Controller object, accessible by a call to the static getInstance()
* method.
*
* @author Michael Paulding
* @version 1.0
*/
public class Controller {


   private static Controller theInstance;
   private ObservableList<Vehicle> allVehicles;
   private ObservableList<Vehicle> filteredVehicles;
   private ObservableList<String> distinctMakes;


   private Controller() {}
   /**
    * Gets the one instance of the Controller.
    * @return The instance
    */
   public static Controller getInstance() {
      if (theInstance == null) {
         theInstance = new Controller();


         // TODO: Note - if your capstone only uses binary file (not CSV)
         // TODO: You can remove the if statement and ONLY populate from the binary file.
         // TODO: Delete the else clause entirely.
         if (Model.binaryFileHasData())
            theInstance.allVehicles = Model.populateListFromBinaryFile();
         else
            theInstance.allVehicles = Model.populateListFromCSVFile();


         // DONE: Be sure to sort the data
         theInstance.distinctMakes = theInstance.initializeDistinctMakes();
         theInstance.filteredVehicles = FXCollections.observableArrayList();
      }
      return theInstance;
   }


   /**
    * Gets the list of all vehicles.
    * @return The list of all vehicles.
    */
   public ObservableList<Vehicle> getAllVehicles() {
      return allVehicles;
   }


   // DONE: Make a method that adds a new vehicle to the list of allVehicles
   // DONE: Be sure to add the Vehicle's make to the list of distinctMakes
   //  if it's not already there
   public void addVehicle(Vehicle v)
   {
      allVehicles.add(v);
      if (!distinctMakes.contains(v.getMake()))
         distinctMakes.add(v.getMake());


      Collections.sort(allVehicles);
      Collections.sort(distinctMakes);
   }




   // DONE: Make a method that removes a Vehicle from the list of allVehicles
   // DONE: No need to remove it from the list of distinctMakes


   public void removeVehicle(Vehicle v)
   {
      System.out.println(allVehicles.size());
      allVehicles.remove(v);
      System.out.println(allVehicles.size());
   }




   /**
    * Makes a request for the model to save all vehicle data to
    * a persistent binary file.
    */
   public void saveData() {
      Model.writeDataToBinaryFile(allVehicles);
   }


   /**
    * Gets a list of the distinct vehicle makes.
    * @return The list of the distinct vehicle makes.
    */
   public ObservableList<String> getDistinctMakes() {
      return distinctMakes;
   }


   /**
    * Initializes the list of distinct makes with empty as the first value.
    * @return The list of distinct makes with empty as the first value.
    */
   private ObservableList<String> initializeDistinctMakes()
   {
      ObservableList<String> distinctMakes = FXCollections.observableArrayList();
      // DONE: Add an empty entry to the list (blank), so user has option to see all makes
      // DONE: Loop through all the vehicles and add their make to the distinctMakes, only if
      // DONE: the make is not already there (prevent duplicates).
      distinctMakes.add("");
      for (Vehicle v : allVehicles)
         if (!distinctMakes.contains(v.getMake()))
            distinctMakes.add(v.getMake());


      Collections.sort(distinctMakes);  // Sorted order (ascending) from A - Z
      return distinctMakes;
   }


   /**
    * Filters data based on make, minimum MPG and maximum annual fuel cost.
    * @param make The make selected.
    * @param minMPG The minimum MPG
    * @param maxAnnualFuelCost The maximum annual fuel cost
    * @return The filtered list based on the three criteria.
    */
   public ObservableList<Vehicle> filter(String make, double minMPG, double maxAnnualFuelCost) {


      //DONE: Clear the filtered vehicles list
      filteredVehicles.clear();
      //DONE: Add each Vehicle that matches the criteria to the filtered vehicles list.
      for (Vehicle v : allVehicles)
         if ((make.isEmpty() || v.getMake().equalsIgnoreCase(make)) && v.getCombinedMPG() >= minMPG && v.getAnnualFuelCost() <= maxAnnualFuelCost)
            filteredVehicles.add(v);
      //DONE: Return the filtered vehicles list.


      return filteredVehicles;
   }
}
