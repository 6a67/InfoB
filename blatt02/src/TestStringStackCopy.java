/**
 * Klasse zum Testen der Funktionalität des Copy-Konstruktors
 */
public class TestStringStackCopy {

    public static void main(String[] args) {
        System.out.println("Starting test...");
        TestStringStackCopy test = new TestStringStackCopy();
        test.testCopyConstructor();
    }

    /**
     * Testet den Copy Constructor
     */
    public void testCopyConstructor() {
        StringStack st = new StringStack();

        st.push("Apfel");
        st.push("Birne");
        st.push("Orange");
        st.push("Tomate");

        StringStack copySt = new StringStack(st);

        st.pop();
        st.pop();

        copySt.pop();

        if(st.peek() == copySt.peek()) {
            System.out.println("Es handelt sich um eine Shallow-Copy");
        } else {
            System.out.println("Es handelt sich um eine Deep-Copy");
        }
    }
}
