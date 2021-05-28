public class TestMyList {

    public static void main(String[] args) {
        // Erstelle Liste und füge Items hinzu
        MyList<String> oriList = new MyList<String>();

        oriList.add("Test1");
        oriList.add("Test2");
        oriList.add("Test3");
        oriList.add("Test4");
        oriList.add("Test5");

        // Kopiere die Liste
        MyList<String> copyList = null;

        try {
            copyList = (MyList<String>) oriList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // Teste x.clone() != x
        System.out.print("x.clone() != x ist ");
        if(!(copyList != oriList)) {
            System.out.print("nicht ");
        }
        System.out.print("erfüllt.\n");

        // Teste x.clone().getClass() == x.getClass()
        System.out.print("x.clone().getClass() == x.getClass() ist ");
        if(!(oriList.getClass() == copyList.getClass())) {
            System.out.print("nicht ");
        }
        System.out.print("erfüllt.\n");

        // Teste x.clone().equals(x)
        System.out.print("x.clone().equals(x) ist ");
        if(!(copyList.equals(oriList))) {
            System.out.print("nicht ");
        }
        System.out.print("erfüllt.\n");

        // Teste die Elemente in der Liste
        oriList.reset();
        copyList.reset();
        boolean sameElements = true;
        System.out.format("\n%-16s%-16s\n", "oriList", "copyList");
        while(!oriList.endpos()) {
            System.out.format("%-16s%-16s\n", (String) oriList.elem(), (String) copyList.elem());
            if(!oriList.elem().equals(copyList.elem())) sameElements = false;
            oriList.advance();
            copyList.advance();
        }
        System.out.println("");

        System.out.print("Die beiden Listen enthalten ");
        if(!sameElements) {
            System.out.print("nicht ");
        }
        System.out.print("die gleichen Elemente.\n");


        // Teste ob die Listen unabhängig sind
        oriList.reset();
        copyList.reset();

        oriList.delete();
        copyList.delete();
        copyList.delete();

        System.out.print("Die beiden Listen sind ");
        if(oriList.elem() == copyList.elem()) {
            System.out.print("nicht ");
        }
        System.out.print("unabhängig.\n");
    }


}
