/*
    Name: Yin Lam lai
    PID:  A15779757
 */

/**
 * Creates an image through 2D arrays
 * @author Yin Lam Lai
 * @since  10/4/2020
 */
public class ImageEditor {
    private static final int MAX_PIXEL_VALUE = 255;
    private static final int STACKS_INIT_CAPACITY = 30;
    private static final int VALUES_TO_POP = 3;
    private static final int COLOR_INDEX = 2;

    private int[][] image;
    private IntStack undo;
    private IntStack redo;


    /**
     * Initializes image from given image and two stacks, undo and redo of size 30.
     * @param image 2D array containing rows and columns of pixels
     * @throws IllegalArgumentException if image's width or height is 0 or if each row of image is not equal
     */
    public ImageEditor(int[][] image) {
        if (image.length == 0) {
            throw new IllegalArgumentException();
        } else {
            int[][] imageInput = image;

            int var4;
            int[] n;
            for (var4 = 0; var4 < image.length; var4++) {
                n = imageInput[var4];
                if (n.length == 0) {
                    throw new IllegalArgumentException();
                }
            }

            this.image = imageInput;


            for (var4 = 0; var4 < image.length; ++var4) {
                n = imageInput[var4];

                byte var8 = 0;
                if (var8 < image.length) {
                    int[] x = image[var8];
                    if (n.length != x.length) {
                        throw new IllegalArgumentException();
                    }
                }
            }

            this.image = image;
            this.undo = new IntStack(STACKS_INIT_CAPACITY);
            this.redo = new IntStack(STACKS_INIT_CAPACITY);
        }
    }
    /**
     * returns image as an array
    */
    public int[][] getImage() {
        return this.image;
    }

    /**
     * Multiplies a certain pixel's color value by scale factor
     * @param i height of pixel
     * @param j width of pixel
     * @param scaleFactor Multiplies the color value by this value
     * @throws IndexOutOfBoundsException if i or j exceeds image dimensions
     * @throws IllegalArgumentException if scaleFactor is a negative number
     */
    public void scale(int i, int j, double scaleFactor) {
        if (scaleFactor < 0) {
            throw new IllegalArgumentException();
        } else if (i <= this.image.length && j <= this.image[i].length) {
            this.redo.clear();
            int[] undoPush = new int[]{this.image[i][j], j, i};
            this.undo.multiPush(undoPush);
            this.image[i][j] = (int) ((double) this.image[i][j] * scaleFactor);
            if (this.image[i][j] > MAX_PIXEL_VALUE) {
                this.image[i][j] = MAX_PIXEL_VALUE;
            }

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Assigns a certain pixel's color value
     * @param i height of pixel
     * @param j width of pixel
     * @param color color value
     * @throws IndexOutOfBoundsException if i or j exceeds image dimensions
     * @throws IllegalArgumentException if number is smaller than 0 or larger than 255
     */
    public void assign(int i, int j, int color) {
        if (i <= this.image.length && j <= this.image[i].length) {
            if (color >= 0 && color <= MAX_PIXEL_VALUE) {
                this.redo.clear();
                int[] undoPush = new int[]{this.image[i][j], j, i};
                this.undo.multiPush(undoPush);
                this.image[i][j] = color;
            } else {
                throw new IndexOutOfBoundsException();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    /**
     * Sets a certain pixel's color value to 0
     * @param i height of pixel
     * @param j width of pixel
     * @throws IndexOutOfBoundsException if i or j exceeds image dimensions
     */
    public void delete(int i, int j) {
        if (i <= this.image.length && j <= this.image[i].length) {
            this.redo.clear();
            int[] undoPush = new int[]{this.image[i][j], j, i};
            this.undo.multiPush(undoPush);
            this.image[i][j] = 0;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }


    /**
     * undo last operations, return false if there is nothing to undo, true otherwise
     */
    public boolean undo() {
        if (this.undo.isEmpty()) {
            return false;
        } else {
            int[] popped = this.undo.multiPop(VALUES_TO_POP);
            int redoI = popped[0];
            int redoJ = popped[1];
            int redoColor = this.image[redoI][redoJ];
            int[] redoPushed = new int[]{redoColor, redoJ, redoI};
            this.redo.multiPush(redoPushed);
            this.image[popped[0]][popped[1]] = popped[COLOR_INDEX];
            return true;
        }
    }

    /**
     * redo last undos, return false if there is nothing to redo, true otherwise
     */
    public boolean redo() {
        if (!this.redo.isEmpty()) {
            int[] popped = this.redo.multiPop(VALUES_TO_POP);
            this.image[popped[0]][popped[1]] = popped[COLOR_INDEX];
            return true;
        } else {
            return false;
        }
    }
}
