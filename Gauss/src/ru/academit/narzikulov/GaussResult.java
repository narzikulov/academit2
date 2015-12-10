package ru.academit.narzikulov;

/**
 * Created by tim on 10.12.2015.
 */
public class GaussResult {
    private Vector gaussSolution;
    private GaussLinearSystemSolveId resultId;

    public GaussResult(Vector gaussSolution) {
        this.gaussSolution = gaussSolution;
    }

    public GaussResult(GaussLinearSystemSolveId resultId) {
        this.resultId = resultId;
    }

    public GaussResult(Vector gaussSolution, GaussLinearSystemSolveId resultId) {
        this.gaussSolution = gaussSolution;
        this.resultId = resultId;
    }

    public String toString() {
        switch (this.resultId) {
            case NOSOLVES:
                return "Система не имеет решений";
            case MANYSOLVES:
                return "Система имеет множество решений";
            default:
                return gaussSolution.toString();
        }
    }
}
