package ru.vsu.app;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class General extends Activity {

    Button button, button2;
    TextView tv;
    RadioButton r1, r2, r3, r4;
    int test_length;
    int count=-1;
    String [][] test;
    Integer [][] answer;

    protected void SetTextLength(int i) {
        test_length=i;  // ПРАВИЛЬНЫЙ ВАРИАНТ ВЫДЕЛЯТЬ ЦВЕТОМ!!!
    }

    protected void fill(){
        test = new String[][]{{"Какое количество свежих фруктов и овощей вы съедаете ежедневно?",
                "Сколько часов в неделю вы обычно занимаетесь спортом?"},
                {"Меньше 200 г (равносильно одному яблоку)", "Не занимаюсь спортом"},
                {"200-500 г", "1-2 часа в неделю"},
                {"500-1000 г", "3-7 часов в неделю"},
                {"Более 1000 г", "Более 7 часов в неделю"}};
        answer= new Integer[][]{ {0,0}, {0,0}, {0,0}, {1,1} };
        SetTextLength(2);
    }

    protected void check(){
        r1.setTextColor(Color.BLACK);
        r2.setTextColor(Color.BLACK);
        r3.setTextColor(Color.BLACK);
        r4.setTextColor(Color.BLACK);
        String message="";
        switch (count){
            case 0:
                message="Всемирная организация здравоохранения " +
                        "рекомендует ежедневно съедать не менее 500 граммов фруктов и овощей. " +
                        "Можно утверждать, что если в рационе нет овощей, то нет и нормального " +
                        "пищеварения. Овощи и фрукты, особенно сырые, активизируют секрецию желудка, " +
                        "поджелудочной железы, желчного пузыря.";
                break;
            case 1: message="Спорт - это основа здоровья! Даже не следя за своим питанием, " +
                    "можно оставаться стройным и здоровым при условии регулярных занятий спортом (не менее 7 часов в неделю).";
                break;
        }
        if (r1.isChecked()) r1.setTextColor(Color.RED);
        if (r2.isChecked()) r2.setTextColor(Color.RED);
        if (r3.isChecked()) r3.setTextColor(Color.RED);
        if (r4.isChecked()) r4.setTextColor(Color.RED);

        if (r1.isChecked()&&(answer[0][count]==1)) r1.setTextColor(Color.GREEN);
        if (r2.isChecked()&&(answer[1][count]==1)) r2.setTextColor(Color.GREEN);
        if (r3.isChecked()&&(answer[2][count]==1)) r3.setTextColor(Color.GREEN);
        if (r4.isChecked()&&(answer[3][count]==1)) r4.setTextColor(Color.GREEN);
        AlertDialog.Builder builder = new AlertDialog.Builder(General.this);
        builder.setTitle("Результат")
                .setMessage(message)
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

    protected void setNext(){
        r1.setTextColor(Color.BLACK);
        r2.setTextColor(Color.BLACK);
        r3.setTextColor(Color.BLACK);
        r4.setTextColor(Color.BLACK);
        if (count<test_length-1) {
            ++count;
            tv.setText(test[0][count]);
            r1.setText(test[1][count]);
            r2.setText(test[2][count]);
            r3.setText(test[3][count]);
            r4.setText(test[4][count]);
            button2.setEnabled(false);
        }
        else finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        fill();
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        tv = (TextView) findViewById(R.id.textView);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        r3 = (RadioButton) findViewById(R.id.radioButton3);
        r4 = (RadioButton) findViewById(R.id.radioButton4);
        setNext();
        OnClickListener ocl = new OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    check();
                    button2.setEnabled(true);
                    break;
                case R.id.button2:
                    setNext();
                    break;
            }
        }
        };
        button.setOnClickListener(ocl);
        button2.setOnClickListener(ocl);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_general, container, false);
            return rootView;
        }
    }

}
