package urna;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {

  public static void main(String[] args)
    throws FileNotFoundException, IOException {
    Scanner scanner = new Scanner(System.in);
    Map<String, Integer> map = new LinkedHashMap<>();

    System.out.println("Enter file full path: ");
    String path = scanner.nextLine();

    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String line = reader.readLine();
      while (line != null) {
        String[] fields = line.split(",");

        String candidate = fields[0];
        Integer votes = Integer.parseInt(fields[1]);

        if (map.get(candidate) == null) {
          map.put(candidate, votes);
        } else {
          map.put(candidate, votes + map.get(candidate));
        }
        line = reader.readLine();
      }
      for (String key : map.keySet()) {
        System.out.println(key + ": " + map.get(key));
      }
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
    scanner.close();
  }
}
