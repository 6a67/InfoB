package blatt04;

import java.util.Arrays;

public class Point extends Geometry {

    public double[] coordinates;

    public Point(double ... x) {
        super(x.length);
        coordinates = new double[x.length];
        for(int i = 0; i < x.length; i++) {
            coordinates[i] = x[i];
        }
    }

    /**
     * Returns the volume of this Geometry. If {@link #dimensions()} is
     * <code>2</code>, the volume is the area.
     *
     * @return volume of this Geometry
     */
    @Override
    public double volume() {
        return 0;
    }

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
    @Override
    public Geometry encapsulate(Geometry other) {
        if(other == null) return null;

        if(other.dimensions() != this.dimensions()) return null;

        if(other instanceof Point2D) {
            return new Rectangle((Point2D) this, (Point2D) other);
        }

        if(other instanceof Point) {
            return new Volume(this, (Point) other);
        }

        if(other instanceof Rectangle) {
            return other.encapsulate(this);
        }

        if(other instanceof Volume) {
            return other.encapsulate(this);
        }

        return null;

    }

    /**
     * @return Returns an array with a coordinates of the Point
     */
    public double[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return Arrays.toString(coordinates);
    }
}
