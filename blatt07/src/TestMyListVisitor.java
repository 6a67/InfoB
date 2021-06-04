import visitor.MyList;
import visitor.Visitor;

public class TestMyListVisitor {

    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<Integer>();

        // Füllt myList mit geraden Zahlen
        for(int i = 0; i < 55; i++) {
            myList.add(2 * i);
            myList.advance();
        }
        myList.reset();

        // Akzeptiert nur gerade Zahlen
        final int[] visitedElements = {0};
        Visitor<Integer> visitor = new Visitor<Integer>() {
            @Override
            public boolean visit(Integer o) {
                visitedElements[0]++;
                return (o % 2 == 0);
            }
        };

        myList.accept(visitor);

        // Testet ob die ganze Liste von dem Visitor durchlaufen wurde
        assert(visitedElements[0] == 55);

        // Akzeptiert nur Zahlen mit weniger als 3 Ziffern
        visitedElements[0] = 0;
        Visitor<Integer> visitor2 = new Visitor<Integer>() {
            @Override
            public boolean visit(Integer o) {
                visitedElements[0]++;
                return (o < 100 && o > -100);
            }
        };

        myList.accept(visitor2);

        // Testet ob die ganze Liste von dem Visitor durchlaufen wurde
        assert(visitedElements[0] != 55);

    }

}
