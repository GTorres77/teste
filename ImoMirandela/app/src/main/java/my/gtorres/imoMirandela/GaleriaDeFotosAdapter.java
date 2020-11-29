package my.gtorres.imoMirandela;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import my.gtorres.imoMirandela.classes.fotosCasas;



public class GaleriaDeFotosAdapter extends BaseAdapter {


    private Context context;

    public GaleriaDeFotosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return fotosCasas.ITEMS.length;
    }

    @Override
    public fotosCasas getItem(int position) {
        return fotosCasas.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(my.gtorres.imoMirandela.R.layout.item_galeria_fotos, viewGroup, false);
        }

        ImageView imagenCoche = (ImageView) view.findViewById(my.gtorres.imoMirandela.R.id.imagen_coche);
        TextView nombreCoche = (TextView) view.findViewById(my.gtorres.imoMirandela.R.id.nombre_coche);

        final fotosCasas item = getItem(position);
        Glide.with(imagenCoche.getContext())
                .load(item.getIdDrawable())
                .into(imagenCoche);

        nombreCoche.setText(item.getNome());

        return view;
    }

}
