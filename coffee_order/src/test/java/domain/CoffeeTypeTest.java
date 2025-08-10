package domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeTypeTest {

    @Test
    void numbersAreUniqueAndInRange() {
        Set<Integer> seen = new HashSet<>();
        for (CoffeeType c : CoffeeType.values()) {
            assertTrue(c.getNumber() >= 1 && c.getNumber() <= 7, "number out of range");
            assertTrue(seen.add(c.getNumber()), "duplicate menu number: " + c.getNumber());
        }
        assertEquals(7, seen.size());
    }

    @Test
    void pricesMatchSpec() {
        assertEquals(20, CoffeeType.ESPRESSO.getPriceTl());
        assertEquals(27, CoffeeType.DOUBLE_ESPRESSO.getPriceTl());
        assertEquals(28, CoffeeType.CAPPUCCINO.getPriceTl());
        assertEquals(28, CoffeeType.CAFFE_LATTE.getPriceTl());
        assertEquals(32, CoffeeType.MOCHA.getPriceTl());
        assertEquals(26, CoffeeType.AMERICANO.getPriceTl());
        assertEquals(5, CoffeeType.HOT_WATER.getPriceTl());
    }

    @Test
    void recipesMatchSpec_subsetChecks() {
        Map<Ingredient,Integer> a = CoffeeType.AMERICANO.getRecipe().asMap();
        assertEquals(1, a.get(Ingredient.ESPRESSO));
        assertEquals(4, a.get(Ingredient.HOT_WATER));
        assertEquals(2, a.size());

        Map<Ingredient,Integer> mocha = CoffeeType.MOCHA.getRecipe().asMap();
        assertEquals(2, mocha.get(Ingredient.HOT_CHOCOLATE));
        assertEquals(4, mocha.size());
    }
}
