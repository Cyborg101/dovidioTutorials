package io.dovid.leggo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/* Author: Umberto D'Ovidio
 * Website: http://dovid.io
 * Email: umberto.dovidio@gmail.com
 * Tutorial page: http://dovid.io/guida-intent-android.html
 */
public class MainActivity extends AppCompatActivity {

    private TextView testoLetto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testoLetto = (TextView) findViewById(R.id.text);
        String messaggio = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        if (messaggio != null) {
            testoLetto.setText(messaggio);
        } else {
            testoLetto.setVisibility(View.GONE);
            Toast.makeText(this, "Non è stato fornito alcun testo", Toast.LENGTH_LONG).show();
        }
    }
}
