package ru.academit.narzikulov;

/**
 * Created by tim on 21.10.2015.
 */
class Vector {
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

    public void extVectorsToEqualSize(Vector vector1) {
        if (this.vectorArray.length == vector1.vectorArray.length) {
            return;
        }

        if (this.vectorArray.length < vector1.vectorArray.length) {
            int newSize = vector1.vectorArray.length;
            double[] newVectorArray = new double[newSize];
            int minimalArraySize = this.vectorArray.length;
            System.arraycopy(this.vectorArray, 0, newVectorArray, 0, minimalArraySize);
            this.vectorArray = newVectorArray;
        }

        if (this.vectorArray.length > vector1.vectorArray.length) {
            int newSize = this.vectorArray.length;
            double[] newVectorArray = new double[newSize];
            int minimalArraySize = vector1.vectorArray.length;
            System.arraycopy(vector1.vectorArray, 0, newVectorArray, 0, minimalArraySize);
            vector1.vectorArray = newVectorArray;
        }
    }

    public void addVector(Vector addVector) {
        this.extVectorsToEqualSize(addVector);
        int vectorArraySize = this.vectorArray.length;
        for (int i = 0; i < vectorArraySize; ++i) {
            this.vectorArray[i] += addVector.vectorArray[i];
        }
    }

    public void subVector(Vector subVector) {
        this.extVectorsToEqualSize(subVector);
        for (int i = 0; i < this.vectorArray.length; ++i) {
            this.vectorArray[i] -= subVector.vectorArray[i];
        }
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
        Vector vector1New = new Vector(vector1);
        Vector vector2New = new Vector(vector2);
        vector1New.extVectorsToEqualSize(vector2New);
        int newVectorSize = vector1New.vectorArray.length;
        Vector newVector = new Vector(newVectorSize);
        for (int i = 0; i < newVectorSize; ++i) {
            newVector.vectorArray[i] = vector1New.vectorArray[i] + vector2New.vectorArray[i];
        }
        return newVector;
    }

    public static Vector subVectorsToNewOne(Vector vector1, Vector vector2) {
        Vector vector1New = new Vector(vector1);
        Vector vector2New = new Vector(vector2);
        vector1New.extVectorsToEqualSize(vector2New);
        int newVectorSize = vector1New.vectorArray.length;
        Vector newVector = new Vector(newVectorSize);
        for (int i = 0; i < newVectorSize; ++i) {
            newVector.vectorArray[i] = vector1New.vectorArray[i] - vector2New.vectorArray[i];
        }
        return newVector;
    }

    public static double multVectors(Vector vector1, Vector vector2) {
        Vector vector1New = new Vector(vector1);
        Vector vector2New = new Vector(vector2);
        int newVectorSize = Math.min(vector1New.vectorArray.length, vector2New.vectorArray.length);
        double sum = 0;
        for (int i = 0; i < newVectorSize; ++i) {
            sum += vector1New.vectorArray[i] * vector2New.vectorArray[i];
        }
        return sum;
    }

}
