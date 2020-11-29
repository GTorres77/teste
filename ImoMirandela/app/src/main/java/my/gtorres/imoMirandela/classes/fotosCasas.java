package my.gtorres.imoMirandela.classes;

import my.gtorres.imoMirandela.R;


public class fotosCasas {
    private String nome;
    private int idDrawable;

    public fotosCasas(String nome, int idDrawable) {
        this.nome = nome;
        this.idDrawable = idDrawable;
    }

    public String getNome() {
        return nome;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nome.hashCode();
    }

    public static fotosCasas[] ITEMS = {
            new fotosCasas("T4_Mirandela", R.drawable.t4_mirandela),
            new fotosCasas("T2_BRAGA", R.drawable.t2_braga),
            new fotosCasas("Armazem", R.drawable.armazem_espinho),
            new fotosCasas("T1_AROUCA", R.drawable.t2_arouca),
    };


    public static fotosCasas getItem(int id) {
        for (fotosCasas item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
