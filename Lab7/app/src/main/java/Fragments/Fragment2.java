package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lab7.R;

import Data.Item;

/**
 * Created by Дмитрий on 10.05.2016.
 */
public class Fragment2 extends Fragment implements Fragment1.OnItemInterface {

    private TextView m_title, m_description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, null);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        m_title = (TextView) getActivity().findViewById(R.id.txtTitle);
        m_description = (TextView) getActivity().findViewById(R.id.txtDescrip);
    }

    @Override
    public void ReplaceItemInfo(Item item) {
        m_title.setText("Название: " + item.GetTitle());
        m_description.setText("Описание: " + item.GetDescription());
    }
}
