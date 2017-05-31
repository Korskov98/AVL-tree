import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AvlTest {
    @Test
    public void addTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(1);
        assertEquals(3,tree.getHeight());
        assertEquals(4, tree.size());
    }

    @Test
    public void addTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(6);
        tree.add(5);
        tree.add(3);
        tree.add(2);
        tree.add(1);
        assertEquals(3,tree.getHeight());
        assertEquals(6,tree.size());
    }

    @Test
    public void addAllTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(5);
        array.add(4);
        array.add(6);
        array.add(1);
        tree.addAll(array);
        assertEquals(3,tree.getHeight());
        assertEquals(4, tree.size());
    }

    @Test
    public void addAllTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(9);
        array.add(6);
        array.add(5);
        array.add(3);
        array.add(2);
        array.add(1);
        tree.addAll(array);
        assertEquals(3,tree.getHeight());
        assertEquals(6,tree.size());
    }

    @Test
    public void deleteTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(1);
        tree.remove(1);
        assertEquals(2,tree.getHeight());
        assertEquals(3,tree.size());
    }

    @Test
    public void deleteTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(6);
        tree.add(5);
        tree.add(3);
        tree.add(2);
        tree.add(1);
        tree.remove(1);
        tree.remove(6);
        assertEquals(3,tree.getHeight());
        assertEquals(4,tree.size());
    }

    @Test
    public void deleteTest3(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(6);
        tree.add(5);
        tree.add(3);
        tree.add(2);
        tree.add(1);
        assertFalse(tree.remove(0));
        assertFalse(tree.remove(10));
        assertTrue(tree.remove(1));
    }

    @Test
    public void deleteAllTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(5);
        array.add(4);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(1);
        tree.removeAll(array);
        assertTrue(tree.contains(1));
    }

    @Test
    public void deleteAllTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(9);
        array.add(8);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(1);
        tree.removeAll(array);
        assertEquals(4,tree.size());
    }

    @Test
    public void isEmptyTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        assertTrue(tree.isEmpty());
        tree.add(9);
        tree.add(80);
        assertFalse(tree.isEmpty());
        tree.remove(9);
        assertFalse(tree.isEmpty());
    }

    @Test
    public void isEmptyTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        assertTrue(tree.isEmpty());
        tree.add(9);
        tree.add(80);
        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    public void firstTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(80);
        assertEquals(9,(long)tree.first());
        tree.remove(9);
        assertEquals(80,(long)tree.first());
    }

    @Test
    public void firstTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(1);
        tree.add(80);
        assertEquals(1,(long)tree.first());
        tree.remove(80);
        assertEquals(1,(long)tree.first());
    }

    @Test
    public void lastTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(80);
        assertEquals(80,(long)tree.last());
        tree.remove(9);
        assertEquals(80,(long)tree.last());
    }

    @Test
    public void lastTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(90);
        assertEquals(90,(long)tree.last());
        tree.remove(90);
        assertEquals(9,(long)tree.last());
    }

    @Test
    public void containTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(80);
        assertTrue(tree.contains(80));
        tree.remove(9);
        assertFalse(tree.contains(9));
    }

    @Test
    public void containTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(90);
        tree.add(80);
        assertTrue(tree.contains(90));
        assertTrue(tree.contains(80));
    }

    @Test
    public void containsAllTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(9);
        array.add(80);
        tree.add(9);
        tree.add(80);
        assertTrue(tree.containsAll(array));
        tree.remove(9);
        assertFalse(tree.containsAll(array));
    }

    @Test
    public void containsAllTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(9);
        array.add(80);
        tree.add(9);
        tree.add(900);
        assertFalse(tree.containsAll(array));
        tree.remove(9);
        assertFalse(tree.containsAll(array));
    }

    @Test
    public void clearTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(80);
        tree.clear();
        assertFalse(tree.contains(80));
    }

    @Test
    public void clearTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(80);
        tree.clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    public void hashCodeTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(80);
        Avl<Integer> copy = tree;
        assertEquals(tree.hashCode(), copy.hashCode());
    }

    @Test
    public void headSetTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(80);
        tree.add(0);
        Avl<Integer> head = (Avl<Integer>) tree.headSet(80);
        assertTrue(head.contains(0));
        assertFalse(head.contains(80));
    }

    @Test
    public void headSetTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(900);
        tree.add(80);
        tree.add(0);
        Avl<Integer> head = (Avl<Integer>) tree.headSet(0);
        assertTrue(head.isEmpty());
    }

    @Test
    public void tailSetTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(80);
        tree.add(0);
        Avl<Integer> tail = (Avl<Integer>) tree.tailSet(9);
        assertTrue(tail.contains(80));
        assertFalse(tail.contains(0));
    }

    @Test
    public void tailSetTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(9);
        tree.add(80);
        tree.add(0);
        Avl<Integer> tail = (Avl<Integer>) tree.tailSet(80);
        assertTrue(tail.isEmpty());
    }

    @Test
    public void subSetTest1(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(80);
        tree.add(8);
        tree.add(6);
        tree.add(5);
        tree.add(90);
        tree.add(0);
        Avl<Integer> sub = (Avl<Integer>) tree.subSet(5, 80);
        assertFalse(sub.contains(80));
        assertFalse(sub.contains(0));
        assertTrue(sub.contains(8));
    }

    @Test
    public void subSetTest2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(80);
        tree.add(8);
        tree.add(6);
        tree.add(5);
        tree.add(90);
        tree.add(0);
        Avl<Integer> sub = (Avl<Integer>) tree.subSet(80, 90);
        assertTrue(sub.isEmpty());
    }

    @Test
    public void testIterator(){
        Avl<Integer> tree = new Avl<Integer>();
        assertTrue(tree.isEmpty());
        tree.add(5);
        tree.add(9);
        tree.add(3);
        int[] array = new int[tree.size()];
        int[ ] array2 = {3, 5, 9};
        int[ ] array3 = {3, 5};
        int j = 0;
        for(int i: tree) {
            array[j] = i;
            j++;
        }
        assertFalse(tree.isEmpty());
        assertArrayEquals(array, array2);
        tree.remove(9);
        assertFalse(tree.contains(9));
        j = 0;
        array = new int[tree.size()];
        for(int i: tree) {
            array[j] = i;
            j++;
        }
        assertArrayEquals(array, array3);
    }

    @Test
    public  void  testToArray(){
        Avl<Double> tree = new Avl<Double>();
        tree.add(5.55);
        tree.add(9.231);
        tree.add(3.15);
        Double[] array;
        array =  tree.toArray(new Double[tree.size()]);
        assertEquals((double) array[0], 3.15, 0);
        assertEquals((double) array[1], 5.55, 0);
        assertEquals((double) array[2], 9.231, 0);
    }

    @Test
    public  void  testToArray2(){
        Avl<Integer> tree = new Avl<Integer>();
        tree.add(5);
        tree.add(9);
        tree.add(3);
        Integer[] array;
        array =  tree.toArray(new Integer[tree.size()-2]);
        assertEquals((long) array[0], 3);
        assertEquals((long) array[1], 5);
        assertEquals((long) array[2], 9);
    }
}
