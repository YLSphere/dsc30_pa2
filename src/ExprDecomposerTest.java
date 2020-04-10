import org.junit.Test;

import static org.junit.Assert.*;

public class ExprDecomposerTest {

    @Test
    public void decompose() {
        ExprDecomposer test = new ExprDecomposer();
        char[] ex = new char[] {'1', '2', '-', '3', '/', '4', '5', '+', '*'};
        assertArrayEquals(ex,test.decompose("((1 - 2) / 3) * (4 + 5)"));
    }
}