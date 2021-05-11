import java.io.IOException;

public class UncertainException {

   private int i = 0;

   public static void uncertain(int number) {
      UncertainException uncertain = new UncertainException();
      int result = 0;

      try {
         System.out.println("uncertain" + number + "()");
         switch (number) {
            case 1:
               result = uncertain.uncertain1();
               break;
            case 2:
               result = uncertain.uncertain2();
               break;
            case 3:
               result = uncertain.uncertain3();
               break;
            case 4:
               result = uncertain.uncertain4();
               break;
            case 5:
               result = uncertain.uncertain5();
               break;
            case 6:
               result = uncertain.uncertain6();
               break;
            case 7:
               result = uncertain.uncertain7();
               break;
            case 8:
               result = uncertain.uncertain8();
               break;
            case 9:
               result = uncertain.uncertain9();
               break;
            case 10:
               result = uncertain.uncertain10();
               break;
         }
         System.out.println("result = " + result + ", i = " + uncertain.i);
      } catch (Exception e) {
         System.out.println("i = " + uncertain.i + " Exception (" + e.getClass()
               .getName() + ")");
      } finally {
      }
   }

   public static void main(String args[]) {
      for (int i = 1; i <= 10; ++i) {
         uncertain(i);
      }
   }

   public int uncertain1() {
      try {
         throw new RuntimeException();
      } catch (RuntimeException e) {
         i++;
         throw new ClassCastException();
      } finally {
         i++;
         throw new NumberFormatException();
      }
   }

   public int uncertain2() {
      // Same as while(true)
      for (; ; ) {
         try {
            break;
         } catch (RuntimeException e) {
            i++;
         } finally {
            i++;
            throw new RuntimeException();
         }
      }
      // Unreachable because of the for(;;)
      // return i++;
   }

   public int uncertain3() {
      do {
         try {
            throw new RuntimeException();
         } catch (RuntimeException e) {
            i++;
            continue;
         } finally {
            i++;
         }
      } while (false);
      return i++;
   }

   public int uncertain4() {
      try {
         return i++;
      } catch (RuntimeException e) {
         i++;
      } finally {
         i++;
      }
      return i++;
   }

   public int uncertain5() {
      try {
         return i;
      } finally {
         throw new RuntimeException();
      }
   }

   public int uncertain6() {
      try {
         throw new RuntimeException();
      } finally {
         return ++i;
      }
   }

   public int uncertain7() {
      try {
         throw new java.io.IOException();
      } catch (RuntimeException e) {
         i++;
      } finally {
         return i++;
      }
   }

   public int uncertain8() {
      try {
         throw new NumberFormatException();
      } catch (RuntimeException e) {
         i++;
         throw new RuntimeException();
      } finally {
         i++;
      }
   }

   public int uncertain9() {
      try {
         throw new ClassCastException();
      } catch (RuntimeException e) {
         return i++;
      } finally {
         return i++;
      }
   }

   public int uncertain10() throws IOException {
      try {
         // This Exception wasn't caught, but has to caught or thrown at the head of the method
         throw new java.io.IOException();
      } catch (RuntimeException e) {
      }
      return 1;

   }
}