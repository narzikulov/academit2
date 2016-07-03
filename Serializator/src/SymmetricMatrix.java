import java.io.Serializable;
import java.util.Random;

/**
 * Created by User on 03.07.2016.
 */
public class SymmetricMatrix implements Serializable{
    private int dim;
    private int[][] matrix;

    public SymmetricMatrix(int dim) {
        this.dim = dim;
        matrix = new int[dim][dim];
        Random randomNumber = new Random();
        for (int i = 0; i < dim; ++i) {
            for (int j = i; j < dim; ++j) {
                randomNumber.nextInt();
                System.out.printf("%5d");
            }
            System.out.println();
        }
    }
}
