import iterator.MyList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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



        // Testen ob das Löschen von mehreren Elementen innerhalb der Liste funktioniert

        // Erstelle referenz Liste mit gleichen Zahlen wie myList, außer 0, 5, 11
        MyList<Integer> refList = new MyList<Integer>();
        for(int i = 0; i < 17; i++) {
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

        // Testet ob einer Iterator failed, wenn ein anderer Iterator ein Element entfernt
        try {
            Iterator<Integer> iterator2 = myList.iterator();
            iterator2.next();
            for(Integer i: myList) {
                if(i.equals(9)) iterator2.remove();
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Der Iterator wirft eine ConcurrentModificationException, wenn ein andere Iterator von ein Element entfernt");
        }

        // Testet ob eine Exception geworfen wird, wenn die Methode next() nicht vorm remove() aufgerufen wurde, bzw. zwei mal remove() für ein next() aufgerufen wird
        try {
            Iterator<Integer> iterator3 = myList.iterator();
            iterator3.remove();
        } catch (RuntimeException e) {
            System.out.println("Der Iterator wirft eine RuntimeException, wenn remove() vor next() aufgerufen wird");
        }

        try {
            Iterator<Integer> iterator4 = myList.iterator();
            iterator4.next();
            iterator4.remove();
            iterator4.remove();
        } catch (RuntimeException e) {
            System.out.println("Der Iterator wirft eine RuntimeException, wenn zwei Mal remove() nach nur einem next() aufgerufen wird");
        }

        // Testet was passiert, wenn der Iterator über das Ende der Liste hinaus geht
        MyList<Integer> list2 = new MyList<Integer>();
        list2.add(1);
        list2.add(2);

        try {
            Iterator<Integer> iterator5 = list2.iterator();
            iterator5.next();
            iterator5.next();
            iterator5.next();
        } catch (NoSuchElementException e) {
            System.out.println("Der Iterator wirft eine NoSuchElementException, wenn dieser über das letzte Element hinaus geht");
        }

        // Testet remove() auf einer leeren Liste
        try {
            Iterator<Integer> iterator6 = list2.iterator();
            iterator6.next();
            iterator6.remove();
            iterator6.next();
            iterator6.remove();
            iterator6.remove();
        } catch(NoSuchElementException e) {
            System.out.println("Der Iterator wirft eine NoSuchElementException, wenn remove() auf einer leeren Liste aufgerufen wird");
        }

    }

}
