package johnson.michael.filearray;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Main {

  public static void main(String[] args) {
    final File workingFile = getWorkingFile();
    if (workingFile == null) {
      // An error occurred
      return;
    }

    if (!writeRandomInts(workingFile)) {
      // An error occurred
      return;
    }
    readRandomInts(workingFile);
  }

  /**
   * Prints random integers to System.out before writing them to {@code file}.
   *
   * @param file The file to which random integers should be written.
   * @return Whether or not the write was successful. True is returned on success, false otherwise.
   */
  public static boolean writeRandomInts(final File file) {
    final int[] randomInts = new int[10];

    final Random r = new Random();
    for (int i = 0; i < randomInts.length; i++) {
      randomInts[i] = r.nextInt(1000);
    }

    System.out.print("Random integer array to be written: ");
    printIntArray(randomInts);
    System.out.println();

    return writeArray(file.getPath(), randomInts);
  }

  /**
   * Reads random integers from {@code file} and prints them to System.out.
   *
   * @param file The file from which random integers should be read.
   * @return Whether or not the operation was successful. True is returned on success, false
   * otherwise.
   */
  public static boolean readRandomInts(final File file) {
    final int[] randomInts = readArray(file.getPath());
    if (randomInts == null) {
      // An error occurred in readArray
      return false;
    }

    System.out.print("Random integers read from file: ");
    printIntArray(randomInts);
    System.out.println();

    return true;
  }

  /**
   * Writes an array of integers to a file.
   *
   * @param fileName The path of the file to be written to.
   * @param intArray The array of integers to be written.
   * @return Whether or not the operation was successful. True is returned on success, false
   * otherwise.
   */
  public static boolean writeArray(final String fileName, int[] intArray) {
    try (final DataOutputStream dataOutputStream = new DataOutputStream(
        new FileOutputStream(fileName))) {
      // Write the array length so that we have it when reading back the array
      dataOutputStream.writeInt(intArray.length);

      for (int i = 0; i < intArray.length; i++) {
        dataOutputStream.writeInt(intArray[i]);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Reads an array of integers from a file.
   *
   * @param fileName The path of the file to be read from.
   * @return The array of integers that was read from the file. {@code null} is returned if the
   * operation is a failure.
   */
  public static int[] readArray(final String fileName) {
    try (final DataInputStream dataInputStream = new DataInputStream(
        new FileInputStream(fileName))) {
      final int arrayLen = dataInputStream.readInt();
      int[] intArray = new int[arrayLen];

      for (int i = 0; i < intArray.length; i++) {
        intArray[i] = dataInputStream.readInt();
      }

      return intArray;
    } catch (FileNotFoundException e) {
      System.out.println(fileName + " was not found while reading integer array.");
      return null;
    } catch (EOFException e) {
      System.out.println("Unexpected EOF while reading back array.");
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    // We should never reach here
  }

  /**
   * Creates a temporary file to be used as scratch space. Requests that the file be deleted on
   * exit.
   *
   * @return The scratch file.
   */
  private static File getWorkingFile() {
    try {
      final File tempFile = File.createTempFile("filearray", "");
      tempFile.deleteOnExit();
      return tempFile;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    // We should never reach here
  }

  /**
   * Prints an array of integers to System.out, separated by commas.
   *
   * @param ints The array of integers to be printed.
   */
  private static void printIntArray(int[] ints) {
    for (int i = 0; i < ints.length; i++) {
      if (i != 0) {
        System.out.print(", ");
      }
      System.out.print(ints[i]);
    }
  }
}
