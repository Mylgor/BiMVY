package Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab7.R;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 10.05.2016.
 */
public class FragmentAdapter extends BaseAdapter{

    Context m_context;
    LayoutInflater m_layoutInflater;
    ArrayList<Item> m_items;

    public FragmentAdapter(Context context)
    {
        m_context = context;
        m_layoutInflater = (LayoutInflater)m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Item GetBook(int position)
    {
        return ((Item)getItem(position));
    }

    public void SetList(ArrayList<Item> list)
    {
        m_items = list;
    }

    @Override
    public int getCount()
    {
        return m_items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = m_layoutInflater.inflate(R.layout.activity_item, parent, false);
        }

        Item book = GetBook(position);

        ((TextView) view.findViewById(R.id.txtItem)).setText("Title: " + book.GetTitle());
        return view;
    }

    @Override
    public Object getItem(int position)
    {
        return m_items.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }
}
