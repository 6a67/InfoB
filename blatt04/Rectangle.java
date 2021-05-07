package blatt04;

public class Rectangle extends Volume {

    public Rectangle(Point2D a, Point2D b) {
        super(a, b);
    }

    /**
     * @param other the Geometry to be encapsulated together with this Geometry
     * @return Returns a Geometry that encapsulates both objects
     */
    public Geometry encapsulate(Geometry other) {
        if(other instanceof Point2D) {
            return super.encapsulate((Point2D) other);
        }

        if(other instanceof Point) {
            return super.encapsulate(other);
        }

        if(other instanceof Rectangle) {
            return super.encapsulate(other);
        }

        if(other instanceof Volume) {
            return super.encapsulate(other);
        }

        return null;
    }

}
