import iterator.MyList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class TestMyListIterator {

    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<Integer>();

        for(int i = 0; i < 17; i++) {
            myList.add(i);
            myList.advance();
        }
        myList.reset();

        // Testen ob der Iterator funktioniert
        int j = 0;
        for(Integer i: myList) {
            assert(i == j++);
        }


        // Testen ob das Löschen des ersten Elements funktioniert
        myList.iterator().remove();

        j = 1;
        for(Integer i: myList) {
            assert(i == j++);
        }


        // Testen ob das Löschen von mehreren Elementen innerhalb der Liste funktioniert

        // Erstelle referenz Liste mit gleichen Zahlen wie myList, außer 0, 5, 11
        MyList<Integer> refList = new MyList<Integer>();
        for(int i = 1; i < 17; i++) {
            if(i == 5 || i == 11) continue;
            refList.add(i);
            refList.advance();
        }
        refList.reset();

        // Lösche mit dem Iterator aus myList die 5 und die 11
        Iterator<Integer> iterator = myList.iterator();
        while(iterator.hasNext()) {
            int element = iterator.next();
            if(element == 5 || element == 11) iterator.remove();
        }

        // Vergleiche refList mit myList
        refList.reset();
        myList.reset();

        while(!myList.endpos()) {
            assert(refList.elem() == myList.elem());
            refList.advance();
            myList.advance();
        }

        // Testest, ob der Interator eine Exception wirft, wenn die Liste von außen verändert wird
        myList.reset();
        try {
            for(Integer i: myList) {
                if(i.equals(12)) myList.delete();
            }
            assert(false);
        } catch (ConcurrentModificationException e) {
            System.out.println("Der Iterator wirft eine ConcurrentModificationException, wenn ein Element von außen entfernt wird");
        }

        myList.reset();
        try {
            for(Integer i: myList) {
                if(i.equals(12)) myList.add(58);
            }
            assert(false);
        } catch (ConcurrentModificationException e) {
            System.out.println("Der Iterator wirft eine ConcurrentModificationException, wenn ein Element von außen hinzugefügt wird");
        }

    }

}
