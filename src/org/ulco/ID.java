package org.ulco;

public class ID {
    private int id;

    private static ID generator;

    private ID()
    {
        id = 0;
    }

    public static ID get_generator()
    {

        if(generator == null)
        {
            generator = new ID();
            return generator;
        }
        else
        {
            generator.id++;
            return generator;
        }
    }

    public int get_id_number()
    {
        return id;
    }

    public void set_id_number(int newID)
    {
        this.id = newID;
    }
}