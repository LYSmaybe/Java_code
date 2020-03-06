package MyBinarySearchTree;

public class Main {

    //可以创建不一样的比较器
    /*
    private static class PersonComparator implements Comparator<Person>{ //此类也可以使用内部匿名类来实现
        public int compare(Person e1, Person e2){
            return e1.getAge() - e2.getAge();
        }
    }
    private static class PersonComparator2 implements Comparator<Person>{
        public int compare(Person e1, Person e2){
            return e2.getAge() - e1.getAge();
        }
    }
    */

    public static void main(String[] args){

        Integer data[] = new Integer[] {
                7,4,9,2,5,8,11,3,12,1
        };

        MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<Integer>();
        for(int i = 0; i < data.length; i++){
            bst.add(data[i]);
        }

        /*
        //个性化比较器
        MyBinarySearchTree<Person> bst1 = new MyBinarySearchTree<Person>(new PersonComparator()); //可以根据创建的不同的方法进行比较
        bst1.add(new Person(12));
        bst1.add(new Person(15));

        MyBinarySearchTree<Person> bst2 = new MyBinarySearchTree<Person>(new PersonComparator2());
        bst2.add(new Person(12));
        bst2.add(new Person(15));
        */

        /*
        //遍历后的打印
        System.out.println(bst.size());
        System.out.println("\n");
        bst.preorderTraversal();
        System.out.println("\n");
        bst.inorderTraversal();
        System.out.println("\n");
        bst.levelOrderTraversal();
        */

         /*
        //遍历后的个性化打印
        bst.levelOrder(new MyBinarySearchTree.Visitor<Integer>(){ //这里需要导入接口
            public void visit(Integer element){
                System.out.print("_" + element + "_");
            }
        });

        bst.ineorder(new MyBinarySearchTree.Visitor<Integer>() {
            public void visit(Integer element) {
                System.out.print("_" + (element + 2) + "_"); //遍历每个值并 +2
            }
        });
        */

        /*
        //高度 height 的 test
        System.out.println(bst.height1());
        System.out.println(bst.height2());
        */

        /*
        //test完全二叉树
        System.out.println(bst.isComplete1());
        */

        //test 删除节点
        System.out.println(bst.size());
        bst.levelOrderTraversal();
        bst.remove(7);
        bst.remove(1);
        bst.remove(3);
        bst.remove(12);
        System.out.println("\n");
        System.out.println(bst.size());
        bst.levelOrderTraversal();
    }
}



























