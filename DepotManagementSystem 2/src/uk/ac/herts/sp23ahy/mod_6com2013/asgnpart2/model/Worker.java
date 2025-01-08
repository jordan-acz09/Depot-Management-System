package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model;

import java.util.*;

/**
 * Worker class for managing workers.
 * 
 * @version (a version number or a date)
 */
public class Worker {
    private String id;
    private String name;
    private QueOfCustomers queOfCustomers;
    private Parcelmap parcelMap;
    private Parcel currentParcel; // Field to store the current parcel

    /**
     * Constructor for objects of class Worker
     */
    public Worker(String id, String name, Parcelmap parcelMap) {
        this.id = id;
        this.name = name;
        this.queOfCustomers = queOfCustomers;
        this.parcelMap = parcelMap;
        
        Log.getInstance().addEvent("Worker created: " + name + " with ID: " + id);
    }

    public String getWorkerName() {
        return name;
    }

    public String getWorkerId() {
        return id;
    }

    /**
     * Retrieves the details of the first parcel in the map.
     */
    public String getCurrentParcelDetails() {
        currentParcel = parcelMap.getFirstParcel();
        if (currentParcel != null) {
            String details = currentParcel.toString();
            Log.getInstance().addEvent("Processing parcel: " + details);
            return details;
        } else {
            Log.getInstance().addEvent("No parcels to process");
            return "No parcels to process";
        }
    }

    
    public String releaseParcel() {
        if (currentParcel != null) {
            String details = currentParcel.toString();
            parcelMap.removeParcel(currentParcel.getParcelID());
            currentParcel = null;
            Log.getInstance().addEvent("Released parcel: " + details);
            return "Parcel released: " + details;
        } else {
            Log.getInstance().addEvent("No parcels to release");
            return "No parcels to release";
        }
    }
    
    /**
     * Returns the current parcel being processed.
     */
    public Parcel getCurrentParcel() {
        return currentParcel;
    }
}