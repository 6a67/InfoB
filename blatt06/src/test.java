import util.Heap;

public class test {

    public static void main(String[] args) {
        MyList<String> testList = new MyList<String>();
        testList.add("Test");
        testList.add("Test2");
        testList.add("Test3");
        testList.add("Test4");
        testList.add("Test5");

        Heap<String> t = new Heap<String>();

        MyList<String> newList = null;

        try {
            newList = testList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        newList.delete();
        newList.delete();


        testList.reset();
        System.out.println("testList:");
        while(!testList.endpos()) {
            System.out.println(testList.elem());
            testList.advance();
        }


        newList.reset();
        System.out.println("newList:");
        while(!newList.endpos()) {
            System.out.println(newList.elem());
            newList.advance();
        }


        System.out.println("done");
    }

}
