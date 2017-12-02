package org.ulco;

import java.util.Vector;

public class FunctionToolbox {
    // 2 fonctions select avant refactoring: une dans layers et une dans document

    // Celle de layers
    public static GraphicsObjects select(Point pt, double distance, Layer layer) {
        GraphicsObjects list = new GraphicsObjects();
        for (GraphicsObject object : layer.getM_list()) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }

    // celle de document
    public static GraphicsObjects select(Point pt, double distance, Document doc) {
        GraphicsObjects list = new GraphicsObjects();

        for (Layer layer : doc.getM_layers()) {
            list.addAll(select(pt, distance, layer));
        }
        return list;
    }
}
