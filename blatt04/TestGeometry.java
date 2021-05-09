package blatt04;

public class TestGeometry {

    public static void main(String[] args) {
        System.out.println("Starte Test...");
        TestGeometry test = new TestGeometry();
        test.testDimensions();
        test.testEncapsulate();
        test.testVolume();
        test.testCompareTo();
        System.out.println("Test mit " + TestModules.getAnzahlFehler() + " Fehlern abgeschlossen");
    }

    /**
     * Testet die verschiedenen Dimensionen der Typen
     */
    public void testDimensions() {
        TestModules.assertBool((new Point2D(1,1)).dimensions() == 2, "Die Dimension von Punkt2D ist falsch");
        TestModules.assertBool((new Point(1,1,1,1)).dimensions() == 4, "Die Dimension von Punkt ist falsch");
        Rectangle rect = new Rectangle(new Point2D(1,1), new Point2D(3,3));
        TestModules.assertBool(rect.dimensions() == 2, "Die Dimension von Rectangle ist falsch");
        Volume vol = new Volume(new Point(1,1,1,1,1), new Point(3,3,3,3,3));
        TestModules.assertBool(vol.dimensions() == 5, "Die Dimension von Volume ist falsch");
    }

    /**
     * Testet die encapsulate Funktion in verschiedenen Fällen
     */
    public void testEncapsulate() {
        Volume twoPoint2D = (Volume) (new Point2D(1,1)).encapsulate(new Point2D(3,3));
        /*double[][] tmpArr1 = {{1,1}, {1,3}, {3,1}, {3,3}};
        for(int i = 0; i < corners1.length; i++) {
            for(int j = 0; j < tmpArr1.length; j++) {

            }
        }*/
        String corners1Str = twoPoint2D.toString();
        boolean validRect1 = corners1Str.contains("[1.0, 3.0]") && corners1Str.contains("[1.0, 1.0]") && corners1Str.contains("[3.0, 3.0]") && corners1Str.contains("[3.0, 1.0]") && corners1Str.split("\n").length == 4;
        TestModules.assertBool(validRect1, "Das erzeugte Rechteckt von zwei Point2D ist falsch");




        Volume twoPoint = (Volume) (new Point(1,1,1)).encapsulate(new Point(3,3,3));
        String corners2Str = twoPoint.toString();
        boolean validVolume1 = corners2Str.contains("[1.0, 1.0, 1.0]") && corners2Str.contains("[1.0, 1.0, 3.0]") && corners2Str.contains("[1.0, 3.0, 1.0]") && corners2Str.contains("[1.0, 3.0, 3.0]") && corners2Str.contains("[3.0, 1.0, 1.0]") && corners2Str.contains("[3.0, 1.0, 3.0]") && corners2Str.contains("[3.0, 3.0, 1.0]") && corners2Str.contains("[3.0, 3.0, 3.0]") && corners2Str.split("\n").length == 8;
        TestModules.assertBool(validVolume1, "Das erzeugte Volume von zwei Point ist falsch");


        Volume rectAndPoint = (Volume) (new Rectangle(new Point2D(1,1), new Point2D(3,3))).encapsulate(new Point2D(4,1.5));
        String corners3Str = rectAndPoint.toString();
        boolean validRect2 = corners3Str.contains("[1.0, 1.0]") && corners3Str.contains("[1.0, 3.0]") && corners3Str.contains("[4.0, 1.0]") && corners3Str.contains("[4.0, 3.0]") && corners3Str.split("\n").length == 4;
        TestModules.assertBool(validRect2, "Das erzeugte Rechteckt von Rectangle und Point2D ist falsch");


        Volume rectAndPointInside = (Volume) (new Rectangle(new Point2D(1,1), new Point2D(3,3))).encapsulate(new Point2D(2,2));
        String corners4Str = rectAndPointInside.toString();
        boolean validRect3 = corners4Str.contains("[1.0, 1.0]") && corners4Str.contains("[1.0, 3.0]") && corners4Str.contains("[3.0, 1.0]") && corners4Str.contains("[3.0, 3.0]") && corners4Str.split("\n").length == 4;
        TestModules.assertBool(validRect3, "Das erzeugte Rechteckt von Rectangle und Point2D, wenn der Point2D im Rectangle liegt, ist falsch");


        Volume rectAndPointTop = (Volume) (new Rectangle(new Point2D(1,1), new Point2D(3,3))).encapsulate(new Point2D(4,5));
        String corners5Str = rectAndPointTop.toString();
        boolean validRect4 = corners5Str.contains("[1.0, 1.0]") && corners5Str.contains("[1.0, 5.0]") && corners5Str.contains("[4.0, 1.0]") && corners5Str.contains("[4.0, 5.0]") && corners4Str.split("\n").length == 4;
        TestModules.assertBool(validRect4, "Das erzeugte Rechteckt von Rectangle und Point2D, wenn der Point2D nicht parallel zum Rechteck liegt, ist falsch");

        Volume rectAndrect = (Volume) (new Rectangle(new Point2D(1,1), new Point2D(3,3))).encapsulate(new Rectangle(new Point2D(4,1.5), new Point2D(6,2.5)));
        String corners6Str = rectAndrect.toString();
        boolean validRect5 = corners6Str.contains("[1.0, 1.0]") && corners6Str.contains("[1.0, 3.0]") && corners6Str.contains("[6.0, 1.0]") && corners6Str.contains("[6.0, 3.0]") && corners6Str.split("\n").length == 4;
        TestModules.assertBool(validRect5, "Das erzeugte Rechteckt von Rectangle und Rectangle, wenn diese parallel liegen, ist falsch");

        Volume rectAndrectTop = (Volume) (new Rectangle(new Point2D(1,1), new Point2D(3,3))).encapsulate(new Rectangle(new Point2D(4,5), new Point2D(7,9)));
        String corners7Str = rectAndrectTop.toString();
        boolean validRect6 = corners7Str.contains("[1.0, 1.0]") && corners7Str.contains("[1.0, 9.0]") && corners7Str.contains("[7.0, 1.0]") && corners7Str.contains("[7.0, 9.0]") && corners7Str.split("\n").length == 4;
        TestModules.assertBool(validRect6, "Das erzeugte Rechteckt von Rectangle und Rectangle, wenn diese nicht parallel liegen, ist falsch");

        Volume rectAndrectInside = (Volume) (new Rectangle(new Point2D(1,1), new Point2D(3,3))).encapsulate(new Rectangle(new Point2D(1.5,1.5), new Point2D(2.5,2.5)));
        String corners8Str = rectAndrectInside.toString();
        boolean validRect7 = corners8Str.contains("[1.0, 1.0]") && corners8Str.contains("[1.0, 3.0]") && corners8Str.contains("[3.0, 1.0]") && corners8Str.contains("[3.0, 3.0]") && corners8Str.split("\n").length == 4;
        TestModules.assertBool(validRect7, "Das erzeugte Rechteckt von Rectangle und Rectangle, wenn das eine in dem anderen liegt, ist falsch");

        Volume volumeAndPoint = (Volume) (new Volume(new Point(1,1,1), new Point(3,3,3))).encapsulate(new Point(4,4,4));
        String corners9Str = volumeAndPoint.toString();
        boolean validVolume2 = corners9Str.contains("[1.0, 1.0, 1.0]") && corners9Str.contains("[1.0, 1.0, 4.0]") && corners9Str.contains("[1.0, 4.0, 1.0]") && corners9Str.contains("[1.0, 4.0, 4.0]") && corners9Str.contains("[4.0, 1.0, 1.0]") && corners9Str.contains("[4.0, 1.0, 4.0]") && corners9Str.contains("[4.0, 4.0, 1.0]") && corners9Str.contains("[4.0, 4.0, 4.0]") && corners9Str.split("\n").length == 8;
        TestModules.assertBool(validVolume2, "Das erzeugte Volume von Volume und Point ist falsch");

        Volume volumeAndPointParallel = (Volume) (new Volume(new Point(1,1,1), new Point(3,3,3))).encapsulate(new Point(1,1,4));
        String corners10Str = volumeAndPointParallel.toString();
        boolean validVolume3 = corners10Str.contains("[1.0, 1.0, 1.0]") && corners10Str.contains("[1.0, 1.0, 4.0]") && corners10Str.contains("[1.0, 3.0, 1.0]") && corners10Str.contains("[1.0, 3.0, 4.0]") && corners10Str.contains("[3.0, 1.0, 1.0]") && corners10Str.contains("[3.0, 1.0, 4.0]") && corners10Str.contains("[3.0, 3.0, 1.0]") && corners10Str.contains("[3.0, 3.0, 4.0]") && corners10Str.split("\n").length == 8;
        TestModules.assertBool(validVolume3, "Das erzeugte Volume von Volume und Point, welche parallel sind, ist falsch");

        Volume volumeAndVolume = (Volume) (new Volume(new Point(1,1,1), new Point(3,3,3))).encapsulate(new Volume(new Point(4,4,4), new Point(5,5,5)));
        String corners11Str = volumeAndVolume.toString();
        boolean validVolume4 = corners11Str.contains("[1.0, 1.0, 1.0]") && corners11Str.contains("[1.0, 1.0, 5.0]") && corners11Str.contains("[1.0, 5.0, 1.0]") && corners11Str.contains("[1.0, 5.0, 5.0]") && corners11Str.contains("[5.0, 1.0, 1.0]") && corners11Str.contains("[5.0, 1.0, 5.0]") && corners11Str.contains("[5.0, 5.0, 1.0]") && corners11Str.contains("[5.0, 5.0, 5.0]") && corners11Str.split("\n").length == 8;
        TestModules.assertBool(validVolume4, "Das erzeugte Volume von Volume und Volume ist falsch");

        Volume volumeAndVolumeInside = (Volume) (new Volume(new Point(1,1,1), new Point(3,3,3))).encapsulate(new Volume(new Point(1.5,1.5,1.5), new Point(2.5,2.5,2.5)));
        String corners12Str = volumeAndVolumeInside.toString();
        boolean validVolume5 = corners12Str.contains("[1.0, 1.0, 1.0]") && corners12Str.contains("[1.0, 1.0, 3.0]") && corners12Str.contains("[1.0, 3.0, 1.0]") && corners12Str.contains("[1.0, 3.0, 3.0]") && corners12Str.contains("[3.0, 1.0, 1.0]") && corners12Str.contains("[3.0, 1.0, 3.0]") && corners12Str.contains("[3.0, 3.0, 1.0]") && corners12Str.contains("[3.0, 3.0, 3.0]") && corners12Str.split("\n").length == 8;
        TestModules.assertBool(validVolume5, "Das erzeugte Volume von Volume und Volume, wenn das eine in dem anderen liegt, ist falsch");
    }

