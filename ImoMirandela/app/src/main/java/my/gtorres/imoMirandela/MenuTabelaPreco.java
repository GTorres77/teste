package my.gtorres.imoMirandela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MenuTabelaPreco extends AppCompatActivity {

    private String userID="";
    private TextView preco_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.gtorres.imoMirandela.R.layout.activity_menu_tabela_preco);
        preco_tv = (TextView) this.findViewById(my.gtorres.imoMirandela.R.id.tabela_tv);
        Bundle dados = getIntent().getExtras();
        preco_tv.setText(dados.getString("aux1"));
        this.userID=dados.getString("userID");
    }
}
