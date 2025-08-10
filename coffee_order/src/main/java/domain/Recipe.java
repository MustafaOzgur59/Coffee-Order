package domain;

import java.util.*;

public class Recipe {

    private final EnumMap<Ingredient, Integer> doses;

    private Recipe(EnumMap<Ingredient, Integer> doses){
        this.doses = new EnumMap<>(Objects.requireNonNull(doses, "doses"));
    }

    // Factory method
    public static Recipe of(Map<Ingredient, Integer> input){
        Objects.requireNonNull(input);
        EnumMap<Ingredient, Integer> map = new EnumMap<>(Ingredient.class);
        for (Map.Entry<Ingredient, Integer> e : input.entrySet()) {
            Ingredient ing = Objects.requireNonNull(e.getKey(), "ingredient");
            Integer valObj = Objects.requireNonNull(e.getValue(), () -> "dose for " + ing);
            int v = valObj;
            if (v < 0) {
                throw new IllegalArgumentException("Dose cannot be negative for " + ing);
            }
            if (v > 0) {
                map.put(ing, v);
            }
        }
        return new Recipe(map);
    }

    /** Returns the dose for an ingredient, or 0 if absent. */
    public int doseOf(Ingredient ingredient) {
        Objects.requireNonNull(ingredient, "ingredient");
        return doses.getOrDefault(ingredient, 0);
    }

    /** Returns an unmodifiable view of ingredients present in this recipe. */
    public Set<Ingredient> ingredients() {
        return Collections.unmodifiableSet(doses.keySet());
    }

    /** Returns an unmodifiable view of the internal dose map. */
    public Map<Ingredient, Integer> asMap() {
        return Collections.unmodifiableMap(doses);
    }
}
