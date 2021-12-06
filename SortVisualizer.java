import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortVisualizer {
    private static SortVisualizer instance;
    private boolean init = false;
    private Window win;
    private Renderer renderer;
    private PenguinList<SortRectangle> rectangles;

    private SortVisualizer() {
        win = new Window(500, 500);
        renderer = win.getR();
        rectangles = renderer.getRectangles();
    }

    public static SortVisualizer getInstance() {
        return instance == null ? new SortVisualizer() : instance;
    }

    public void init() throws InterruptedException {
        if (!init) {
            init = true;
        } else {
            System.out.println("SortVisualizer Already Initialized");
            return;
        }

        switch (Main.SORT) {
            case BUBBLE:
                performBubbleSort();
                break;
            case RADIX:
                performRadixSort();
                break;
            case QUICK:
                performQuickSort();
                break;
        }
    }

    private void performBubbleSort() throws InterruptedException {
        int[] arr = generateRandomArray(Main.LENGTH);
        generateRectangles(arr);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                rectangles.get(j).setColor(Color.green);
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    Thread.sleep(5);
                    generateRectangles(arr);
                }
            }
        }
        generateRectangles(arr);
    }

    private void performQuickSort() throws InterruptedException {
        int[] arr = generateRandomArray(Main.LENGTH);
        generateRectangles(arr);
        quickSort(arr, 0, arr.length - 1);
        generateRectangles(arr);
    }

    private void quickSort(int[] arr, int low, int high) throws InterruptedException {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    private int partition(int[] arr, int low, int high) throws InterruptedException {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high-1; j++) {
            rectangles.get(j).setColor(Color.GREEN);
            Thread.sleep(10);
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
            generateRectangles(arr);
        }
        swap(arr, i+1, high);
        return (i+1);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void performRadixSort() throws InterruptedException {
        int[] arr = generateRandomArray(Main.LENGTH);
        generateRectangles(arr);

        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
        generateRectangles(arr);
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private void countSort(int[] arr, int exp) throws InterruptedException {
        int[] result = new int[arr.length];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < arr.length; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i-1];
        }

        for (i = arr.length-1; i >= 0; i--) {
            result[count[arr[i]/exp%10]-1] = arr[i];
            count[(arr[i])/exp%10]--;
        }

        for (i = 0; i < arr.length; i++) {
            rectangles.get(i).setColor(Color.GREEN);
            rectangles.update();
            arr[i] = result[i];
            Thread.sleep(10);
            generateRectangles(arr);
        }
    }

    private int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(win.getHeight() - 50) + 50;
        }
        return arr;
    }

    private void generateRectangles(int[] arr) {
        rectangles.clear();
        for (int i = 0; i < arr.length; i++) {
            /* when (win.getWidth() / numOfRects) is > 1.5, it causes bugs because of integer division */
            // int x = (win.getWidth() / numOfRects) * i;
            int x = i * (int) Math.round((double)win.getWidth() / (double)arr.length);
            int y = win.getHeight() - arr[i];
            int w = (win.getWidth() / arr.length);
            int h = arr[i];
            rectangles.add(new SortRectangle(x, y, w, h, Color.WHITE));
        }
    }
}
