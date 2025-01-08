package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Parcelmap class for managing parcels.
 * 
 * @version (a version number or a date)
 */
public class Parcelmap {
    // Map to store parcels, with parcelID as the key
    public Map<String, Parcel> parcelMap;

    /**
     * Constructor for objects of class Parcelmap
     * Initializes the parcelMap.
     */
    public Parcelmap() {
        this.parcelMap = new HashMap<>();
        Log.getInstance().addEvent("Parcelmap initialized");
    }

    /**
     * Adds a parcel to the map.
     * 
     * @param parcel The Parcel object to add.
     */
    public void addParcel(Parcel parcel) {
        if (parcel != null && parcel.getParcelID() != null) {
            parcelMap.put(parcel.getParcelID(), parcel);
            Log.getInstance().addEvent("Parcel added: " + parcel.toString());
        } else {
            Log.getInstance().addEvent("Parcel data is invalid.");
            System.out.println("Parcel data is invalid.");
        }
    }

    /**
     * Retrieves a parcel by its ID.
     * 
     * @param parcelID The ID of the parcel to retrieve.
     * @return The Parcel object with the given ID, or null if not found.
     */
    public Parcel getParcel(String parcelID) {
        return parcelMap.get(parcelID);
    }

    /**
     * Removes a parcel by its ID.
     * 
     * @param parcelID The ID of the parcel to remove.
     * @return The removed Parcel object, or null if not found.
     */
    public Parcel removeParcel(String parcelID) {
        Parcel removedParcel = parcelMap.remove(parcelID);
        if (removedParcel != null) {
            Log.getInstance().addEvent("Parcel removed: " + removedParcel.toString());
        }
        return removedParcel;
    }

    /**
     * Checks if a parcel exists in the map.
     * 
     * @param parcelID The ID of the parcel to check.
     * @return true if the parcel exists, false otherwise.
     */
    public boolean containsParcel(String parcelID) {
        return parcelMap.containsKey(parcelID);
    }

    public int size() {
        return parcelMap.size();
    }


        /**
     * Displays all parcels in the map.
     */
    public String displayAllParcels() {
        StringBuilder sb = new StringBuilder();
        if (parcelMap.isEmpty()) {
            String message = "No parcels available in this Parcelmap.";
            Log.getInstance().addEvent(message);
            sb.append(message);
        } else {
            for (Parcel parcel : parcelMap.values()) {
                String parcelInfo = "Displaying parcel: " + parcel.toString();
                Log.getInstance().addEvent(parcelInfo);
                sb.append(parcelInfo).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Retrieves the first parcel in the map.
     * 
     * @return The first Parcel object in the map, or null if the map is empty.
     */
    public Parcel getFirstParcel() {
        return parcelMap.isEmpty() ? null : parcelMap.values().iterator().next();
    }
}