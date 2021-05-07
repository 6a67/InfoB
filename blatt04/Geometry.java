package blatt04;
/**
 * Every Geometry represents a body in a data-space with {@link #dimensions()}.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public abstract class Geometry implements Comparable<Geometry> {

   /**
    * Holds the number of dimensions for this Geometry.
    */
   private int dimension;

   /**
    * Create a new Geometry. Every Geometry must have a <code>dimension</code>
    * of at least 2.
    *
    * @param dimension number of dimensions of the data space of this Geometry
    * @throws RuntimeException if the number of dimensions <code>dimension</code>
    *                          is lesser than 2.
    */
   public Geometry(int dimension) {
      if (dimension < 2) {
         throw new RuntimeException("dimension is < 2");
      }
      this.dimension = dimension;
   }

   /**
    * Returns the number of dimensions of the data space of this Geometry.
    *
    * @return number of dimensions of this Geometry
    */
   public int dimensions() {
      return this.dimension;
   }

   /**
    * Returns the volume of this Geometry. If {@link #dimensions()} is
    * <code>2</code>, the volume is the area.
    *
    * @return volume of this Geometry
    */
   public abstract double volume();

   /**
    * Encapsulates this Geometry and the given Geometry by a new Geometry and
    * returns the new Geometry. Thus the new Geometry then contains at least
    * this and the given Geometry. If other and this have a different number
    * of dimensions <code>null</code> is returned.
    *
    * @param other the Geometry to be encapsulated together with this Geometry
    * @return a new Geometry containing this and other or <code>null</code>
    * @throws RuntimeException if the type of <code>other</code> is unknown
    */
   public abstract Geometry encapsulate(Geometry other);

   @Override
   public int compareTo(Geometry o) {
      int returnVolume = (int) (this.volume() - o.volume());

      if(this.volume() > o.volume() && returnVolume == 0) {
         return 1;
      }

      if(this.volume() < o.volume() && returnVolume == 0) {
         return -1;
      }

      return returnVolume;
   }
}
