public class Main {
    /* change to Sort.BUBBLE for bubble sort */
    /* change to Sort.RADIX for radix sort */
    public static final Sort SORT = Sort.RADIX;
    public static final int LENGTH = 100;

    public static void main(String[] args) throws InterruptedException {
        SortVisualizer.getInstance().init();
    }
}
