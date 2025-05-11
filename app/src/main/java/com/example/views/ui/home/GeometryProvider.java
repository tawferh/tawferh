package com.example.views.ui.home;

import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.geometry.LinearRing;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polygon;
import com.yandex.mapkit.geometry.Polyline;
import com.yandex.mapkit.map.CameraPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeometryProvider {

    public static final CameraPosition startPosition = new CameraPosition(
            new Point(53.19, 50.118),
            13.5f,
            310.0f,
            0.0f);

    public static List<Point> points = Arrays.asList(
            new Point ( 53.230628, 50.199031 ),
            new Point ( 53.193613, 50.203055 ),
            new Point ( 53.216578, 50.179640 ),
            new Point ( 53.230594, 50.164527 ),
            new Point ( 53.197680, 50.094005 ),
            new Point ( 53.212447, 50.153643 ),
            new Point ( 53.211056, 50.159051 ),
            new Point ( 53.216098, 50.159114 ),
            new Point ( 53.247228, 50.173453 ),
            new Point ( 53.212428, 50.177079 ),
            new Point ( 53.191249, 50.108682 ),
            new Point ( 53.202182, 50.119834 ),
            new Point ( 53.194242, 50.096508 ),
            new Point ( 53.215930, 50.148801 ),
            new Point ( 53.196730, 50.098088 ),
            new Point ( 53.192788, 50.110537 ),
            new Point ( 53.184465, 50.105701 ),
            new Point ( 53.186913, 50.097748 ),
            new Point ( 53.196957, 50.112198 ),
            new Point ( 53.198818, 50.110775 ),
            new Point ( 53.189679, 50.089881 ),
            new Point ( 53.188573, 50.098639 ),
            new Point ( 53.205481, 50.125994 ),
            new Point ( 53.203256, 50.142184 ),
            new Point ( 53.198610, 50.115319 ),
            new Point ( 53.192136, 50.102605 ),
            new Point ( 53.197639, 50.097324 ),
            new Point ( 53.188821, 50.102783 ),
            new Point ( 53.191572, 50.094899 )
    );

    public static List<Point> pointsBrown = Arrays.asList(
            new Point ( 53.230628, 50.199031 ),
            new Point ( 53.193613, 50.203055 ),
            new Point ( 53.216578, 50.179640 ),
            new Point ( 53.230594, 50.164527 ),
            new Point ( 53.197680, 50.094005 ),
            new Point ( 53.212447, 50.153643 )
    );

    public static List<Point> pointsGreen = Arrays.asList(
            new Point ( 53.211056, 50.159051 ),
            new Point ( 53.216098, 50.159114 ),
            new Point ( 53.247228, 50.173453 ),
            new Point ( 53.212428, 50.177079 ),
            new Point ( 53.191249, 50.108682 ),
            new Point ( 53.202182, 50.119834 ),
            new Point ( 53.194242, 50.096508 )
    );

    public static List<Point> pointsOrange = Arrays.asList(
            new Point ( 53.215930, 50.148801 ),
            new Point ( 53.196730, 50.098088 ),
            new Point ( 53.192788, 50.110537 ),
            new Point ( 53.184465, 50.105701 )
    );

    public static List<Point> pointsPink = Arrays.asList(
            new Point ( 53.186913, 50.097748 ),
            new Point ( 53.196957, 50.112198 ),
            new Point ( 53.198818, 50.110775 ),
            new Point ( 53.189679, 50.089881 ),
            new Point ( 53.188573, 50.098639 ),
            new Point ( 53.205481, 50.125994 )
    );

    public static List<Point> pointsPurple = Arrays.asList(
            new Point ( 53.203256, 50.142184 ),
            new Point ( 53.198610, 50.115319 ),
            new Point ( 53.192136, 50.102605 ),
            new Point ( 53.197639, 50.097324 ),
            new Point ( 53.188821, 50.102783 ),
            new Point ( 53.191572, 50.094899 )
    );
}
