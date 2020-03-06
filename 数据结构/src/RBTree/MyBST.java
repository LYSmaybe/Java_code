package RBTree;

import java.util.Comparator;


@SuppressWarnings("unchecked")
public class MyBST<E> extends MyBinaryTree<E> { //extends Comparable：即传入的元素必须实现可比较的接口（不能写死在这里，所以通过判断写在了后面）
                                                        //也可以写一个比较器接口，实现方法时可以变换逻辑

    private Comparator<E> comparator; //构造比较器属性

    public MyBST(){ //创建二叉搜索树时，就传入比较器构造方法
        this(null); //调用已有的构造方法，传入 null，即没有传入比较器
    }

    public MyBST(Comparator<E> comparator){ //创建二叉搜索树时，就传入比较器构造方法
        this.comparator = comparator;
    }


    //方法实现
    public void add(E element){
        elementNotNullCheck(element);
        //判断:添加的是根节点（即第一个结点）
        if(root == null){
            root = createNode(element, null); //在设置创建结点的接口后，此处创建结点需要进行选择
            size++; //一定不要忘记size

            aftreAdd(root);
            return;
        }

        //添加的不是第一个结点

        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while(node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 此处为相等情况
                return;
            }
        }

            //将待插入元素创建成新的节点，进行插入
            Node<E> newNode = createNode(element, parent); //在设置创建结点的接口后，此处创建结点需要进行选择
            if(cmp > 0){
                parent.right = newNode;
            }else{
                parent.left = newNode;
            }
            size++;

            aftreAdd(newNode);
    }
    //添加“平衡”接口交给子类实现
    protected void aftreAdd(Node<E> node){ }
    protected void aftreRemove(Node<E> node){ } //传入被删除结点 或 用以取代被删除结点的子节点

    //禁止输入null：输入数据是否为null的检测
    private void elementNotNullCheck(E element){ //由于二叉树中不能有空元素，所以要有检查插入数据是否为空的操作
        if(element == null){
            throw new IllegalArgumentException("传入的数据不能为空");
        }
    }

    //比较 e1 和 e2 的大小
    private int compare(E e1, E e2){
        //return e1.compareTo(e2); //利用泛型中自己实现的 compareTo 方法
        if(comparator != null){ //分类讨论是否传入了个性化比较器
            return comparator.compare(e1, e2); //使用传入的 comparator 中的 comparator 方法？？？前一个 comparator 是啥？
        }
        //当没有传入个性化比较器时，类型强制转换后再使用 compareTo 方法
        return ((Comparable<E>) e1).compareTo(e2);
    }

    //删除某节点
    public void remove(E element){ //首先要获取元素的结点，再进行删除
        remove(node(element));
    }

    private void remove(Node<E> node){ //给结点，删除元素
        if(node == null) return; //表示没有此结点

        size--;

        if(node.hasTwoChildren()){ //node 是度为 2 的结点
            Node<E> s = successor(node); //使用 node 的后继节点代替
            node.element = s.element;
            node = s; //同一后面的操作：后面只需要全部删除 node 结点就可以了
        }

        Node<E> replacement = node.left != null ? node.left : node.right; //node 是度为 1 或 0 的结点：找到子节点，取代node

        if(replacement != null){ //node 是度为 1 的结点：用左右子节点取代它
            //用替代的值取代要删除的值，连接到父节点上
            replacement.parent = node.parent;
            //更改 parent 子节点的指向
            if(node.parent == null){ //node 度为 1，且为根节点
                root = replacement;
            }else if(node == node.parent.left){
                node.parent.left = replacement;
            }else {
                node.parent.right = replacement;
            }
            //真正删除完结点之后，进行调整
            aftreRemove(replacement); //没有影响的改造
        }else if(node.parent == null){ //node 是度为 0 的结点：此处 node 是叶子节点 + 根节点，直接删除
            root = null;
            //真正删除完结点之后，进行调整
            aftreRemove(node);
        }else{ //node 是度为 0 的结点：此处 node 是叶子节点，让其父节点的子节点设置为空
            if(node == node.parent.left){
                node.parent.left = null;
            }else {
                node.parent.right = null;
            }
            //真正删除完结点之后，进行调整
            aftreRemove(node);
        }
    }

    private Node<E> node(E element){ //给元素，获取结点
        Node<E> node = root;
        while(node != null){
            int cmp = compare(element, node.element); //比较给定元素和我设置的节点元素是否相等，相等说明该节点为寻找的结点
            if(cmp == 0) return node;
            if(cmp > 0){
                node = node.right;
            }else {
                node = node.left;
            }
        }
        return null; //最后没有找到就返回不存在
    }


    //其他方法内容
    public boolean contains(E element){
        return node(element) != null;
    }
}