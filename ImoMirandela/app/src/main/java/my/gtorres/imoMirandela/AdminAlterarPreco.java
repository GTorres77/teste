package my.gtorres.imoMirandela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class AdminAlterarPreco extends AppCompatActivity {

    private String aux="";
    private EditText id_edt;
    private EditText preco_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.gtorres.imoMirandela.R.layout.activity_admin_alterar_preco);


        id_edt =(EditText) this.findViewById(my.gtorres.imoMirandela.R.id.id_edt);
        preco_edt =(EditText) this.findViewById(my.gtorres.imoMirandela.R.id.preco_edt);
    }

    public void btConfirmar (View v)
    {
        final Intent i = new Intent(AdminAlterarPreco.this,MenuAdmin.class);
        final String namespace="http://imomirandela.app.com/";
        final String url="http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
        final String method_name="AlterarPrecoCasa";
        final String soap_action="http://imomirandela.app.com/AlterarPrecoCasa/";

        Thread thread = new Thread(new Runnable(){

            @Override
            public void run(){

                SoapObject request = new SoapObject(namespace, method_name);

                PropertyInfo id = new PropertyInfo();
                id.type = PropertyInfo.INTEGER_CLASS;
                id.setName("idc");
                id.setValue(id_edt.getText().toString());

                PropertyInfo preco = new PropertyInfo();
                preco.type = PropertyInfo.INTEGER_CLASS;
                preco.setName("preco");
                preco.setValue(preco_edt.getText().toString());

                request.addProperty(id);
                request.addProperty(preco);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(request);

                HttpTransportSE androidHttpTransport= new HttpTransportSE(url);

                try
                {
                    androidHttpTransport.call(soap_action, envelope);
                    SoapObject response = (SoapObject) envelope.bodyIn;
                    System.out.println( id.getValue() + " id");
                    System.out.println(response.getProperty(0).toString() + " ++++++++++++++++++++++++++++++++++++++");
                    aux = response.getProperty(0).toString();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        });
        thread.start();

        try{
            Thread.sleep(1500);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        if ( aux.contentEquals("preco atualizado"))
        {
            Toast.makeText(getApplicationContext(), aux , Toast.LENGTH_LONG).show();
            System.out.println("aux 0" +aux );
            startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "erro atualizar preco" , Toast.LENGTH_LONG).show();
        }
    }
}
