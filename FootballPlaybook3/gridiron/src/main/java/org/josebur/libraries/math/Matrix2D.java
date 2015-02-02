package org.josebur.libraries.math;

import org.ejml.alg.dense.mult.MatrixVectorMult;
import org.ejml.data.D1Matrix64F;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.ops.MatrixFeatures;
import org.ejml.simple.SimpleMatrix;

public class Matrix2D {

    static int kDim = 3;
    DenseMatrix64F _matrix;

    public Matrix2D() {
        _matrix = CommonOps.identity(kDim);
    }

    private Matrix2D(DenseMatrix64F matrix) {
        _matrix = matrix;
    }

    public boolean isIdentity() {
        return MatrixFeatures.isIdentity(_matrix, 0.1);
    }

    public Matrix2D scaleX(float x) {
        _matrix.set(0, 0, x);
        return this;
    }

    public Matrix2D scaleY(float y) {
        _matrix.set(1, 1, y);
        return this;
    }

    public Matrix2D translateX(float x) {
        _matrix.set(0, 2, x);
        return this;
    }

    public Matrix2D translateY(float y) {
        _matrix.set(1, 2, y);
        return this;
    }

    public float scaleX() {
        return (float)_matrix.get(0, 0);
    }

    public float scaleY() {
        return (float)_matrix.get(1, 1);
    }

    public float multiplyPointX(float x) {
        DenseMatrix64F point = new DenseMatrix64F(kDim, 1);
        point.set(0, 0, x);
        point.set(2, 0, 1);

        DenseMatrix64F result = new DenseMatrix64F(kDim, 1);
        MatrixVectorMult.mult(_matrix, point, result);

        return (float)result.get(0, 0);
    }


    public float multiplyPointY(float y) {
        DenseMatrix64F point = new DenseMatrix64F(kDim, 1);
        point.set(1, 0, y);
        point.set(2, 0, 1);

        DenseMatrix64F result = new DenseMatrix64F(kDim, 1);
        MatrixVectorMult.mult(_matrix, point, result);

        return (float)result.get(1, 0);
    }

    public Matrix2D invert() {
        DenseMatrix64F inverted = new DenseMatrix64F(kDim, kDim);
        if (!CommonOps.invert(_matrix, inverted)) return null;
        return new Matrix2D(inverted);
    }

}
