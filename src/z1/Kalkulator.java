package z1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Kalkulator {
    public void oblicz(String dataPath, String resultPath) {
        PrintWriter printWriter = null;

        try {
            File dataFile = new File(dataPath);
            Scanner scanner = new Scanner(dataFile);
            printWriter = new PrintWriter(resultPath);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(" ");

                if (elements.length != 3) {
                    throw new IllegalArgumentException("Wrong format: " + line);
                }

                int num1 = Integer.parseInt(elements[0]);
                int num2 = Integer.parseInt(elements[2]);
                String operation = elements[1];

                int result = 0;
                switch (operation) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            printWriter.println("Division by zero");
                            continue;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation: " + operation);
                }
                printWriter.println(result);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            try {
                printWriter = new PrintWriter(resultPath);
                printWriter.println("File not found: " + dataPath);
            } catch (FileNotFoundException e2) {
                System.out.println("File not found: " + resultPath + " " + e2);
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}