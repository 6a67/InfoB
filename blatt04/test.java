package blatt04;

public class test {

    public static void main(String[] args) {
        Rectangle test = new Rectangle(new Point2D(1,1), new Point2D(3,3));
        Rectangle test2 = new Rectangle(new Point2D(4,1.5), new Point2D(6,2.5));
        System.out.println(new Point2D(6,2.5).encapsulate(test));
        System.out.println(test.compareTo(test2));

        String testString = "Test\nt\nt\n";
        System.out.println(testString.split("\n").length);

    }

}

