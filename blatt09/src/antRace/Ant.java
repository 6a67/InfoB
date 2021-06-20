package antRace;

import antRace.AntField.Field;
import threads.MakeRandomSleeps;

import java.util.ArrayList;

/**
 * An {@code Ant} is created at a specific position of an {@link AntField} with
 * an initial {@code stepCount}. When running an Ant, it will lookup the values
 * on the current and all surrounding {@link Field}
 * (Moore-neighborhood) instances and test if the position is free, i.e. has a
 * value of {@code 0}, or if the value is greater than the {@code stepCount} of
 * this Ant. For both cases, the Ant will set the value of the {@code Field} at
 * the visited position to its own {@code stepCount+1}. After an {@code Ant} has
 * successfully visited one field, it will create new {@code Ant} instances with
 * an incremented {@code stepCount} to visit the other available {@code Field}
 * elements. The Ant will run until it finds no more {@code Field} elements in
 * its neighborhood to be altered.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class Ant implements Runnable {

   private ArrayList<Field> neighbours = new ArrayList<Field>();
   private int stepCount;
   private int x;
   private int y;
   private AntField fields;

   /**
    * 
    * @param fields
    *           the {@code AntField} on which this {@code Ant} operates
    * @param x
    *           x-axis value of the starting position
    * @param y
    *           y-axis value of the starting position
    * @param stepCount
    *           initial stepCount of this {@code Ant}.
    * 
    * @throws IllegalArgumentException
    *            If the {@code Field} at position {@code x,y} does not exist, or
    *            if its value is < 0
    */
   public Ant(AntField fields, int x, int y, int stepCount) {
      this.stepCount = stepCount;
      this.x = x;
      this.y = y;
      this.fields = fields;
      // Set the ants current field to the stepCount
      fields.getField(x, y).setValue(stepCount);
      // As the be counted up in the constructor, otherwise the workingAnts variable in fields might be 0 at some point
      // before the program is finished
      fields.addAnt();
   }

   public void run() {
      boolean traveled;

      do {

         // Temp variables used to still look from the original center, even if the ant already changed its
         // position to for example to top left square
         int tmpX = x;
         int tmpY = y;
         int tmpStepCount = stepCount;

         // If declared outside of the do-while-loop, the ant would end in an endless loop if it only has smaller
         // or null elements around it. Hence it has to be set to false at the beginning of the loop and if the
         // ant isn't allowed to travel the loop will stop
         traveled = false;

         // Iterate through every neighbour
         for(int i = tmpX-1; i <= tmpX+1; i++) {
            for(int j = tmpY-1; j <= tmpY+1; j++) {
               // All the calls on the field are synced
               synchronized (fields) {
                  Field n = fields.getField(i, j);

                  // Null would be an valid field and it should also not overwrite itself
                  if(n != null && (i != tmpX || j != tmpY)) {
                     int value = n.getValue();

                     // Only change the field if one of these conditions is met
                     if (value == 0 || value > tmpStepCount + 1) {
                        // If the ant hasn't traveled yet, it will travel and change the value,
                        // otherwise it will spawn a new ant
                        if(!traveled) {
                           stepCount++;
                           n.setValue(stepCount);
                           traveled = true;
                           x = i;
                           y = j;
                        } else {
                           new Thread(new Ant(fields, i, j, tmpStepCount + 1)).start();
                        }
                     }
                  }
               }

            }
         }
      } while(traveled);
      fields.removeAnt();
   }

}
