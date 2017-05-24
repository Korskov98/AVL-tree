import org.junit.Test;
import static org.junit.Assert.*;

public class AvlTest {
    @Test
    public void addTest1(){
        Avl tree = new Avl();
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(1);
        assertEquals(3,tree.getHeight());
        assertEquals(4,tree.getSize());
    }

    @Test
    public void addTest2(){
        Avl tree = new Avl();
        tree.add(9);
        tree.add(6);
        tree.add(5);
        tree.add(3);
        tree.add(2);
        tree.add(1);
        assertEquals(3,tree.getHeight());
        assertEquals(6,tree.getSize());
    }

    @Test
    public void deleteTest1(){
        Avl tree = new Avl();
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(1);
        tree.delete(1);
        assertEquals(2,tree.getHeight());
        assertEquals(3,tree.getSize());
    }

    @Test
    public void deleteTest2(){
        Avl tree = new Avl();
        tree.add(9);
        tree.add(6);
        tree.add(5);
        tree.add(3);
        tree.add(2);
        tree.add(1);
        tree.delete(1);
        tree.delete(6);
        assertEquals(3,tree.getHeight());
        assertEquals(4,tree.getSize());
    }
}
