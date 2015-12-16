package ru.academit.narzikulov;

/**
 * Created by tim on 10.12.2015.
 */
public class GaussResult {
    private Vector gaussSolution;
    private LinearSystemSolveResultCode resultCode = LinearSystemSolveResultCode.ONE_SOLVE;

    public GaussResult(LinearSystemSolveResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public GaussResult(Vector gaussSolution, LinearSystemSolveResultCode resultCode) {
        this.gaussSolution = gaussSolution;
        this.resultCode = resultCode;
    }

    public String toString() {
        if (this.resultCode == LinearSystemSolveResultCode.ONE_SOLVE) {
            return this.gaussSolution.toString();
        }
        return this.resultCode.getMessage();
    }
}
