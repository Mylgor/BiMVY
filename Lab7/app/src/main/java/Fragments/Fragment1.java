package Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lab7.R;

import java.util.ArrayList;

import Data.FragmentAdapter;
import Data.Item;

/**
 * Created by Дмитрий on 10.05.2016.
 */
public class Fragment1 extends Fragment{

    private ListView m_listData;
    private OnItemInterface m_interface;
    private FragmentAdapter m_adapter;
    private ArrayList<Item> m_data;
    private String[] m_titles = {"Book1", "Book2", "Book3", "Book4"};
    private String[] m_descriptions = {"Big description1", "Big description2", "Big description3", "Big description4"};

    public Fragment1() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        m_adapter = new FragmentAdapter(getActivity());
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new GetListView().execute();
    }

    public interface OnItemInterface {
        void ReplaceItemInfo(Item item);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            m_interface = (OnItemInterface)context;
        }
        catch (ClassCastException ex) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        m_listData = (ListView) getActivity().findViewById(R.id.listView);

        m_listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                m_interface.ReplaceItemInfo(m_data.get(position));
            }
        });
    }

    class GetListView extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            m_data = new ArrayList<Item>();
            for (int i = 0; i < 4; i++) {
                Item book = new Item(m_titles[i], m_descriptions[i]);
                m_data.add(book);
            }
            m_adapter.SetList(m_data);
        }

        @Override
        protected Void doInBackground(Void... v) {
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            m_listData.setAdapter(m_adapter);
        }
    }
}
