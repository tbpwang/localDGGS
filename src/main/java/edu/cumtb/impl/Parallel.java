package edu.cumtb.impl;

import edu.cumtb.DGGS;
import edu.cumtb.model.QTM;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Earth;
import gov.nasa.worldwind.render.DrawContext;

/**
 * Administrator
 * Created by tbpwang
 * 2016/9/19.
 */
// method: circle of latitude
// 纬圈法
public class Parallel extends QTM {
    public Parallel(String address, int subdivisionLevel, double minLongitude, double maxLongitude, double minLatitude, double maxLatitude) {
        super(address, subdivisionLevel, minLongitude, maxLongitude, minLatitude, maxLatitude);
    }

    private LatLon[] topPoints, leftPoints, rightPoints;

    protected void createInnerTriangle() {
        topPoints = new Position[21];
        leftPoints = new Position[21];
        rightPoints = new Position[21];
        double latitudeSpan = (getMaxLatitude() - getMinLatitude()) / 20;
        double longitudeSpan = (getMaxLongitude() - getMinLongitude()) / 20;
        for (int i = 0; i < 21; i++) {
            leftPoints[i] = Position.fromDegrees(getMinLatitude() + i * latitudeSpan, getMinLongitude(), 10);// set elevation = 10m
            rightPoints[i] = Position.fromDegrees(getMinLatitude() + i * latitudeSpan, getMaxLongitude(),10);// set elevation = 10m
            if (getMaxLatitude() > 0) {
                topPoints[i] = Position.fromDegrees(getMinLatitude(), getMinLongitude() + i * longitudeSpan, 10);
            } else {
                topPoints[i] = Position.fromDegrees(getMaxLatitude(), getMinLongitude() + i * longitudeSpan, 10);
            }
        }
    }

    protected void update() {

    }

    public void render(DrawContext drawContext) {

    }
}
