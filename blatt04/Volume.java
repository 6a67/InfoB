package blatt04;

public class Volume extends Geometry {

    private Point a,b;
    private Point[] corners;

    public Volume(Point a, Point b) {
        super(a.getCoordinates().length);

        if (a.getCoordinates().length != b.getCoordinates().length) {
            throw new RuntimeException("Dimensions have to be the same");
        }

        this.a = a;
        this.b = b;

        createCorners();
    }


    /**
     * Returns the volume of this Geometry. If {@link #dimensions()} is
     * <code>2</code>, the volume is the area.
     *
     * @return volume of this Geometry
     */
    @Override
    public double volume() {
        double[] coA = a.getCoordinates();
        double[] coB = b.getCoordinates();
        double volume = 1;
        for(int i = 0; i < coA.length; i++) {
            volume *= coA[i] - coB[i];
        }
        if(volume < 0) volume *= -1;
        return volume;
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
        if(other.dimensions() != this.dimensions()) return null;
        if(other instanceof Point) {
            return (Volume) this.properEncapsulate(new Volume(a, (Point) other));
        }
        if(other instanceof Volume) {
            return (Volume) this.properEncapsulate(this.properEncapsulate(other));
        }

        return null;
    }

    /**
     * A function that is used to help the encapsulate function
     * @param other Object that should be encapsulated
     * @return Returns a Geometry object that encapsulates both objects
     */
    private Geometry properEncapsulate(Geometry other) {
        if(other instanceof Volume) {
            Point g = a ,k = b;
            double currentVolume = this.volume();
            Point[] arr1 = this.getCorners();
            Point[] arr2 = ((Volume) other).getCorners();
            for(int i = 0; i < arr1.length; i++) {
                for(int j = 0; j < arr2.length; j++) {
                    double tmpVolume = new Volume(arr1[i], arr2[j]).volume();
                    if(tmpVolume > currentVolume) {
                        g = arr1[i];
                        k = arr2[j];
                        currentVolume = tmpVolume;
                    }
                }
            }

            if(other.volume() > currentVolume) {
                //currentVolume = other.volume();
                g = arr2[0];
                k = arr2[1];
            }

            return new Volume(g, k);
        }
        return null;
    }

    /**
     * Finds the coordinates of corners of the current object
     */
    private void createCorners() {
        double[] arr1 = a.getCoordinates();
        double[] arr2 = b.getCoordinates();
        double[][] cornerCoordinates = this.recurCorners(arr1, arr2);
        corners = new Point[cornerCoordinates.length];
        for(int i = 0; i < cornerCoordinates.length; i++) {
            corners[i] = new Point(cornerCoordinates[i]);
        }
    }

    /**
     * Recursive function that is used for createCorners
     * @param a Coordinates of a
     * @param b Coordinates of b
     * @return Returns an 2D array with all the coordinates of the corners
     */
    private double[][] recurCorners(double[] a, double[] b) {
        double[][] corns;

        if(a.length > 2) {
            double[] arr1 = new double[a.length - 1];
            double[] arr2 = new double[a.length - 1];

            for(int i = 0; i < a.length - 1; i++) {
                arr1[i] = a[i + 1];
                arr2[i] = b[i + 1];
            }

            corns = recurCorners(arr1, arr2);
        } else {
            corns = new double[2][1];
            corns[0] = new double[1];
            corns[1] = new double[1];

            corns[0][0] = a[1];
            corns[1][0] = b[1];
        }

        double[][] returnArray = new double[corns.length * 2][corns[0].length + 1];


        for(int j = 0; j < corns.length; j++) {
            returnArray[j][0] = a[0];
            returnArray[corns.length + j][0] = b[0];

            for(int i = 0; i < corns[0].length; i++) {
                returnArray[j][i + 1] = corns[j][i];
                returnArray[corns.length + j][i + 1] = corns[j][i];
            }
        }

        return returnArray;

    }

    /**
     * @return Returns an array that contains all corners
     */
    public Point[] getCorners() {
        return corners;
    }

    /**
     * @return Returns a String that contains all coordinates for the corners
     */
    @Override
    public String toString() {
        String returnString = "";
        for(int i = 0; i < corners.length; i++) {
            returnString += corners[i].toString() + "\n";
        }
        return returnString;
    }
}
