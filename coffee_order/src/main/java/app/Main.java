package app;


import io.ConsoleInputReader;
import io.ConsoleOutputWriter;
import io.ConsoleUI;
import service.FormattingService;
import service.MenuService;
import service.OrderService;

public class Main {
    public static void main(String[] args) {
        // Instantiate services
        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService();
        FormattingService formattingService = new FormattingService();

        // Set up I/O
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();

        // Create UI and run
        ConsoleUI ui = new ConsoleUI(menuService, orderService, formattingService, inputReader, outputWriter);
        ui.run();
    }
}