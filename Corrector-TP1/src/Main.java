class C {
    public void print() {
        System.out.println("C");
    }
}
class C1 extends C {
    public void print() {
        System.out.println("C1");
    }
}
class Main{
    public boolean echange(int a, int b)
    {
        System.out.println("Valeur initiale a:" + a);
        System.out.println("Valeur intiale b:" + b);
        int temp;
        temp = a;
        a = b;
        b = temp;
        System.out.println("Valeur modif a:" + a);
        System.out.println("Valeur modif b:" + b);
        return false;
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.echange(2,3));
    }

}


/*public class Main {
    public static void main(String[] args) {
        //Declaring GUI
        //gui.GUI g = new gui.GUI();
        String s = "Allo";
        String f = "Allo";
        System.out.println();
        //TODO
        // - Fix bugs in interface
        // - Optimize algorithm
    }
}*/
