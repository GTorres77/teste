package my.gtorres.imoMirandela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuLoginRegist extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.gtorres.imoMirandela.R.layout.activity_menu_login_regist);
    }

    public void btLogin (View v)
    {
        Intent i = new Intent(MenuLoginRegist.this, Login.class);
        startActivity(i);
    }

    public void btRegistrar (View v)
    {
        Intent i = new Intent(MenuLoginRegist.this, Registrar.class);
        startActivity(i);
    }
}
