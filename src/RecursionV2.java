import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Demonstrates recursion by calculating the product of multiple integers.
 * Uses BigInteger internally so the result can be arbitrarily large
 */
public class RecursionV2 {

    private static final int NUMBER_COUNT = 5;

    /**
     * Reads a valid long from the user.
     */
    public static long readValidNumber(Scanner sc, int position) {
        long num = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter number " + position + ": ");

            try {
                num = sc.nextLong();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a whole number (integer).");
                sc.nextLine();
            }
        }
        return num;
    }

    /**
     * Recursively calculates the product as a BigInteger.
     */
    public static BigInteger calculateProduct(Scanner sc, int remaining, int currentPosition) {
        long num = readValidNumber(sc, currentPosition);
        BigInteger current = BigInteger.valueOf(num);

        if (remaining == 1) {
            return current;
        }

        // Recurse and multiply
        BigInteger rest = calculateProduct(sc, remaining - 1, currentPosition + 1);
        return current.multiply(rest);
    }

    /**
     * Formats a BigInteger with commas.
     */
    public static String formatWithCommas(BigInteger number) {
        return String.format("%,d", number);
    }
    
    /**
     * Prints the result in both comma-delimited and scientific notation.
     */
    public static void printResult(BigInteger number) {
        // Comma-delimited format
        String commaFormatted = String.format("%,d", number);

        // Scientific notation (using BigDecimal for full precision)
        BigDecimal bd = new BigDecimal(number);
        String scientific = String.format("%.6E", bd);

        System.out.println("\nThe product of all " + NUMBER_COUNT + " numbers is:");
        System.out.println("Standard notation:   " + commaFormatted);
        System.out.println("Scientific notation: " + scientific);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Recursive Calculator =====");
        System.out.println("Please enter " + NUMBER_COUNT + " integers:\n");

        BigInteger product = calculateProduct(scanner, NUMBER_COUNT, 1);

        printResult(product);

        scanner.close();
    }
}