package me.dan.kvadratickrovnice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextA)
    EditText aInput;

    @BindView(R.id.editTextB)
    EditText bInput;

    @BindView(R.id.editTextC)
    EditText cInput;

    @BindView(R.id.textViewResult)
    TextView resultView;

    @BindView(R.id.vypisRovniceTextView)
    TextView rovniceView;

    private double a;
    private double b;
    private double c;
    private double discriminant;
    private double result1;
    private double result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onClick(View v) {
        try {
            a = Integer.parseInt(aInput.getText().toString());
            b = Integer.parseInt(bInput.getText().toString());
            c = Integer.parseInt(cInput.getText().toString());
        } catch (NullPointerException ex) {
            Toast.makeText(getApplicationContext(), "Zadejte číslo", Toast.LENGTH_SHORT).show();
            return;
        } catch (NumberFormatException ex) {
            Toast.makeText(getApplicationContext(), "Zadejte číslo", Toast.LENGTH_SHORT).show();
            return;
        }
        discriminant = (Math.pow(b, 2)) - (4 * a * c);
        result1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        result2 = (-b - Math.sqrt(discriminant)) / (2 * a);


        if (a == 0 && b == 0 && c == 0) {
            rovniceView.setText("0 = 0");
            resultView.setText("Rovnice má nekonečně mnoho řešení");
        } else if (a == 0 && b == 0) {
            rovniceView.setText(c + " = 0");
            resultView.setText("Rovnice nemá řešení");
        } else if (a == 0 && c == 0) {
            rovniceView.setText(b + "x = 0");
            resultView.setText("Rovnice má jedno řešení: 0");
        } else if (b == 0 && c == 0) {
            rovniceView.setText(a + "x² = 0");
            resultView.setText("Rovnice má jedno řešení: 0");
        } else if (a == 0) {
            rovniceView.setText(b + "x + " + c + " = 0");
            result1 = -c / b;
            resultView.setText("Rovnice má jedno řešení: " + result1);
        } else if (b == 0) {
            rovniceView.setText(a + "x² + " + c + " = 0");
            result1 = Math.sqrt(-c / a);
            result2 = -Math.sqrt(-c / a);
            resultView.setText("Rovnice má dvá kořeny:\n\nPrvní kořen je: " + result1 + "\nDruhý kořen je: " + result2);
        } else if (c == 0) {
            rovniceView.setText(a + "x² + " + b + "x = 0");
            resultView.setText("Rovnice má dvá kořeny:\n\nPrvní kořen je: " + result1 + "\nDruhý kořen je: " + result2);
        } else {
            rovniceView.setText(a + "x² + " + b + "x + " + c + " = 0");
            resultView.setText("Rovnice má dvá kořeny:\n\nPrvní kořen je: " + result1 + "\nDruhý kořen je: " + result2);
        }
        if (discriminant < 0)
        {
            resultView.setText("Rovnice nemá řešení v oboru reálných čísel");
        }
        if(discriminant == 0 && a != 0 && b != 0 && c != 0)
        {
            result1 = -b / (2*a);
            resultView.setText("Rovnice má jedno řešení: " + result1);
        }

        aInput.setText("");
        bInput.setText("");
        cInput.setText("");
    }
}
