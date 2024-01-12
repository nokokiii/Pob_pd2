package z2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Drukarka {
    public static void start(String dataPath, String resultPath) {
        File dataFile = new File(dataPath);
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        try {
            Scanner scanner = new Scanner(dataFile);
            PrintWriter writer = new PrintWriter(resultPath);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.equals("print")) {
                    if (!queue.isEmpty()) {
                        writer.println(queue.poll());
                    } else {
                        writer.println("nothing");
                    }
                } else if (line.equals("end")) {
                    while (!queue.isEmpty()) {
                        writer.println(queue.poll());
                    }
                } else {
                    int number = Integer.parseInt(line);
                    queue.offer(number);
                }
            }

            writer.close();
        } catch (FileNotFoundException e) {
            try {
                PrintWriter writer = new PrintWriter(resultPath);
                writer.println("File not found");
                writer.close();
            } catch (FileNotFoundException e2) {
                System.out.println("File not found " + e2);
            }
        }
    }
}