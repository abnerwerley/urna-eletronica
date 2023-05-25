package urna;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Program {

  public static void main(String[] args) {
    Map<String, Integer> map = readDataFromFile();
    printResult(map);
  }

  public static Map<String, Integer> readDataFromFile() {
    Map<String, Integer> map = new LinkedHashMap<>();

    try (
      BufferedReader reader = new BufferedReader(
        new FileReader("src/urna/Urna 1.csv")
      )
    ) {
      String line = reader.readLine();
      while (line != null) {
        String[] fields = line.split(",");

        String candidate = fields[0];
        Integer votes = Integer.parseInt(fields[1]);

        if (map.containsKey(candidate)) {
          votes += map.get(candidate);
        }
        map.put(candidate, votes);

        line = reader.readLine();
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }

    return map;
  }

  public static void printResult(Map<String, Integer> map) {
    for (String key : map.keySet()) {
      System.out.println(key + ": " + map.get(key));
    }
  }
}
