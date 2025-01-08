package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2;

import src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.controller.MainController;
import src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model.*;
import src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.view.MainView;

public class Main {
    public static void main(String[] args) {
        // Initialize the model components
        QueOfCustomers queOfCustomers = new QueOfCustomers();
        Parcelmap parcelMap = new Parcelmap();
        Worker worker = new Worker("W1", "John Doe", parcelMap);

        // Initialize the view
        MainView mainView = new MainView();

        // Initialize the controller
        MainController mainController = new MainController(queOfCustomers, parcelMap, worker, mainView);

        // Example data
        queOfCustomers.addCustomer("Alice");
        queOfCustomers.addCustomer("Bob");
        parcelMap.addParcel(new Parcel("P1", 10, 10, 10, 2, 5));
        parcelMap.addParcel(new Parcel("P2", 20, 20, 20, 5, 3));

        // Update the view with the initial data
        mainController.updateView();

        // Save log to file on exit
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Log.getInstance().saveLogToFile("application_log.txt")));
    }
}