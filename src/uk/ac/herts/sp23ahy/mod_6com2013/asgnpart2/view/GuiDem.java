package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.view;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GuiDem {
    private QueOfCustomers queOfCustomers;
    private Parcelmap parcelMap;
    private Worker worker;

    public GuiDem() {
        // Initialize the list of customers and parcels
        queOfCustomers = new QueOfCustomers();
        parcelMap = new Parcelmap();
        worker = new Worker("W001", "John Doe", parcelMap);

        // Load parcels and customers from CSV files
        loadParcels("/Users/jordananyanwu/Downloads/DepotManagementSystem 2/src/Parcels.csv");
        loadCustomers("/Users/jordananyanwu/Downloads/DepotManagementSystem 2/src/Custs.csv");

        // Show the GUI
        showGUI();
    }

    private void loadParcels(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length >= 6) {
                    String parcelID = data[0];
                    int daysInDepot = Integer.parseInt(data[1]);
                    double weight = Double.parseDouble(data[2]);
                    double length = Double.parseDouble(data[3]);
                    double width = Double.parseDouble(data[4]);
                    double height = Double.parseDouble(data[5]);

                    Parcel parcel = new Parcel();
                    parcel.setParcelID(parcelID);
                    parcel.setDaysInDepot(daysInDepot);
                    parcel.setWeight(weight);
                    parcel.setLength(length);
                    parcel.setWidth(width);
                    parcel.setHeight(height);

                    parcelMap.addParcel(parcel);
                }
            }
            System.out.println("Parcels loaded successfully.");
        } catch (IOException ioException) {
            System.out.println("Error reading parcels file: " + filename + " - " + ioException.getMessage());
        }
    }

    private void loadCustomers(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String fullName = data[0];
                    String parcelID = data[1];

                    Parcel parcel = parcelMap.getParcel(parcelID);
                    if (parcel != null) {
                        Customer customer = new Customer(fullName, parcel);
                        queOfCustomers.addCustomer(customer);
                    } else {
                        System.out.println("Parcel with ID " + parcelID + " not found for customer " + fullName);
                    }
                }
            }
            System.out.println("Customers loaded successfully.");
        } catch (IOException ioException) {
            System.out.println("Error reading customers file: " + filename + " - " + ioException.getMessage());
        }
    }

    private void showGUI() {
        // Create the main GUI with the customer queue, parcel map, and worker
        ManagerGui gui = new ManagerGui(queOfCustomers, parcelMap, worker);
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        // Create the GUIDemo object which sets up the interface
        // Then just waits for user interaction
        GUIDemo demo = new GUIDemo();
    }
}