    /**
     * Testet die volume Funktion in verschiedenen Fällen
     */
    public void testVolume() {
        TestModules.assertBool(new Point2D(1,1).volume() == 0, "Das Volume von Point2D ist falsch");
        TestModules.assertBool(new Point(1,1,1).volume() == 0, "Das Volume von Point ist falsch");
        TestModules.assertBool((new Rectangle(new Point2D(1,1), new Point2D(3,3))).volume() == 4, "Das Volume von Rectangle ist falsch");
        TestModules.assertBool((new Volume(new Point(1,1,1), new Point(3,3,3))).volume() == 8, "Das Volume von Volume ist falsch");
    }

    /**
     * Testet compareTo
     */
    public void testCompareTo() {
        Volume vol1 = new Volume(new Point(0,0,0), new Point(3,3,3));
        Volume vol2 = new Volume(new Point(1,1,1), new Point(3,3,3));
        Volume vol3 = new Volume(new Point(2,2,2), new Point(3,3,3));
        Volume vol4 = new Volume(new Point(0,0,0), new Point(3,3,3));

        TestModules.assertBool(vol1.compareTo(vol2) > 0 && vol2.compareTo(vol3) > 0 && vol1.compareTo(vol3) > 0, "Fehler bei der Transitivität von compareTo()");

        TestModules.assertBool(vol1.compareTo(vol4) == 0, "Fehler bei compareTo() mit gleichem Volume");
    }

}
