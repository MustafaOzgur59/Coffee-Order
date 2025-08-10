package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum CoffeeType {

    ESPRESSO(1, "Espresso", 20,
             Recipe.of(Map.of(
                     Ingredient.ESPRESSO, 1
    ))),

    DOUBLE_ESPRESSO(2, "Double Espresso", 27,
                    Recipe.of(Map.of(
                            Ingredient.ESPRESSO, 2
    ))),

    CAPPUCCINO(3, "Cappuccino", 28,
               Recipe.of(Map.of(
                       Ingredient.ESPRESSO, 1,
               Ingredient.STEAMED_MILK, 2,
               Ingredient.MILK_FOAM, 2
    ))),

    CAFFE_LATTE(4, "Caffe Latte", 28,
                Recipe.of(Map.of(
                        Ingredient.ESPRESSO, 1,
                Ingredient.STEAMED_MILK, 3,
                Ingredient.MILK_FOAM, 1
    ))),

    MOCHA(5, "Mocha", 32,
          Recipe.of(Map.of(
                  Ingredient.ESPRESSO, 1,
          Ingredient.STEAMED_MILK, 1,
          Ingredient.MILK_FOAM, 1,
          Ingredient.HOT_CHOCOLATE, 2
    ))),

    AMERICANO(6, "Americano", 26,
              Recipe.of(Map.of(
                      Ingredient.ESPRESSO, 1,
              Ingredient.HOT_WATER, 4
    ))),

    HOT_WATER(7, "Hot Water", 5,
              Recipe.of(Map.of(
                      Ingredient.HOT_WATER, 5
    )));

    private final int number;
    private final String displayName;
    private final int priceTl;
    private final Recipe recipe;

    CoffeeType(int number, String displayName, int priceTl, Recipe recipe) {
        this.number = number;
        this.displayName = displayName;
        this.priceTl = priceTl;
        this.recipe = recipe;
    }

    public int getNumber() {
        return number;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getPriceTl() {
        return priceTl;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    private static final Map<Integer, CoffeeType> BY_NUMBER = new HashMap<>();
    static {
        for (CoffeeType c : values()) {
            BY_NUMBER.put(c.number, c);
        }
    }

    /** Lookup by menu number. */
    public static Optional<CoffeeType> fromNumber(int n) {
        return Optional.ofNullable(BY_NUMBER.get(n));
    }
}
