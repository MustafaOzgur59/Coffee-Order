package service;

import domain.CoffeeType;
import dto.MenuItemDTO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class MenuService {

    public List<MenuItemDTO> getMenu() {
        return Arrays.stream(CoffeeType.values())
                .sorted(Comparator.comparing(CoffeeType::getNumber))
                .map(coffeeType -> new MenuItemDTO(coffeeType.getNumber(), coffeeType.getDisplayName(), coffeeType.getPriceTl()))
                .toList();
    }
}
