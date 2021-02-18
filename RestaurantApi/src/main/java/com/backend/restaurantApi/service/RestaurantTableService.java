package com.backend.restaurantApi.service;

import com.backend.restaurantApi.exception.RestaurantTableNotFoundException;
import com.backend.restaurantApi.model.RestaurantTable;
import com.backend.restaurantApi.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The Service clas of the ResturantTable model  that handles
 * creating the the table, updating it and getting information from it.
 */
@Service
public class RestaurantTableService {
    
    /**
     * Autowires to the the custom repository.
     */
    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    /**
     * Creates a new Restaurant Table and upates the repository.
     * 
     * @param restaurantTable the new restaurant table
     * @return the updated repository
     */
    public RestaurantTable createNewRestaurantTable(RestaurantTable restaurantTable) {
        return restaurantTableRepository.save(restaurantTable);
    }
}
