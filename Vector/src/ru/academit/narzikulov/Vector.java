package ru.academit.narzikulov;

import java.util.Arrays;

/**
 * Created by tim on 21.10.2015.
 */
public class Vector {
    private double[] vectorArray;


    public Vector(int vectorSize) {
        this.vectorArray = new double[vectorSize];
    }

    public Vector(double[] vectorArray) {
        this(vectorArray.length, vectorArray);
    }

    public Vector(int vectorSize, double[] vectorArray) {
        this.vectorArray = new double[vectorSize];
        System.arraycopy(vectorArray, 0, this.vectorArray, 0, vectorSize);
    }

    public Vector(Vector vector) {
        this(vector.vectorArray);
    }

    public int getSize() {
        return this.vectorArray.length;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("{");
        int vectorArrayLength = vectorArray.length;
        for (int i = 0; i < vectorArrayLength - 1; i++) {
            double currentElement = vectorArray[i];
            s.append(currentElement);
            s.append(", ");
        }
        s.append(vectorArray[vectorArrayLength - 1]);
        s.append("}");
        return s.toString();
    }

    public double[] extVectorsToEqualSize(Vector vector1) {
        return new double[Math.max(this.vectorArray.length, vector1.vectorArray.length)];
    }

    public void addVector(Vector addVector) {
        double[] newVectorArray = this.extVectorsToEqualSize(addVector);

        int newArraySize = newVectorArray.length;
        int thisVectorArraySize = this.vectorArray.length;
        int addVectorArraySize = addVector.vectorArray.length;
        for (int i = 0; i < newArraySize; ++i) {
            if (i < thisVectorArraySize) {
                newVectorArray[i] += this.vectorArray[i];
            }
            if (i < addVectorArraySize) {
                newVectorArray[i] += addVector.vectorArray[i];
            }
        }
        this.vectorArray = newVectorArray;
    }

    public void subVector(Vector subVector) {
        double[] newVectorArray = this.extVectorsToEqualSize(subVector);

        int newArraySize = newVectorArray.length;
        int thisVectorArraySize = this.vectorArray.length;
        int addVectorArraySize = subVector.vectorArray.length;
        for (int i = 0; i < newArraySize; ++i) {
            if (i < thisVectorArraySize) {
                newVectorArray[i] = this.vectorArray[i];
            }
            if (i < addVectorArraySize) {
                newVectorArray[i] -= subVector.vectorArray[i];
            }
        }
        this.vectorArray = newVectorArray;
    }

    public void multVectorToNum(double num) {
        int vectorArraySize = this.vectorArray.length;
        for (int i = 0; i < vectorArraySize; ++i) {
            this.vectorArray[i] *= num;
        }
    }

    public void reverseVector() {
        this.multVectorToNum(-1);
    }

    public double getVectorLength() {
        double vectorLength = 0;
        for (double aVectorArray : this.vectorArray) {
            vectorLength += aVectorArray * aVectorArray;
        }
        return Math.sqrt(vectorLength);
    }

    public double getVectorLengthWithoutLastElement() {
        double vectorLength = 0;
        double[] vectorArray1 = this.vectorArray;
        for (int i = 0; i < vectorArray1.length - 1; i++) {
            double aVectorArray = vectorArray1[i];
            vectorLength += aVectorArray * aVectorArray;
        }
        return Math.sqrt(vectorLength);
    }

    public double getVectorElement(int index) {
        return vectorArray[index];
    }

    public void setVectorElement(int index, double num) {
        vectorArray[index] = num;
    }

    public boolean equals(Object comparedVector) {
        if (this == comparedVector) {
            return true;
        }

        if (comparedVector == null) {
            return false;
        }

        if (comparedVector.getClass() != this.getClass()) {
            return false;
        }

        Vector newComparedVector = (Vector) comparedVector;

        if (this.vectorArray.length != newComparedVector.vectorArray.length) {
            return false;
        }


        double epsilon = 0.0001;
        int vectorArraySize = this.vectorArray.length;
        for (int i = 0; i < vectorArraySize; ++i) {
            if (Math.abs(this.vectorArray[i] - newComparedVector.vectorArray[i]) > epsilon) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        final int prime = 19;
        return prime + Arrays.hashCode(this.vectorArray);
    }

    public static Vector copyVector(Vector vector) {
        return new Vector(vector);
    }

    public static Vector addTwoVectorsToNewOne(Vector vector1, Vector vector2) {
        Vector newVector1 = new Vector(vector1);
        newVector1.addVector(vector2);
        return newVector1;
    }

    public static Vector subVectorsToNewOne(Vector vector1, Vector vector2) {
        Vector newVector1 = new Vector(vector1);
        newVector1.subVector(vector2);
        return newVector1;
    }

    public static double multVectors(Vector vector1, Vector vector2) {
        int newVectorSize = Math.min(vector1.vectorArray.length, vector2.vectorArray.length);
        double sum = 0;
        for (int i = 0; i < newVectorSize; ++i) {
            sum += vector1.vectorArray[i] * vector2.vectorArray[i];
        }
        return sum;
    }
}
