package com.example.boxdelivery.service;

import com.example.boxdelivery.model.Box;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class BoxService {

    // In-memory storage for boxes
    private List<Box> boxes = new ArrayList<>();

    // Create a new box
    public Box createBox(Box box) {
        validateBox(box);  // Validate the box before adding
        boxes.add(box);
        return box;
    }

    // Get all boxes
    public List<Box> getAllBoxes() {
        return boxes;
    }

    // Get a box by ID
    public Box getBoxById(Long id) {
        Optional<Box> box = boxes.stream().filter(b -> b.getId().equals(id)).findFirst();
        return box.orElse(null); // return null if not found
    }

    // Update a box
    public Box updateBox(Long id, Box updatedBox) {
        validateBox(updatedBox);  // Validate the box before updating
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).getId().equals(id)) {
                boxes.set(i, updatedBox);
                return updatedBox;
            }
        }
        return null; // Return null if box not found
    }

    // Delete a box
    public boolean deleteBox(Long id) {
        return boxes.removeIf(box -> box.getId().equals(id));
    }

    // Validate the box before saving or updating
    private void validateBox(Box box) {
        // Validate txref (max 20 characters, only uppercase letters, numbers, and underscores)
        if (box.getTxref() == null || !Pattern.matches("^[A-Z0-9_]{1,20}$", box.getTxref())) {
            throw new IllegalArgumentException("txref must be a valid string with uppercase letters, numbers, and underscores, and a maximum length of 20.");
        }

        // Validate name (only letters, numbers, hyphen, and underscore)
        if (box.getName() == null || !Pattern.matches("^[A-Za-z0-9-_]+$", box.getName())) {
            throw new IllegalArgumentException("name must contain only letters, numbers, hyphen '-', and underscore '_'");
        }

        // Validate weight limit (must be between 0 and 500 grams)
        if (box.getWeightLimit() < 0 || box.getWeightLimit() > 500) {
            throw new IllegalArgumentException("Weight limit must be between 0 and 500 grams.");
        }

        // Validate battery capacity (must be between 0 and 100)
        if (box.getBatteryCapacity() < 0 || box.getBatteryCapacity() > 100) {
            throw new IllegalArgumentException("Battery capacity must be between 0 and 100.");
        }

        // Validate battery capacity for LOADING state (must be greater than 25%)
        if ("LOADING".equals(box.getState()) && box.getBatteryCapacity() < 25) {
            throw new IllegalArgumentException("Battery capacity must be above 25% to load the box.");
        }

        // Validate box state
        List<String> validStates = List.of("IDLE", "LOADING", "LOADED", "DELIVERING", "DELIVERED", "RETURNING");
        if (!validStates.contains(box.getState())) {
            throw new IllegalArgumentException("Invalid state. Valid states are: IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING.");
        }
    }

    // Check available boxes for loading (battery level > 25% and IDLE or LOADED state)
    public List<Box> getAvailableBoxesForLoading() {
        List<Box> availableBoxes = new ArrayList<>();
        for (Box box : boxes) {
            if (box.getBatteryCapacity() >= 25 && (box.getState().equals("IDLE") || box.getState().equals("LOADED"))) {
                availableBoxes.add(box);
            }
        }
        return availableBoxes;
    }

    // Get items loaded in a specific box (using the getItems() method)
    public List<String> getLoadedItems(Long boxId) {
        Box box = getBoxById(boxId); // Retrieve the box by ID
        if (box != null) {
            return box.getItems(); // Return the list of items loaded into the box
        }
        return new ArrayList<>(); // Return an empty list if box is not found
    }

    // Corrected method to return battery level as a double
    public double getBatteryLevel(Long boxId) {
        Box box = getBoxById(boxId); // Retrieve the box by ID
        if (box != null) {
            return box.getBatteryCapacity(); // Return the battery level as a double
        }
        throw new IllegalArgumentException("Box with ID " + boxId + " not found.");
    }
}