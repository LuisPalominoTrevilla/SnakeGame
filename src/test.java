
public class test {

    public static void main(String[] args) {
        SparceMatrix<Integer> sm = new SparceMatrix<Integer>(1000, 1000);
        sm.add(2, 1, 4);
        sm.add(2, 0, 5);
        sm.add(2, 9, 5);
        sm.add(1, 1, 3);
        sm.add(3, 4, 199);
        sm.add(5, 4, 200);
        sm.add(2, 5, 78);

        System.out.println(sm);

        sm.remove(1, 1);
        sm.add(1, 0, 3);
        sm.remove(5, 4);
        sm.remove(1, 0);
        sm.remove(3, 4);
        sm.remove(2, 0);
        sm.remove(2, 1);
        sm.remove(2, 5);
        sm.remove(2, 9);
        sm.add(1, 9, 1);
        sm.add(0, 2, 1);
        sm.remove(0, 2);
        sm.add(0, 9, 100);
        sm.add(0, 0, 10);
        sm.add(6, 9, 20);
        sm.add(0, 1, 10);
        sm.remove(0, 9);

        System.out.println(sm);
        
        
    }
}
