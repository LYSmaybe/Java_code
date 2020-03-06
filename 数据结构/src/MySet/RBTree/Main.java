package MySet.RBTree;

public class Main {
    public static void main(String[] args){

        Integer data[] = new Integer[] {
                67,52,92,96,53,95,13,63,34,82,76,54,9,68,39
        };

        MyRBTree<Integer> rbt = new MyRBTree<>();
        for(int i = 0; i < data.length; i++){
            rbt.add(data[i]);
        }
    }
}

































