package my.gtorres.imoMirandela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MenuVerMarcacoes extends AppCompatActivity {
    private String userID="";
    private TextView visitas_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.gtorres.imoMirandela.R.layout.activity_menu_ver_marcacoes);


        visitas_tv = (TextView) this.findViewById(my.gtorres.imoMirandela.R.id.visitas_tv);
        Bundle dados = getIntent().getExtras();
        visitas_tv.setText(dados.getString("aux1"));
        this.userID=dados.getString("userID");
    }
}
