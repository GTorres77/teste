package my.gtorres.imoMirandela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import my.gtorres.imoMirandela.classes.fotosCasas;

public class actividad_detalhe_foto extends AppCompatActivity {

    public static final String EXTRA_PARAM_ID = "com.herprogramacion.coches2015.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private fotosCasas itemDetallado;
    private ImageView imagenExtendida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.gtorres.imoMirandela.R.layout.actividad_detalhe_foto);




        itemDetallado = fotosCasas.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        imagenExtendida = (ImageView) findViewById(my.gtorres.imoMirandela.R.id.imagen_extendida);

        cargarImagenExtendida();
    }

    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(itemDetallado.getIdDrawable())
                .into(imagenExtendida);
    }




}
