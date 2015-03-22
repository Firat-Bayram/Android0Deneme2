package com.example.firat.androiddeneme2;

import android.graphics.Rect;

/**
 * Created by Firat on 11.1.2015.
 */
public class GraphicsHelper {
    public final static GraphicsHelper INSTANCE = new GraphicsHelper();

    private GraphicsHelper() {
    }

    public Rect GetirDikdortgen(int sol, int ust, int genislik, int yukseklik) {
        return new Rect(sol, ust, sol + genislik, ust + yukseklik);
    }
}
