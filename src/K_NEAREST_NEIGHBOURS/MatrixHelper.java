/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K_NEAREST_NEIGHBOURS;

import java.util.stream.IntStream;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author adam
 */
public class MatrixHelper {
    
    static RealMatrix div(RealMatrix matrix1 , RealMatrix matrix2){
        double[][] returnData = new double[matrix1.getData().length][matrix1.getData()[0].length];
        IntStream.range(0, matrix1.getData().length).forEach(r -> 
                IntStream.range(0,matrix1.getData()[0].length).forEach(c -> 
                returnData[r][c] = matrix1.getEntry(r, c)/matrix2.getEntry(r, c)));
        return MatrixUtils.createRealMatrix(returnData);
    }
}
