package Data;

/**
 * Created by Дмитрий on 10.05.2016.
 */
public class Item {

    private String m_title;
    private String m_description;

    public Item(String title, String description)
    {
        super();
        m_title = title;
        m_description = description;
    }

    public String GetTitle(){return m_title;}
    public String GetDescription(){return m_description;}

    public void SetTitle(String title){m_title = title;}
    public void SetDescription(String description){m_description = description;}

    @Override
    public String toString()
    {
        String out = "";
        if (m_title != null)
            out += "Title: " + m_title + "\n";
        if (m_description != null)
            out += "Description: " + m_description + "\n";
        return out;
    }
}
