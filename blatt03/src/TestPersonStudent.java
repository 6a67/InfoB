public class TestPersonStudent {

    public static void main(String[] args) {
        TestPersonStudent test = new TestPersonStudent();
        test.testAll();
    }

    /**
     * Testet die Reflexivität der Objekte
     */
    public void testReflexivity() {
        Person p = new Person("マーク");
        Student s = new Student("マーク", 1207);

        System.out.println("p.equals(p) " + p.equals(p));
        System.out.println("s.equals(s) " + s.equals(s));

        if(!p.equals(p)) {
            System.out.println("Reflexivität ist bei Person nicht erfüllt");
        }

        if(!s.equals(s)) {
            System.out.println("Reflexivität ist bei Student nicht erfüllt");
        }
    }

    /**
     * Testet die Symmetrie der Objekte
     */
    public void testSymmetry() {
        Person p1 = new Person("ヨナ");
        Student s1 = new Student("ヨナ", 1208);

        Person p2 = new Person("ヨナ");
        Student s2 = new Student("ヨナ", 1208);


        System.out.println("p1.equals(s1) " + p1.equals(s1));
        System.out.println("s1.equals(p1) " + s1.equals(p1));

        System.out.println("p1.equals(p2) " + p1.equals(p2));
        System.out.println("p2.equals(p1) " + p2.equals(p1));

        System.out.println("s1.equals(s2) " + s1.equals(s2));
        System.out.println("s2.equals(s1) " + s2.equals(s1));

        if(p1.equals(s1) != s1.equals(p1)) {
            System.out.println("Symmetrie zwischen Person und Student ist nicht erfüllt");
            /* Ein Vergleich zwischen Student und Person oder Person und Student sollte
             * immer false zurück geben, da diese nicht die gleichen Attribute besitzen
             */
        }

        if(p1.equals(p2) != p2.equals(p1)) {
            System.out.println("Symmetrie zwischen Person und Person ist nicht erfüllt");
        }

        if(s1.equals(s2) != s2.equals(s1)) {
            System.out.println("Symmetrie zwischen Student und Student ist nicht erfüllt");
        }


    }

    /**
     * Testet die Transitivität der Objekte
     */
    public void testTransitivity() {
        Person p1 = new Person("Person");
        Student s1 = new Student("Student", 1);

        Person p2 = new Person("Person");
        Student s2 = new Student("Student", 1);

        Person p3 = new Person("Person");
        Student s3 = new Student("Student", 1);

        System.out.println("p1.equals(p2) " + p1.equals(p2));
        System.out.println("p2.equals(p3) " + p2.equals(p3));
        System.out.println("p1.equals(p3) " + p1.equals(p3));

        if(!(p1.equals(p2) && p2.equals(p3) && p1.equals(p3))) {
            System.out.println("Traversität zwischen Person und Person ist nicht erfüllt");
        }

        System.out.println("s1.equals(s2) " + s1.equals(s2));
        System.out.println("s2.equals(s3) " + s2.equals(s3));
        System.out.println("s1.equals(s3) " + s1.equals(s3));

        if(!(s1.equals(s2) && s2.equals(s3) && s1.equals(s3))) {
            System.out.println("Traversität zwischen Student und Student ist nicht erfüllt");
        }



        Person p4 = new Person("Name");
        Student s4 = new Student("Name", 1);
        Student s5 = new Student("Name", 1);


        System.out.println("p4.equals(s4) " + p4.equals(s4));
        System.out.println("s4.equals(s5) " + s4.equals(s5));
        System.out.println("p4.equals(s5) " + p4.equals(s5));

        if(p4.equals(s4) && s4.equals(s5) && p4.equals(s5)) {
            System.out.println("Traversität zwischen Person und Student ist nicht erfüllt");
        }


    }

    /**
     * Testet, ob equals immer das gleiche Ergebnis zurück gibt
     */
    public void testEqCons() {
        Person p1 = new Person("Person");
        Student s1 = new Student("Student", 1);

        Person p2 = new Person("Person");
        Student s2 = new Student("Student", 1);

        if(p1.equals(p2) != p1.equals(p2)) {
            System.out.println("Equals von Person und Person bleibt nicht konsistent");
        }

        if(s1.equals(s2) != s1.equals(s2)) {
            System.out.println("Equals von Student und Student bleibt nicht konsistent");
        }

        if(s1.equals(p1) != s1.equals(p1)) {
            System.out.println("Equals von Student und Person bleibt nicht konsistent");
        }

        if(p1.equals(s1) != p1.equals(s1)) {
            System.out.println("Equals von Person und Student bleibt nicht konsistent");
        }

    }

    /**
     * Testet den Vergleich mit null
     */
    public void testEqNull() {
        Person p1 = new Person("Person");
        Student s1 = new Student("Student", 1);

        System.out.println("p1.equals(null) " + s1.equals(null));
        System.out.println("s1.equals(null) " + s1.equals(null));

        if(p1.equals(null)) {
            System.out.println("Vergleich mit null bei Person ist nicht false");
        }

        if(s1.equals(null)) {
            System.out.println("Vergleich mit null bei Student ist nicht false");
        }

    }

    /**
     * Testet, ob der Hash Code immer gleich bleibt
     */
    public void testHashCons() {
        Person p1 = new Person("Person");

        if(p1.hashCode() != p1.hashCode()) {
            System.out.println("Der Hash Code von Person bleibt nicht konsistent");
        }

        Student s1 = new Student("Student", 1);

        if(s1.hashCode() != s1.hashCode()) {
            System.out.println("Der Hash Code von Student bleibt nicht konsistent");
        }

    }

    /**
     * Testet, ob die der Hash Code zweier Objekte, welche laut equals gleich sind, auch gleich sind
     */
    public void testHashEq() {
        Person p1 = new Person("Name");
        Student s1 = new Student("Name", 1);

        Person p2 = new Person("Name");
        Student s2 = new Student("Name", 1);

        if(p1.equals(s1) && p1.hashCode() != s1.hashCode()) {
            System.out.println("Eine Person wurde mit einem Student verglichen, was true zurück gab, jedoch ist der Hash Code der beiden Objekte anders");
        }

        if(s1.equals(p1) && p1.hashCode() != s1.hashCode()) {
            System.out.println("Ein Student wurde mit einer Person verglichen, was true zurück gab, jedoch ist der Hash Code der beiden Objekte anders");
        }

        if(p1.equals(p2) && p1.hashCode() != p2.hashCode()) {
            System.out.println("Der Hash Code von zwei gleichen Personen ist anders");
        }

        if(s1.equals(s2) && s1.hashCode() != s2.hashCode()) {
            System.out.println("Der Hash Code von zwei gleichen Student ist anders");
        }

    }

    public void testAll() {
        this.testReflexivity();
        this.testSymmetry();
        this.testTransitivity();
        this.testEqNull();
        this.testHashCons();
        this.testHashEq();
    }

}
