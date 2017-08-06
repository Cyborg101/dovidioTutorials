package io.dovid.delegolettura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/* Author: Umberto D'Ovidio
 * Website: http://dovid.io
 * Email: umberto.dovidio@gmail.com
 * Tutorial page: http://dovid.io/guida-intent-android
 */
public class MainActivity extends AppCompatActivity {

    private Button delegaButton;
    private final String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ut gravida lorem.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        delegaButton = (Button) findViewById(R.id.delega_button);

        delegaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, lorem);
                startActivity(Intent.createChooser(intent, "Scegli un'app per leggere il testo"));
            }
        });
    }
}
