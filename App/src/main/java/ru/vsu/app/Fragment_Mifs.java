package ru.vsu.app;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Алёна on 15.12.13.
 */
public class Fragment_Mifs extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Результат")
                .setMessage("Всемирная организация здравоохранения " +
                        "рекомендует ежедневно съедать не менее 400 граммов фруктов и овощей. " +
                        "Можно утверждать, что если в рационе нет овощей, то нет и нормального " +
                        "пищеварения. Овощи и фрукты, особенно сырые, активизируют секрецию желудка, " +
                        "поджелудочной железы, желчного пузыря.")
                .setIcon(R.drawable.ic_launcher)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_general2, null) ;
    }
}
