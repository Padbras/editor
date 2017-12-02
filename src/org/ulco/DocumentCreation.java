package org.ulco;

import java.util.Vector;

public class DocumentCreation {



    public static void DocumentGrid(Document doc, Point origin, int line, int column, double length) {


        Layer layer = doc.createLayer();

        for (int indexX = 0; indexX < column; ++indexX) {
            for (int indexY = 0; indexY < line; ++indexY) {
                layer.add(new Square(new Point(origin.getX() + indexX * length, origin.getY() + indexY * length), length));
            }
        }
    }

    public static void DocumentCircles(Document doc, Point center, int number, double radius, double delta) {


        Layer layer = doc.createLayer();

        for (int index = 0; index < number; ++index) {
            layer.add(new Circle(center, radius + index * delta));
        }
    }

}
