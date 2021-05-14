/**
 * Class representing a Person.
 *
 * @author Mathias Menninghaus
 */
public class Person {

   /**
    * The name of this Person.
    */
   private String name;

   /**
    * Constructor setting the name of this Person.
    *
    * @param name the name of this Person
    */
   public Person(String name) {
      this.name = name;
   }

   /**
    * Returns the name of this Person.
    *
    * @return the name of this Person
    */
   public String getName() {
      return this.name;
   }

   /**
    * A Person can only be equal to another Person with exactly the same name.
    *
    * @param o Object to be compared with
    * @return if this Person is equal to o
    */
   public boolean equals(Object o) {

      if (o == null) {
         return false;
      }

      if (o == this) {
         return true;
      }

      if (o instanceof Person) {
         return this.name.equals(((Person) o).getName());
      }

      return false;
   }

   /**
    * Produces the hash Code for this Person. Which simply is the hashCode of
    * its name.
    *
    * @return hashCode of name
    */
   public int hashCode() {
      return this.getName().hashCode();
   }
}
