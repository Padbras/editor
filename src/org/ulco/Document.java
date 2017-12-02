package org.ulco;

import java.util.Iterator;
import java.util.Vector;

public class Document {
    public Document() {
        m_layers = new Vector<Layer>();
    }

    public Document(String json) {
        m_layers = new Vector<Layer>();
        String str = json.replaceAll("\\s+", "");
        int layersIndex = str.indexOf("layers");
        int endIndex = str.lastIndexOf("}");

        parseLayers(str.substring(layersIndex + 8, endIndex));
    }




    public Layer createLayer() {
        Layer layer = new Layer();

        m_layers.add(layer);
        return layer;
    }

    public int getLayerNumber() {
        return m_layers.size();
    }

    public int getObjectNumber() {
        int size = 0;

        for (int i = 0; i < m_layers.size(); ++i) {
            size += m_layers.elementAt(i).getObjectNumber();
        }
        return size;
    }

    private void parseLayers(String layersStr) {
        while (!layersStr.isEmpty()) {
            int separatorIndex = FunctionToolbox.searchSeparator(layersStr);
            String layerStr;

            if (separatorIndex == -1) {
                layerStr = layersStr;
            } else {
                layerStr = layersStr.substring(0, separatorIndex);
            }
            m_layers.add(JSON.parseLayer(layerStr));
            if (separatorIndex == -1) {
                layersStr = "";
            } else {
                layersStr = layersStr.substring(separatorIndex + 1);
            }
        }
    }




    public String toJson() {
        String str = "{ type: document, layers: { ";

        for (int i = 0; i < m_layers.size(); ++i) {
            Layer element = m_layers.elementAt(i);

            str += element.toJson();
            if (i < m_layers.size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }

    public Vector<Layer> getM_layers() {
        return m_layers;
    }

    private Vector<Layer> m_layers;
}
