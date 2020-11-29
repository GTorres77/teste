package my.gtorres.imoMirandela;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class AdminVerMarcacoes extends AppCompatActivity {
    private String userID="";
    private TextView marcacoes_tv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.gtorres.imoMirandela.R.layout.activity_admin_ver_marcacoes);

        marcacoes_tv = (TextView) this.findViewById(my.gtorres.imoMirandela.R.id.marcacoes_tv);

        Bundle dados = getIntent().getExtras();
///        this.userID=dados.getString("userID");

        marcacoes_tv.setText(dados.getString("aux1"));
       // this.userID=dados.getString("userID");
    }
    
    
}
