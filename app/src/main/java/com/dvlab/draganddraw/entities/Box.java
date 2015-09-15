package com.dvlab.draganddraw.entities;

import android.graphics.PointF;

public class Box {

    private PointF origin;
    private PointF current;

    public Box(PointF origin) {
        this.origin = current = origin;
    }

    public PointF getCurrent() {
        return current;
    }

    public void setCurrent(PointF current) {
        this.current = current;
    }

    public PointF getOrigin() {
        return origin;
    }

}
