package my.gtorres.imoMirandela;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import my.gtorres.imoMirandela.classes.fotosCasas;

public class GaleriaCasas extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private GaleriaDeFotosAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.gtorres.imoMirandela.R.layout.activity_actividad_principal);
        gridView = (GridView) findViewById(my.gtorres.imoMirandela.R.id.grid);
        adaptador = new GaleriaDeFotosAdapter(this);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        fotosCasas item = (fotosCasas) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, actividad_detalhe_foto.class);
        intent.putExtra(actividad_detalhe_foto.EXTRA_PARAM_ID, item.getId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat activityOptions =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this,
                            new Pair<View, String>(view.findViewById(my.gtorres.imoMirandela.R.id.imagen_coche),
                                    actividad_detalhe_foto.VIEW_NAME_HEADER_IMAGE)
                    );
            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        } else
            startActivity(intent);
    }
}