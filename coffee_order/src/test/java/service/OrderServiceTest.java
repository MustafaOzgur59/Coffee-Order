package service;

import domain.CoffeeType;
import dto.OrderSummaryDTO;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void summarize_concreteType() {
        OrderService os = new OrderService();
        OrderSummaryDTO s = os.summarize(CoffeeType.CAPPUCCINO);
        assertEquals("Cappuccino", s.drinkName());
        assertEquals(28, s.priceTl());
        assertEquals(3, s.consumed().size());
    }

    @Test
    void summarizeByNumber_validAndInvalid() {
        OrderService os = new OrderService();
        Optional<OrderSummaryDTO> ok = os.summarizeByNumber(7);
        assertTrue(ok.isPresent());
        assertEquals("Hot Water", ok.get().drinkName());

        assertTrue(os.summarizeByNumber(0).isEmpty());
        assertTrue(os.summarizeByNumber(42).isEmpty());
    }
}