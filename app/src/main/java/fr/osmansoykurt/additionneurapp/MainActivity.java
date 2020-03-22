package fr.osmansoykurt.additionneurapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static final String BASE_8 = "Octal";
    public static final String BASE_10 = "Decimal";
    public static final String BASE_16 = "Hexa";

    private TextView result;
    private String mode;


    private int var1;
    private int var2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.result = (TextView) findViewById(R.id.resultat);

        final Spinner spinnerMode = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinnerItems,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(adapter);
        spinnerMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mode = spinnerMode.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), mode, Toast.LENGTH_SHORT).show();
                var2 = 0;
                var1 = 0;
                result.setText(""+var1);

                switch (mode) {
                    case BASE_8:
                        findViewById(R.id.btn8).setClickable(false);
                        findViewById(R.id.btn9).setClickable(false);
                        findViewById(R.id.btnA).setClickable(false);
                        findViewById(R.id.btnB).setClickable(false);
                        findViewById(R.id.btnC).setClickable(false);
                        findViewById(R.id.btnD).setClickable(false);
                        findViewById(R.id.btnE).setClickable(false);
                        findViewById(R.id.btnF).setClickable(false);
                        break;
                    case BASE_10:
                        findViewById(R.id.btn8).setClickable(true);
                        findViewById(R.id.btn9).setClickable(true);
                        findViewById(R.id.btnA).setClickable(false);
                        findViewById(R.id.btnB).setClickable(false);
                        findViewById(R.id.btnC).setClickable(false);
                        findViewById(R.id.btnD).setClickable(false);
                        findViewById(R.id.btnE).setClickable(false);
                        findViewById(R.id.btnF).setClickable(false);
                        break;
                    case BASE_16:
                        findViewById(R.id.btn8).setClickable(true);
                        findViewById(R.id.btn9).setClickable(true);
                        findViewById(R.id.btnA).setClickable(true);
                        findViewById(R.id.btnB).setClickable(true);
                        findViewById(R.id.btnC).setClickable(true);
                        findViewById(R.id.btnD).setClickable(true);
                        findViewById(R.id.btnE).setClickable(true);
                        findViewById(R.id.btnF).setClickable(true);
                        break;
                    default:
                        Toast.makeText(getBaseContext(), mode, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addNbr(View view) {
        try {
            switch (mode) {
                case BASE_8 :
                    if (var1 == 0) {
                        int val = Integer.parseInt(((Button) view).getText().toString());
                        var1 = val;
                        result.setText(""+var1);
                    }
                    else {
                        int val = Integer.parseInt(((Button) view).getText().toString());
                        var2 = var1 + val;
                        if (var2/8 >= 1){
                            var2 = 10 +var2%8;
                        }
                        result.setText(""+var2);
                        var2 = 0;
                        var1 = 0;
                    }
                    break;
                case BASE_10 :
                    if (var1 == 0) {
                        int val = Integer.parseInt(((Button) view).getText().toString());
                        var1 = val;
                        result.setText(""+var1);
                    }
                    else {
                        int val = Integer.parseInt(((Button) view).getText().toString());
                        var2 = var1 + val;
                        result.setText(""+var2);
                        var2 = 0;
                        var1 = 0;
                    }
                    break;
                case BASE_16 :
                    if (var1 == 0) {
                        char val = ((Button) view).getText().charAt(0);
                        var1 = Integer.parseInt(String.valueOf(val),16);
                        result.setText((""+val).toUpperCase());
                    }
                    else {
                        char val = ((Button) view).getText().charAt(0);
                        var2 = Integer.parseInt(String.valueOf(val),16);
                        var2 = var1 + var2;
                        result.setText((""+Integer.toHexString(var2)).toUpperCase());
                        var2 = 0;
                        var1 = 0;
                    }
                    break;
                default:
                    Toast.makeText(this, " MARCHE PAS !!!",Toast.LENGTH_LONG);
                    break;
            }
        } catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }
}
