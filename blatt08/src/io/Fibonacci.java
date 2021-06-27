package io;

import java.io.*;
import java.util.HashMap;

/**
 * Class to calculate the Fibonacci number. Uses a HashMap to reduce the
 * calculation cost.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Fibonacci {


   private static HashMap<Integer, Long> fibonacciHash;

   /**
    * Fill HashMap with initial key-value-pairs.
    */
   static {
      fibonacciHash = new HashMap<>();
      fibonacciHash.put(0, 0L);
      fibonacciHash.put(1, 1L);
   }

   /**
    * Calculates the fibonacci value of n.
    *
    * @param n a natural number >= 0 to calculate the fibonacci value of
    * @return fibonacci value of n
    */
   public static long fibonacci(int n) {
      if (n < 0) {
         throw new IllegalArgumentException("n = " + n);
      }
      return getFibonacci(n);
   }

   private static long getFibonacci(int n) {
      if (fibonacciHash.containsKey(n)) {
         return fibonacciHash.get(n);
      } else {
         long nMinus1 = getFibonacci(n - 1);
         long nMinus2 = getFibonacci(n - 2);
         long fibonacci = nMinus1 + nMinus2;

         fibonacciHash.put(n, fibonacci);
         return fibonacci;
      }
   }

   public static void main(String[] args) {
      String fileName = "fibo_hash_map.ser";
      File file = new File(fileName);
      if(file.isFile()) {
         try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            fibonacciHash = (HashMap<Integer, Long>) ois.readObject();
         } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
         }
      }
      Runtime.getRuntime().addShutdownHook(new Thread() {
         public void run() {
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
               oos.writeObject(fibonacciHash);
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      });


      if (args.length != 1) {
         printUsage();
      } else {
         try {
            System.out.println(fibonacci(Integer.parseInt(args[0])));

         } catch (IllegalArgumentException ex) {
            printUsage();
         }
      }


   }

   private static void printUsage() {
      System.out.println("java calc/Fiboncci n");
      System.out.println("n must be a natural number >= 0");
   }

}
