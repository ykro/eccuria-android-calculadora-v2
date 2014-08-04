package com.eccuria.demos.calculadora;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ResultFragment extends Fragment {
    private TextView txtResult;

    public ResultFragment() {
        // Required empty public constructor
    }

    public void setResult(double total) {
        txtResult.setText("Total a pagar " + total);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        txtResult = (TextView) view.findViewById(R.id.txtResult);
        return view;
    }


}
