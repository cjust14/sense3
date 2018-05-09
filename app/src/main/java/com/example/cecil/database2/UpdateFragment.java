package com.example.cecil.database2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class UpdateFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private EditText HF12, HF3, HF4, Fedt;
    private TextView Date;
    private Button BnUpdate;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        Date = view.findViewById(R.id.date);
        HF12 = view.findViewById(R.id.txt_HF12);
        HF3 = view.findViewById(R.id.txt_HF3);
        HF4 = view.findViewById(R.id.txt_HF4);
        Fedt = view.findViewById(R.id.txt_fedt);
        BnUpdate = view.findViewById(R.id.bn_update_mad);

        BnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = Date.getText().toString();
                String hf12 = HF12.getText().toString();
                String hf3 = HF3.getText().toString();
                String hf4 = HF4.getText().toString();
                String fedt = Fedt.getText().toString();

                Mad mad = new Mad();
                mad.setHF12(hf12);
                mad.setHF3(hf3);
                mad.setHF4(hf4);
                mad.setFedt(fedt);
                mad.setDate(date);

                MainActivity.myAppDatabase.myDao().updateMad(mad);
                Toast.makeText(getActivity(),"Opdateret!", Toast.LENGTH_SHORT).show();

                Date.setText("");
                HF12.setText("");
                HF3.setText("");
                HF4.setText("");
                Fedt.setText("");
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
