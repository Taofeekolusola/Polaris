package com.example.boxdelivery.controller;

import com.example.boxdelivery.model.Box;
import com.example.boxdelivery.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {

    private final BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    // Create a new box
    @PostMapping
    public Box createBox(@RequestBody Box box) {
        return boxService.createBox(box);
    }

    // Get all boxes
    @GetMapping
    public List<Box> getAllBoxes() {
        return boxService.getAllBoxes();
    }

    // Get box by ID
    @GetMapping("/{id}")
    public Box getBoxById(@PathVariable Long id) {
        return boxService.getBoxById(id);
    }

    // Update a box
    @PutMapping("/{id}")
    public Box updateBox(@PathVariable Long id, @RequestBody Box updatedBox) {
        return boxService.updateBox(id, updatedBox);
    }

    // Delete a box
    @DeleteMapping("/{id}")
    public void deleteBox(@PathVariable Long id) {
        boxService.deleteBox(id);
    }

    // Get available boxes for loading
    @GetMapping("/available-for-loading")
    public List<Box> getAvailableBoxesForLoading() {
        return boxService.getAvailableBoxesForLoading();
    }

    // Get loaded items for a box
    @GetMapping("/{id}/items")
    public List<String> getLoadedItems(@PathVariable Long id) {
        return boxService.getLoadedItems(id);
    }

    // Add an item to a box
    @PostMapping("/{id}/items")
    public Box addItemToBox(@PathVariable Long id, @RequestBody String item) {
        Box box = boxService.getBoxById(id);
        if (box != null) {
            box.getItems().add(item); // Assuming `items` is a List<String>
            return boxService.updateBox(id, box); // Update the box after adding the item
        }
        return null; // Return null or an error response if box is not found
    }

    // Get the battery level of a specific box
    @GetMapping("/{id}/battery-level")
    public Integer getBatteryLevel(@PathVariable Long id) {
        return boxService.getBatteryLevel(id);
    }
}