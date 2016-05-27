package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Дмитрий on 06.04.2016.
 */
public class Computer implements Serializable{
    private static final long serialVersionUID = 901472643963076823L;

    private String m_name;
    private int m_operationMemory;
    private int m_systemMemory;

    public Computer(String name, int operMemory, int sysMemory)
    {
        super();
        m_name = name;
        m_operationMemory = operMemory;
        m_systemMemory = sysMemory;
    }

    public String GetName() { return m_name; }
    public int GetOperationMemory() { return m_operationMemory; }
    public int GetSystemMemory() { return m_systemMemory; }

    public void SetName(String name) { m_name = name; }
    public void SetOperationMemory(int operMemory){ m_operationMemory = operMemory; }
    public void SetSystemMemory(int sysMemory){ m_systemMemory = sysMemory; }

    @Override
    public String toString() {
        String out = "";
        if (m_name != null)
            out += "Computer Name: " + m_name + "\n";

        if (m_operationMemory > 0)
            out += "Operation Memory: " + m_operationMemory + "\n";

        if (m_systemMemory > 0)
            out += "System Memory : " + m_systemMemory + "\n";

        return out;
    }
}
