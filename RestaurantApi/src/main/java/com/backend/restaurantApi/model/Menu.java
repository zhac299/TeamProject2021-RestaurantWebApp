package com.backend.restaurantApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class for the Menu model.
 */
@Entity
@Table(name = "restaurant_menu_item")
public class Menu {

    /**
     * Defining column for Menu called id that is generated every time there is a new instance.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Column for the name of the Menu.
     */
    @Column(name = "name")
    private String name;

    /**
     * Column for the description of the menu.
     */
    @Column(name = "description")
    private String description;

    /**
     * Column for the price of the menu.
     */
    @Column(name = "price")
    private Double price = 0.0;

    @Column
    private String pictureUrl;

    @ManyToOne
    @JoinColumn(name = "category", nullable = true)
    private MenuCategory category;

    /**
     * Column for the isSuggested.
     */
    @Column(name = "isSuggested")
    private String isSuggested;

    /**
     * Column for the calories.
     */
    @Column(name = "calories")
    private Double calories;

    /**
     * Column for the peanuts.
     */
    @Column(name = "peanuts")
    private boolean peanuts = false;

    /**
     * Column for the celery.
     */
    @Column(name = "celery")
    private boolean celery = false;

    /**
     * Column for the gluten.
     */
    @Column(name = "gluten")
    private boolean gluten = false;

    /**
     * Column for the crustaceans
     */
    @Column(name = "crustaceans")
    private boolean crustaceans = false;

    /**
     * Column for eggs.
     */
    @Column(name = "eggs")
    private boolean eggs = false;

    /**
     * Column for fish.
     */
    @Column(name = "fish")
    private boolean fish = false;

    /**
     * Column for lupin.
     */
    @Column(name = "lupin")
    private boolean lupin = false;

    /**
     * Column for milk.
     */
    @Column(name = "milk")
    private boolean milk = false;

    /**
     * Column for molluscs.
     */
    @Column(name = "molluscs")
    private boolean molluscs = false;

    /**
     * Column for mustard
     */
    @Column(name = "mustard")
    private boolean mustard = false;

    /**
     * Column for nuts.
     */
    @Column(name = "nuts")
    private boolean nuts = false;

    /**
     * Column for soya.
     */
    @Column(name = "soya")
    private boolean soya = false;

    /**
     * Column for sesameSeeds
     */
    @Column(name = "sesame_seeds")
    private boolean sesameSeeds = false;

    /**
     * Column for sulphites.
     */
    @Column(name = "sulphites")
    private boolean sulphites = false;

    /**
     * Column for time to cook.
     */
    @Column(name = "time_to_cook")
    private double timeToCook = 20;
    
    /**
     * Getter for the id.
     * @return the Meal object id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter from calories in a Meal object.
     * @return Double instance representing calories.
     */
    public Double getCalories() {
        return calories;
    }

    /**
     * Setter for the calories in a Meal object.
     * @param calories to set.
     */
    public void setCalories(Double calories) {
        this.calories = calories;
    }

    /**
     * Setter for the id of Menu object.
     * @param id the id to be given.
     */
    public void setMenuId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the name.
     * @return a string to represent main.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name
     * @param name name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for price of Meal.
     * @return a double representing the price.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Setter for the price.
     * @param price the amount you want the price to be.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Checks whether there are peanuts or not.
     * @return true or false according to if there are peanuts or not.
     */
    public boolean isPeanuts() {
        return peanuts;
    }

    /**
     * Setter for isPeanuts.
     * @param peanuts boolean for if there are peanuts of not.
     */
    public void setPeanuts(boolean peanuts) {
        this.peanuts = peanuts;
    }

    /**
     * Checks for Celery.
     * @return true or false according to if there is celery in a meal or not.
     */
    public boolean isCelery() {
        return celery;
    }

    /**
     * Setter for isCelery.
     * @param celery boolean for if there is celery or not.
     */
    public void setCelery(boolean celery) {
        this.celery = celery;
    }

    /**
     * Checks for gluten.
     * @return true or false according to if there is Gluten or not.
     */
    public boolean isGluten() {
        return gluten;
    }

    /**
     * Setter for isGluten.
     * @param gluten boolean for if there is gluten or not.
     */
    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    /**
     * Checks for Crustaceans.
     * @return boolean according to if there are Crustaceans or not.
     */
    public boolean isCrustaceans() {
        return crustaceans;
    }

    /**
     * Setter for isCrustaceans.
     * @param gluten boolean for if there is Crustaceans or not.
     */
    public void setCrustaceans(boolean crustaceans) {
        this.crustaceans = crustaceans;
    }

    /**
     * Checks for eggs.
     * @return boolean according to if there are eggs or not.
     */
    public boolean isEggs() {
        return eggs;
    }

    /**
     * Setter for eggs.
     * @param gluten boolean for if there is eggs or not.
     */
    public void setEggs(boolean eggs) {
        this.eggs = eggs;
    }

    public boolean isFish() {
        return fish;
    }

    public void setFish(boolean fish) {
        this.fish = fish;
    }

    public boolean isLupin() {
        return lupin;
    }

    public void setLupin(boolean lupin) {
        this.lupin = lupin;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isMolluscs() {
        return molluscs;
    }

    public void setMolluscs(boolean molluscs) {
        this.molluscs = molluscs;
    }

    public boolean isMustard() {
        return mustard;
    }

    public void setMustard(boolean mustard) {
        this.mustard = mustard;
    }

    public boolean isNuts() {
        return nuts;
    }

    public void setNuts(boolean nuts) {
        this.nuts = nuts;
    }

    public boolean isSoya() {
        return soya;
    }

    public void setSoya(boolean soya) {
        this.soya = soya;
    }

    public boolean isSesameSeeds() {
        return sesameSeeds;
    }

    public void setSesameSeeds(boolean sesameSeeds) {
        this.sesameSeeds = sesameSeeds;
    }

    public boolean isSulphites() {
        return sulphites;
    }

    public void setSulphites(boolean sulphites) {
        this.sulphites = sulphites;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public double getTimeToTime() {
        return this.timeToCook;
    }
    public void setSuggested(String suggest) {
        this.isSuggested = suggest;
    }
    
    public String getSuggested() {
        return isSuggested;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    public double getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(double timeToCook) {
        this.timeToCook = timeToCook;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
