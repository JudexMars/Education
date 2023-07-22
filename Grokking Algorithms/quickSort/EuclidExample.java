package quickSort;

import java.util.Scanner;

public class EuclidExample {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter width of the rectangle:");
            int width = in.nextInt();
            System.out.println("Enter height of the rectangle:");
            int height = in.nextInt();

            try {
                int result = maxSquareArea(width, height);
                System.out.println("Max area of a square: " + result);
            } catch (StackOverflowError e) { System.out.println("It's over"); }
        }
    }

    private static int maxSquareArea(int width, int height) {
        if (width % height == 0) return height * height;
        else if (height % width == 0) return width * width;
        else {
            if (width > height) return maxSquareArea(width - height, height);
            if (width < height) return maxSquareArea(width, height - width);
        }
        return 1;
    }
}
