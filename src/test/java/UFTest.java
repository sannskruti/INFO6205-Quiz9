import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UFTest {

    @Test
    public void testInitialization() {
        UF uf = new UF(5);
        assertEquals(5, uf.count(), "Initial count of components should be equal to the number of elements.");
        for (int i = 0; i < 5; i++) {
            assertEquals(i, uf.find(i), "Each element should be its own parent initially.");
        }
    }

    @Test
    public void testUnionAndFind() {
        UF uf = new UF(5);
        uf.union(0, 1);
        assertEquals(4, uf.count(), "After one union, there should be 4 components.");
        assertTrue(uf.connected(0, 1), "Elements 0 and 1 should be connected.");

        uf.union(1, 2);
        assertEquals(3, uf.count(), "After two unions, there should be 3 components.");
        assertTrue(uf.connected(0, 2), "Elements 0 and 2 should be connected.");
        assertEquals(uf.find(0), uf.find(2), "Elements 0 and 2 should have the same root.");
    }

    @Test
    public void testUnionByRank() {
        UF uf = new UF(6);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(0, 2); // This should attach smaller trees correctly
        assertEquals(3, uf.count(), "After three unions, there should be 3 components.");
        assertTrue(uf.connected(0, 3), "Elements 0 and 3 should be connected.");
    }

    @Test
    public void testPathCompression() {
        UF uf = new UF(10);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(3, 4);

        int root = uf.find(4);
        assertEquals(root, uf.find(0), "After path compression, all elements should have the same root.");
    }

    @Test
    public void testValidation() {
        UF uf = new UF(5);
        assertThrows(IllegalArgumentException.class, () -> uf.find(5), "Finding an invalid index should throw an exception.");
        assertThrows(IllegalArgumentException.class, () -> uf.union(1, 5), "Union with an invalid index should throw an exception.");
    }
}
