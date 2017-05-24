public class Avl {

    private class Node{
        int key;
        Node left;
        Node right;
        int height;

        Node(int key) {this.key = key; left = right = null; height = 1; }
    }

    private Node root;
    private int size;

    Avl(){
        root = null;
    }

    int getHeight(){
        return root.height;
    }

    int getSize(){
        return size;
    }

    private int getHeight(Node p){
        if (p == null){
            return 0;
        }else {
            return p.height;
        }
    }

    private int getBalanceFactor(Node p){
        return getHeight(p.right)-getHeight(p.left);
    }

    private Node fixHeight(Node p){
        int heightLeft = getHeight(p.left);
        int heightRight = getHeight(p.right);
        if (heightLeft > heightRight){
            p.height = heightLeft + 1;
        }else {
            p.height = heightRight + 1;
        }
        return p;
    }

    private Node rotateRight(Node p){
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        p = fixHeight(p);
        q = fixHeight(q);
        return q;
    }

    private Node rotateLeft(Node q){
        Node p = q.right;
        q.right = p.left;
        p.left = q;
        q = fixHeight(q);
        p = fixHeight(p);
        return p;
    }

    private Node balanceTree(Node p){
        p = fixHeight(p);
        if (getBalanceFactor(p) == 2){
            if (getBalanceFactor(p.right) < 0){
                p.right = rotateRight(p.right);
            }
            return rotateLeft(p);
        }
        if (getBalanceFactor(p) == -2){
            if (getBalanceFactor(p.left) > 0){
                p.left = rotateLeft(p.left);
            }
            return rotateRight(p);
        }
        return p;
    }

    void add(int k){
        if (root == null){
            root = new Node(k);
        }else {
            if (k < root.key) {
                root.left = add(root.left, k);
            } else {
                root.right = add(root.right, k);
            }
        }
        root = balanceTree(root);
        ++size;
    }

    private Node add(Node p, int k){
        if (p == null){
            return new Node(k);
        }
        if (k < p.key){
            p.left = add(p.left, k);
        }else{
            p.right = add(p.right, k);
        }
        return balanceTree(p);
    }

    private Node findMin(Node p){
        if (p.left != null){
            return findMin(p.left);
        }else {
            return p;
        }
    }

    private Node deleteMin(Node p){
        if (p.left == null){
            return p.right;
        }
        p.left = deleteMin(p.left);
        return balanceTree(p);
    }

    void delete(int k){
        if (k < root.key){
            root.left = delete(root.left, k);
        }else{
            if (k > root.key){
                root.right = delete(root.right, k);
            }else {
                Node left = root.left;
                Node right = root.right;
                if (right == null){
                    root = left;
                }
                Node min = findMin(right);
                min.right = deleteMin(right);
                min.left = left;
                root = balanceTree(min);
            }
        }
        root = balanceTree(root);
        --size;
    }

    private Node delete(Node p, int k){
        if (k < p.key){
            p.left = delete(p.left, k);
        }else{
            if (k > p.key){
                p.right = delete(p.right, k);
            }else {
                Node left = p.left;
                Node right = p.right;
                if (right == null){
                    return left;
                }
                Node min = findMin(right);
                min.right = deleteMin(right);
                min.left = left;
                return balanceTree(min);
            }
        }
        return balanceTree(p);
    }
}
