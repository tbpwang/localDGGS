package edu.cumtb.model;

import edu.cumtb.DGGS;
import gov.nasa.worldwind.geom.Triangle;
import gov.nasa.worldwind.globes.Earth;
import lombok.Data;

/**
 * Administrator
 * Created by tbpwang
 * 2016/9/18.
 */

public abstract
@Data
class QTM extends DGGS {
    // address of the QTM
    private String address;

    // extent of the QTM
    private double minLongitude;
    private double maxLongitude;
    private double minLatitude;
    private double maxLatitude;

    // subdivision levels: 0, 1, 2 ,3 ,4 ......
    private int subdivisionLevel;

    // QTM initialized only once
    private boolean isInit;

    // subdivision sphere
    private static final Earth EARTH = new Earth();

    // QTM consisted of points in latitude[-90, 90] and longitude(-180, 180]
//    private LatLon topPoint;
//    private LatLon leftPoint;
//    private LatLon rightPoint;

    // 外三角形数组表示球面大三角形，其内三角形数组表示计算用分割
    // 即1维三角形数组表示某层剖分的某个三角形，其2维表示该三角形用有限个小三角形组成
//    private Triangle[][] triangles

    //该三角形用有限个小三角形组成
    private Triangle[] triangles;

    // subdivision into 4 children
    private QTM topChild;
    private QTM coreChild;
    private QTM leftChild;
    private QTM rightChild;


    // methods

    public QTM(String address, int subdivisionLevel, double minLongitude, double maxLongitude, double minLatitude, double maxLatitude) {
        this.address = address;
        this.subdivisionLevel = subdivisionLevel;
        this.minLongitude = minLongitude;
        this.maxLongitude = maxLongitude;
        this.minLatitude = minLatitude;
        this.maxLatitude = maxLatitude;
    }

    protected final void dispose() {
        if (topChild != null) {
            topChild.dispose();
            topChild = null;
        }
        if (coreChild != null) {
            coreChild.dispose();
            coreChild = null;
        }
        if (leftChild != null) {
            leftChild.dispose();
            leftChild = null;
        }
        if (rightChild != null) {
            rightChild.dispose();
            rightChild = null;
        }
        isInit = false;
    }

    protected final void init() {
        // create grid mesh
        // set isInit = true
        createInnerTriangle();
        isInit = true;
    }

    protected abstract void createInnerTriangle();


    protected abstract void update();




}
