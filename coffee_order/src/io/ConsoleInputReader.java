package io;

import java.util.Scanner;

public final class ConsoleInputReader implements InputReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readLine() {
        return  scanner.nextLine();
    }
}
