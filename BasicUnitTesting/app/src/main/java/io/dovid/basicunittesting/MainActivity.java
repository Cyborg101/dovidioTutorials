package io.dovid.basicunittesting;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private Button computeFactorial;
    private TextView resultNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = (EditText) findViewById(R.id.input_et);
        computeFactorial = (Button) findViewById(R.id.compute_factorial_btn);
        resultNumber = (TextView) findViewById(R.id.result_tv);

        computeFactorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputNumber.getText().toString();
                if (input == null) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle(R.string.number_field_empty)
                            .setMessage(R.string.must_enter_number)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }
                Integer number = null;
                try {
                    number = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle(R.string.not_valid_number)
                            .setMessage(R.string.provide_valid_number)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }

                if (number != null) {
                    if (number > 20) {
                        new AlertDialog.Builder(view.getContext())
                                .setTitle(R.string.number_too_large)
                                .setMessage(R.string.enter_number_less_20)
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .show();
                    } else if (number < 0) {
                        new AlertDialog.Builder(view.getContext())
                                .setTitle("Number is negative")
                                .setMessage("Factorial is defined only for non negative numbers")
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .show();
                    }
                    else {
                        long result = Arithmetic.factorial(number);
                        resultNumber.setText(getString(R.string.factorial_of) + " " +
                                        input + " " +  getString(R.string.is) + " " + result);
                    }
                }
            }
        });
    }
}
