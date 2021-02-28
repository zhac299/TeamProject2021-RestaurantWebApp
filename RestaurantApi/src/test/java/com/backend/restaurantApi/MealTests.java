package com.backend.restaurantApi;

import com.backend.restaurantApi.exception.MealNotFoundException;
import com.backend.restaurantApi.exception.MenuNotFoundException;
import com.backend.restaurantApi.model.*;
import com.backend.restaurantApi.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MealTests {

    @Autowired
    MealService mealService;

    @Autowired
    OrderService orderService;

    @Autowired
    RestaurantTableService tableService;

    @Autowired
    CustomerService customerService;

    @Autowired
    MenuService menuService;

    static Meal meal;
    static Order order;
    static Menu menu;
    static RestaurantTable table;
    static Customer customer;

    @BeforeEach
    void setUp(){
        meal = new Meal();
        menu = new Menu();
        order = new Order();
        customer = new Customer();
        table = tableService.createNewRestaurantTable(new RestaurantTable());
        // Check if table is created
        Assertions.assertNotNull(tableService.getTableByNumber(table.getTableNumber()));

        customer.setTable(table);
        customer = customerService.createNewCustomer(customer);
        // Check if customer is created
        Assertions.assertNotNull(customerService.getCustomerById(customer.getId()));
        order.setCustomer(customer);
        order = orderService.createNewOrder(order);
        // Check if order has been created
        Assertions.assertNotNull(orderService.getOrderById(order.getId()));

        menu.setName("Prawn Cocktail");
        menu = menuService.createNewMenu(menu);
        // Meal needs order to be created
        meal.setOrder(this.order);
        meal.setMenu(menu);
        meal = mealService.createNewMeal(meal);
        // Check if meal is created
        Assertions.assertNotNull(mealService.getMealById(meal.getId()));
    }

    @Test
    void testCreateMeal(){
        Assertions.assertNotNull(mealService.getMealById(meal.getId()));
        Assertions.assertEquals(mealService.getMealById(meal.getId()).getMenu().getName()
                ,"Prawn Cocktail");
    }

    @Test
    void testDelete(){
        mealService.deleteMeal(meal.getId());
        Assertions.assertThrows(MenuNotFoundException.class,() -> mealService.getMealById(meal.getId()));
        Assertions.assertNotNull(orderService.getOrderById(this.order.getId()));
        Assertions.assertNotNull(menuService.getMenuById(menu.getId()));
    }
}