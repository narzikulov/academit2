package ru.academit.narzikulov;

/**
 * Created by tim on 21.10.2015.
 */
public class Vector {
    private double[] vectorArray;


    public Vector(int vectorSize) {
        this.vectorArray = new double[vectorSize];
        System.arraycopy(vectorArray, 0, this.vectorArray, 0, vectorSize);
    }

    public Vector(double[] vectorArray) {
        int vectorSize = vectorArray.length;
        this.vectorArray = new double[vectorSize];
        System.arraycopy(vectorArray, 0, this.vectorArray, 0, vectorSize);
    }

    public Vector(int vectorSize, double[] vectorArray) {
        this.vectorArray = new double[vectorSize];
        System.arraycopy(vectorArray, 0, this.vectorArray, 0, vectorSize);
    }

    public Vector(Vector vector) {
        double[] newVectorArray = new double[vector.vectorArray.length];
        System.arraycopy(vector.vectorArray, 0, newVectorArray, 0, vector.vectorArray.length);
        this.vectorArray = newVectorArray;
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
        double[] newVectorArray = new double[Math.max(this.vectorArray.length, vector1.vectorArray.length)];
        return newVectorArray;
    }

    public void addVector(Vector addVector) {
        double[] newVectorArray = this.extVectorsToEqualSize(addVector);

        int thisVectorArraySize = this.vectorArray.length;
        for (int i = 0; i < thisVectorArraySize; ++i) {
            newVectorArray[i] = this.vectorArray[i];
        }

        int addVectorArraySize = addVector.vectorArray.length;
        for (int i = 0; i < addVectorArraySize; ++i) {
            newVectorArray[i] += addVector.vectorArray[i];
        }

        this.vectorArray = newVectorArray;
    }

    public void subVector(Vector subVector) {
        double[] newVectorArray = this.extVectorsToEqualSize(subVector);

        int thisVectorArraySize = this.vectorArray.length;
        for (int i = 0; i < thisVectorArraySize; ++i) {
            newVectorArray[i] = this.vectorArray[i];
        }

        int subVectorArraySize = subVector.vectorArray.length;
        for (int i = 0; i < subVectorArraySize; ++i) {
            newVectorArray[i] -= subVector.vectorArray[i];
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

    public double getVectorElement(int index) {
        return vectorArray[index];
    }

    public void setVectorElement(int index, double num) {
        vectorArray[index] = num;
    }

    public boolean isEqualTo(Vector comparedVector) {
        if (comparedVector == this) {
            return true;
        }

        if (comparedVector == null || comparedVector.getClass() != this.getClass()) {
            return false;
        }

        Vector newComparedVector = (Vector) comparedVector;

        double epsilon = 0.0001;
        int vectorArraySize = newComparedVector.vectorArray.length;
        for (int i = 0; i < vectorArraySize; ++i) {
            if (Math.abs(this.vectorArray[i] - comparedVector.vectorArray[i]) > epsilon) {
                return false;
            }
        }
        return true;
    }

    public static Vector copyVector(Vector vector) {
        int vectorSize = vector.vectorArray.length;
        Vector newVector = new Vector(vectorSize);
        System.arraycopy(vector.vectorArray, 0, newVector.vectorArray, 0, vectorSize);
        return newVector;
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
        //Vector vector1New = new Vector(vector1);
        //Vector vector2New = new Vector(vector2);
        int newVectorSize = Math.min(vector1.vectorArray.length, vector2.vectorArray.length);
        double sum = 0;
        for (int i = 0; i < newVectorSize; ++i) {
            sum += vector1.vectorArray[i] * vector2.vectorArray[i];
        }
        return sum;
    }

}
