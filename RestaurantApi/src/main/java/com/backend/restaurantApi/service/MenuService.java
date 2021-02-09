package com.backend.restaurantApi.service;

import com.backend.restaurantApi.exception.MenuNotFoundException;
import com.backend.restaurantApi.model.Meal;
import com.backend.restaurantApi.model.Menu;
import com.backend.restaurantApi.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MealService mealService;

    public Menu createNewMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu addMenuMeal(Meal meal, Long id) {
        // Check if menu with 'id' exists
        Optional<Menu> menu = menuRepository.findById(id);
        if (!menu.isPresent()) {
            throw new MenuNotFoundException("Menu record is not available...");
        } else {
            // create the meal and add it to menu
            mealService.createNewMeal(meal);
            menu.get().getMeal().add(meal);
        }
        return menuRepository.save(menu.get());
    }

    public List<Menu> filterByPeanuts() {
        return menuRepository.filterByPeanuts();
    }
}
