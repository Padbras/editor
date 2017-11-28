package org.ulco;

abstract public class GraphicsObject {
    public GraphicsObject() {
        m_ID=ID.get_generator().get_id_number();
    }

    abstract public GraphicsObject copy();

    public int getID() {
        return m_ID;
    }

    public int size() {return 1;}

    abstract boolean isClosed(Point pt, double distance);

    abstract void move(Point delta);

    abstract public String toJson();

    abstract public String toString();

    private int m_ID;


    public boolean isSimple() {return true;}
}
