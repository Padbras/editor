package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject {

    public Group() {
        //   m_objectList = new  Vector<Group>();
        m_objectList = new Vector<GraphicsObject>();
        m_ID=ID.get_generator().get_id_number();
    }

    public Group(String json) {
        // m_objectList = new  Vector<Group>();
        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+", "");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2));
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
    }

    private void parseObjects(String substring) {
        FunctionToolbox.parseObjects(this, substring);
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    private int count() {
        int i = 0;
        for (GraphicsObject o : m_objectList) {
            if (o.isSimple() == false) {
                i++;
            }
        }
        return i;
    }

    public void add(Object object) {
        if (object instanceof Group) {
            addGroup((Group) object);
        } else {
            addObject((GraphicsObject) object);
        }
    }

    private void addGroup(Group group) {
        m_objectList.add(group);
    }

    private void addObject(GraphicsObject object) {
        m_objectList.add(object);
    }

    public Group copy() {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);

            g.addObject(element.copy());
        }

        return g;
    }

    public int getID() {
        return m_ID;
    }

    @Override
    boolean isClosed(Point pt, double distance) {
        return false;
    }

    public void move(Point delta) {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);

            element.move(delta);
        }

    }



    private void parseGroups(String groupsStr) {
        while (!groupsStr.isEmpty()) {
            int separatorIndex = FunctionToolbox.searchSeparator(groupsStr);
            String groupStr;

            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parseGroup(groupStr));
            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
    }

    public  Vector<GraphicsObject> get_list()
    {
        return  m_objectList;
    }


    public int size() {
        int size = 0;

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            size += element.size();
        }
        return size;
    }

    // refactor: passer les for de tString et tJson comme fonctions
    public String objectsLoop()
    {
        String str = "";
        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if (element.isSimple()) {
                str += element.toJson();
                if (i < m_objectList.size() - this.count() - 1) {
                    str += ", ";
                }
            }

        }
        return str;
    }

    public String JsongroupsLoop() {
        String str = "";
        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if (!element.isSimple()) {
                str += element.toJson();
            }
        }
            return str;
    }

    public String toJson() {
        String str = "{ type: group, objects : { ";

        str+= objectsLoop();

        str += " }, groups : { ";

        str += JsongroupsLoop();

        return str + " } }";
    }

    public String toString() {
        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if (element.isSimple()) {
                str += element.toString();
                if (i < m_objectList.size() - this.count() - 1) {
                    str += ", ";
                }

            }
        }
        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if (!element.isSimple()) {
                str += element.toString();

            }
        }
        return str + "]]";
    }

    //private Vector<Group> m_objectList;
    private Vector<GraphicsObject> m_objectList;
    private int m_ID;
}
