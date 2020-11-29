package my.gtorres.imoMirandela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuAdmin extends AppCompatActivity {

        private String userID="";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(my.gtorres.imoMirandela.R.layout.activity_menu_admin);
        }
        public void VerMTestDrive(View v)
        {
            Intent i = new Intent (MenuAdmin.this,AdminMenuTDporData.class);
            startActivity(i);
        }

        public void AddCasas(View v)
        {
            Intent i = new Intent (MenuAdmin.this,AdminAddCasa.class);
            startActivity(i);
        }

        public void RemoverCasas(View v)
        {
            Intent i = new Intent (MenuAdmin.this,AdminRemoverCasas.class);
            startActivity(i);
        }

        public void AlterPrecos(View v)
        {
            Intent i = new Intent (MenuAdmin.this,AdminAlterarPreco.class);
            startActivity(i);
        }
}
