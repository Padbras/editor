package org.ulco;

import java.util.Vector;

public class Layer {
    public Layer() {
        m_list = new Vector<GraphicsObject>();
        m_ID=ID.get_generator().get_id_number();
    }

    public Layer(String json) {
        m_list= new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, endIndex - 1));
    }

    private void parseObjects(String substring) {
        FunctionToolbox.parseObjects(this, substring);
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public void addGroup(Group g) {
        m_list.add(g);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getObjectNumber() {
        return m_list.size();
    }

    public Vector<GraphicsObject> getM_list() {
        return m_list;
    }

    public int getID() {
        return m_ID;
    }





   public String toJson() {
       String str = "{ type: layer, objects : { ";

       for (int i = 0; i < m_list.size(); ++i) {
           GraphicsObject element = m_list.elementAt(i);
           if (element.isSimple()) {
               str += element.toJson();
               if (i < m_list.size()  - 1) {
                   str += ", ";
               }
           }

       }
       str += " }, groups : { ";

       for (int i = 0; i < m_list.size(); ++i) {
           GraphicsObject element2 = m_list.elementAt(i);
           if (!element2.isSimple()) {
               str += element2.toJson();
           }
       }
       return str + " } }";
   }

    Vector<GraphicsObject> m_list;
    private int m_ID;
}
