package K_NEAREST_NEIGHBOURS;

import java.util.ArrayList;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class KNearestNeighbor{
    RealMatrix x,xMin,xMax,xRng,xNrm; //xNrm normalizowane wartosci
// min max values for length size location . 
    ArrayList<String> y;

    public KNearestNeighbor(RealMatrix x, ArrayList<String> y)
    {
     this.x = x;
     this.y = y;
     this.xMin = x.getRowMatrix(0); // initialize first row
     this.xMax = x.getRowMatrix(0); 
     this.xRng = normalize();
    }
    private RealMatrix calcRange() 
    {
        for (int i = 0;i < x.getData().length;i++)
           for (int j = 0;j< x.getData()[0].length;j++){
               if (x.getData()[i][j] < xMin.getData()[0][j]) xMin.setEntry(0, j, x.getData()[i][j]);
               if (x.getData()[i][j] > xMax.getData()[0][j]) xMax.setEntry(0, j, x.getData()[i][j]);
           }
        return xMax.subtract(xMin); // zwraca range
    }
    private RealMatrix normalize()
    {
        RealMatrix xMinRepeat = MatrixUtils.createRealMatrix(x.getData().length, x.getData()[0].length);
        for (int i = 0;i< xMinRepeat.getData().length;i++)
            for (int j = 0;j<xMinRepeat.getData()[0].length;j++)
                xMinRepeat.setEntry(i, j, xMin.getData()[0][j]);
        RealMatrix xRngRepeat = MatrixUtils.createRealMatrix(x.getData().length,x.getData()[0].length);
        for(int i = 0;i<xMinRepeat.getData().length;i++)
            for (int j = 0;j<xMinRepeat.getData()[0][j];j++)
                xRngRepeat.setEntry(i, j, xRng.getData()[0][j]);
        return MatrixHelper.div(x.subtract(xMinRepeat),xRngRepeat);
    }
    public RealMatrix getX() {
        return x;  }
    public RealMatrix getxMin() {
        return xMin;  }
    public RealMatrix getxMax() {
        return xMax;   }
    public RealMatrix getxRng() {
        return xRng;  }
    public RealMatrix getxNrm() {
        return xNrm;  }
    public ArrayList<String> getY() {
        return y;    }
}