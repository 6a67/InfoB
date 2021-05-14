/**
 * Class representing a student.
 *
 * @author Mathias Menninghaus
 */
public class Student extends Person {

   /**
    * Registration number of this Student.
    */
   private int matNr;

   /**
    * Custom constructor setting the name and the registration number of this
    * Student.
    *
    * @param name  the name of this Student
    * @param matNr the registration number of this Student
    */
   public Student(String name, int matNr) {
      super(name);
      this.matNr = matNr;
   }

   /**
    * Returns the registration number of this Student.
    *
    * @return the registration number of this Student
    */
   public int getMatNr() {
      return this.matNr;
   }

   /**
    * A Student can only be equal to another Student with exactly the same name
    * and matNr.
    *
    * @param o Object to be compared with
    * @return if this Student is equal to o
    */
   public boolean equals(Object o) {

      if (o == null) {
         return false;
      }

      if (o == this) {
         return true;
      }

      if (o instanceof Student) {
         return this.matNr == ((Student) o).getMatNr()
               && this.getName().equals(((Student) o).getName());
      }

      return false;
   }

   /**
    * Produces a hash-value for this Student.
    *
    * @return the sum of matNr an the hashCode of name
    */
   public int hashCode() {
      return this.matNr + this.getName().hashCode();
   }

}
