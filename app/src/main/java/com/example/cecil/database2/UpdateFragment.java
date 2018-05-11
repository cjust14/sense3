package com.example.cecil.database2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
    private Button BnUpdate, BnReadDate, BnDate;

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
        BnReadDate = view.findViewById(R.id.bn_read_date);
        BnDate = view.findViewById(R.id.bn_date);


        HF12.setVisibility(View.INVISIBLE);
        HF3.setVisibility(View.INVISIBLE);
        HF4.setVisibility(View.INVISIBLE);
        Fedt.setVisibility(View.INVISIBLE);


        BnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new DatePickerFragment();

                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        BnReadDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HF12.setVisibility(View.VISIBLE);
                HF3.setVisibility(View.VISIBLE);
                HF4.setVisibility(View.VISIBLE);
                Fedt.setVisibility(View.VISIBLE);


                String dato = Date.getText().toString();

                Mad mad = new Mad();

                mad = MainActivity.myAppDatabase.myDao().getMadFromDate(dato);

                HF12.setText(mad.getHF12());
                HF3.setText(mad.getHF3());
                HF4.setText(mad.getHF4());
                Fedt.setText(mad.getFedt());

                Toast.makeText(getActivity(),"Loaded!", Toast.LENGTH_SHORT).show();
            }
        });

        BnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Mad mad = new Mad();
                //set the id of the object were saving, to the id from the object we loaded
                mad.setId(MainActivity.myAppDatabase.myDao().getMadFromDate(Date.getText().toString()).getId());
                mad.setHF12(HF12.getText().toString());
                mad.setHF3(HF3.getText().toString());
                mad.setHF4(HF4.getText().toString());
                mad.setFedt(Fedt.getText().toString());
                mad.setDato(Date.getText().toString());

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
