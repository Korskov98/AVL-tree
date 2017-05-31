import java.util.*;

public class Avl<T extends Comparable<T>> implements SortedSet<T>{

    private class Node<T>{
        T key;
        Node<T> left;
        Node<T> right;
        int height;

        Node(T key) {
            this.key = key;
            left = right = null;
            height = 1;
        }
    }

    private Node<T> root;
    private int size = 0;

    public Avl(){
        root = null;
    }

    public SortedSet<T> subSet(T fromElement, T toElement) {
        Iterator<T> iterator = iterator();
        Avl<T> new_tree = new Avl<T>();
        int from_number = getIndexNumber(fromElement);
        int to_number = getIndexNumber(toElement);
        for (int i = 0; i <= from_number; ++i){
            iterator.next();
        }
        for (int i = from_number + 1; i < to_number; ++i){
            new_tree.add(iterator.next());
        }
        return new_tree;
    }

    public SortedSet<T> headSet(T toElement) {
        Iterator<T> iterator = iterator();
        Avl<T> new_tree = new Avl<T>();
        int number = getIndexNumber(toElement);
        for (int i = 0; i < number; ++i){
            new_tree.add(iterator.next());
        }
        return new_tree;
    }

    public SortedSet<T> tailSet(T fromElement) {
        Iterator<T> iterator = iterator();
        Avl<T> new_tree = new Avl<T>();
        int number = getIndexNumber(fromElement);
        for (int i = 0; i <= number; ++i){
            iterator.next();
        }
        for (int i = number + 1; i < size; ++i){
            new_tree.add(iterator.next());
        }
        return new_tree;
    }

    private int getIndexNumber(T key){
        int number = 0;
        Iterator<T> iterator = iterator();
        while ((iterator.hasNext()) && (iterator.next() != key)){
            ++number;
        }
        return number;
    }

    public T first(){
        Node<T> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    public T last() {
        Node<T> node = root;
        while (node.right != null){
            node = node.right;
        }
        return node.key;
    }

    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(Object o) {
        T key = (T) o;
        Node<T> node = root;
        while (node != null){
            if (node.key == key) {
                return true;
            }else if (key.compareTo(node.key) < 0) {
                node = node.left;
            }else if (key.compareTo(node.key) > 0) {
                node = node.right;
            }
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new AvlTreeIterator();
    }

    public Object[] toArray() {
        Object[] r = new Object[size];
        Iterator<T> iterator = iterator();
        for (int i = 0; i < r.length; ++i){
            if (!iterator.hasNext()){
                return Arrays.copyOf(r, i);
            }
            r[i] = iterator.next();
        }
        return r;
    }

    public <T1> T1[] toArray(T1[] a) {
        int size = size();
        T1[] r = a.length >= size ? a : (T1[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        Iterator<T> iterator = iterator();
        for (int i = 0; i < r.length; i++) {
            if (! iterator.hasNext()) {
                if (a != r){
                    return Arrays.copyOf(r, i);
                }
                r[i] = null;
                return r;
            }
            r[i] = (T1)iterator.next();
        }
        return r;
    }

    public boolean containsAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()){
            if (!contains(iterator.next())){
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        boolean flag = false;
        Iterator<? extends T> iterator = c.iterator();
        while (iterator.hasNext()){
            if (add(iterator.next())){
                flag = true;
            }
        }
        return flag;
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()){
            if (remove(iterator.next())){
                flag = true;
            }
        }
        return flag;
    }

    public void clear() {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            remove(iterator.next());
        }
    }

    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (!(o instanceof Set)){
            return false;
        }
        Collection c = (Collection) o;
        if (c.size() != size){
            return false;
        }
        try {
            return containsAll(c);
        }catch (ClassCastException e){
            return false;
        }catch (NullPointerException e){
            return false;
        }
    }

    public int hashCode() {
        int h = 0;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            T node = iterator.next();
            if (node != null){
                h += node.hashCode();
            }
        }
        return h;
    }

    int getHeight(){
        return root.height;
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

    public boolean add(T k){
        if (root == null){
            root = new Node(k);
            ++size;
            return true;
        }else if (k.compareTo(root.key) < 0){
                root.left = add(root.left, k);
                root = balanceTree(root);
                ++size;
                return true;
            } else if(k.compareTo(root.key) > 0){
                root.right = add(root.right, k);
                root = balanceTree(root);
                ++size;
                return true;
            }
        return false;
    }

    public boolean remove(Object o) {
        T key = null;
        try {
            key = (T) o;
        } catch (ClassCastException e) {
            return false;
        }
        if (!(contains(key))){
            return false;
        }else{
            root = delete(root,key);
            --size;
            return true;
        }
    }

    private Node delete(Node<T> p, Object o){
        T key = (T) o;
        if (key.compareTo(p.key) < 0){
            p.left = delete(p.left, o);
        }else if (key.compareTo(p.key) > 0){
            p.right = delete(p.right, o);
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
        return balanceTree(p);
    }

    private Node add(Node p, T k){
        if (p == null){
            return new Node(k);
        }
        if (k.compareTo(root.key) < 0){
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

    private class AvlTreeIterator implements Iterator<T>{

        private final Stack<Node<T>> nodes;

        public AvlTreeIterator(){
            this.nodes = new Stack<Node<T>>();
            pushLeft(root);
        }

        private void pushLeft(Node<T> node) {
            while (node != null) {
                nodes.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        public T next() {
            Node<T> node = nodes.pop();
            if (node != null){
                pushLeft(node.right);
                return node.key;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
