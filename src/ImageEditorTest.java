/*
    Name: Yin Lam lai
    PID:  A15779757
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing for ImageEditor class
 * @author Yin Lam lai
 * @since  4/10/20
 */



public class ImageEditorTest {
    ImageEditor test1;
    ImageEditor test2;
    ImageEditor test3;


    public ImageEditorTest() {
    }

    @Before
    public void setup() throws Exception {
        int[][] img1 = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] img2 = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] img3 = new int[][]{{0, 0}, {0, 0}, {0, 0}};


        this.test1 = new ImageEditor(img1);
        this.test2 = new ImageEditor(img2);
        this.test3 = new ImageEditor(img3);
    }

    private void assert2DArrayEquals(int[][] expecteds, int[][] actuals) {
        if (expecteds != actuals) {
            if (expecteds == null || actuals == null) {
                Assert.fail("null argument");
            }

            if (expecteds.length != actuals.length) {
                Assert.fail("different i-dimension");
            }

            for(int i = 0; i < expecteds.length; ++i) {
                Assert.assertArrayEquals("different row at index " + i, expecteds[i], actuals[i]);
            }
        }

    }

    @Test(
            expected = IllegalArgumentException.class
    )
    public void testConstructorEmptyImageThrowsIAE() {
        new ImageEditor(new int[0][0]);
        new ImageEditor(new int[][]{{0, 0}, {0}, {0, 0}});
    }

    @Test
    public void getImage() {
        int[][] imgtest1 = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] imgtest2 = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] imgtest3 = new int[][]{{0, 0}, {0, 0}, {0, 0}};
        this.assert2DArrayEquals(imgtest1, this.test1.getImage());
        this.assert2DArrayEquals(imgtest2, this.test2.getImage());
        this.assert2DArrayEquals(imgtest3, this.test3.getImage());
    }

    @Test
    public void Assign() {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.test1.assign(i, j, i + j);
            }
        }

        int[][] expected1 = new int[][]{{0, 1, 2}, {1, 2, 3}, {2, 3, 4}};
        this.assert2DArrayEquals(expected1, this.test1.getImage());

        try {
            this.test2.assign(5, 5, 200);
        } catch (IndexOutOfBoundsException var3) {
            System.out.println("Row or column is out of bounds!");
        }

        this.test3.assign(0, 0, 200);
        this.test3.assign(1, 0, 200);
        this.test3.assign(2, 0, 200);
        int[][] expected3 = new int[][]{{200, 0}, {200, 0}, {200, 0}};
        this.assert2DArrayEquals(expected3, this.test3.getImage());
    }

    @Test
    public void scale() {
        try {
            this.test1.scale(5, 5, 2.0D);
        } catch (IndexOutOfBoundsException var3) {
            System.out.println("Row or column is out of bounds!");
        }

        this.test2.assign(0, 0, 200);
        this.test2.scale(0, 0, 2.0D);
        int[][] expected2 = new int[][]{{255, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        this.assert2DArrayEquals(expected2, this.test2.getImage());
        this.test3.assign(0, 0, 2);
        this.test3.scale(0, 0, 2.0D);
        int[][] expected3 = new int[][]{{4, 0}, {0, 0}, {0, 0}};
        this.assert2DArrayEquals(expected3, this.test3.getImage());
    }

    @Test
    public void delete() {
        this.test1.assign(0, 0, 2);
        int[][] ex1 = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        this.test1.delete(0, 0);
        this.assert2DArrayEquals(ex1, this.test1.getImage());
        this.test2.assign(0, 0, 2);
        this.test2.assign(0, 1, 8);
        this.test2.delete(0, 0);
        this.test2.delete(0, 1);
        int[][] ex2 = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        this.assert2DArrayEquals(ex2, this.test2.getImage());

        try {
            this.test3.delete(9, 1);
        } catch (IndexOutOfBoundsException var4) {
            System.out.println("Row or column is out of bounds!");
        }

    }

    @Test
    public void undo() {
        int[][] ex2 = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] var10000 = new int[][]{{0, 0}, {0, 0}, {0, 0}};
        Assert.assertFalse(this.test1.undo());
        this.test2.assign(0, 0, 1);
        this.test2.undo();
        this.assert2DArrayEquals(ex2, this.test2.getImage());
        this.test3.assign(0, 0, 1);
        this.test3.assign(0, 1, 1);
        this.test3.undo();
        this.test3.undo();
        this.assert2DArrayEquals(ex2, this.test2.getImage());
    }

    @Test
    public void redo() {
        int[][] ex2 = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] ex3 = new int[][]{{1, 1}, {0, 0}, {0, 0}};
        Assert.assertFalse(this.test1.undo());
        this.test2.assign(0, 0, 1);
        this.test2.undo();
        this.test2.redo();
        this.assert2DArrayEquals(ex2, this.test2.getImage());
        this.test3.assign(0, 0, 1);
        this.test3.assign(0, 1, 1);
        this.test3.undo();
        this.test3.redo();
        this.test3.redo();
        this.assert2DArrayEquals(ex3, this.test3.getImage());
    }
}