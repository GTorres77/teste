package my.gtorres.imoMirandela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class AdminRemoverCasas extends AppCompatActivity{
	
	private EditText ref_imo_edt;
	private String aux = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(my.gtorres.imoMirandela.R.layout.activity_admin_remover_casas);
		ref_imo_edt = (EditText) this.findViewById(my.gtorres.imoMirandela.R.id.id_edt);
	}
	
	public void btconfirmar(View v){
		final Intent i = new Intent(AdminRemoverCasas.this, MenuAdmin.class);
		final String namespace = "http://imomirandela.app.com/";
		final String url = "http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
		final String method_name = "RemoverCasa";
		final String soap_action = "http://imomirandela.app.com/RemoverCasa/";
		
		new Thread(new Runnable(){
			
			@Override
			public void run(){
				
				SoapObject request = new SoapObject(namespace, method_name);
				PropertyInfo ref_imo = new PropertyInfo();
				ref_imo.type = PropertyInfo.STRING_CLASS;
				ref_imo.setName("id");
				ref_imo.setValue(ref_imo_edt.getText().toString());
				request.addProperty(ref_imo);
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(request);
				HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
				
				try{
					androidHttpTransport.call(soap_action, envelope);
					SoapObject response = (SoapObject) envelope.bodyIn;
					System.out.println(ref_imo.getValue() + " ref_imo");
					System.out.println(response.getProperty(0).toString() + " ++++++++++++++++++++++++++++++++++++++");
					aux = response.getProperty(0).toString();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}).start();
		
		try{
			Thread.sleep(1500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		if(aux.contentEquals("casa eliminada com sucesso")){
			Toast.makeText(getApplicationContext(), aux, Toast.LENGTH_LONG).show();
			System.out.println("aux 0" + aux);
			startActivity(i);
		}else{
			Toast.makeText(getApplicationContext(), "erro ao eliminar a casa", Toast.LENGTH_LONG).show();
		}
	}
}