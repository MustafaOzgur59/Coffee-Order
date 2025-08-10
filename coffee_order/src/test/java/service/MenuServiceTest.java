package service;

import dto.MenuItemDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest {

    @Test
    void menuHasSevenItemsInOrder() {
        MenuService ms = new MenuService();
        List<MenuItemDTO> menu = ms.getMenu();
        assertEquals(7, menu.size());
        for (int i = 0; i < menu.size(); i++) {
            assertEquals(i + 1, menu.get(i).number());
        }
        assertEquals("Americano", menu.get(5).name());
        assertEquals(26, menu.get(5).priceTl());
    }
}