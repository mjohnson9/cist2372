package exceptionexamples;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    try {
      File file = new File("Resources.txt");
      Scanner scanner = new Scanner(file);
      double a = scanner.nextDouble();
      System.out.println(a);
    } catch (FileNotFoundException e) {
      System.out.println("Caught FileNotFoundException:");
      e.printStackTrace();
    }
  }
}
