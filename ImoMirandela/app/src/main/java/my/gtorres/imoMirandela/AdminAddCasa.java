package my.gtorres.imoMirandela;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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

public class AdminAddCasa extends AppCompatActivity{
	
	private EditText ref_imo_edt;
	private EditText quartos_edt;
	private EditText ano_edt;
	private EditText preco_edt;
	private String aux = "";
	
	
	Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(my.gtorres.imoMirandela.R.layout.activity_admin_add_casas);
		
		ref_imo_edt = (EditText) this.findViewById(my.gtorres.imoMirandela.R.id.ref_imo_edt);
		quartos_edt = (EditText) this.findViewById(my.gtorres.imoMirandela.R.id.quartos_edt);
		ano_edt = (EditText) this.findViewById(my.gtorres.imoMirandela.R.id.ano_edt);
		preco_edt = (EditText) this.findViewById(my.gtorres.imoMirandela.R.id.preco_edt);
	}
	
	Handler h = new Handler() {
		public void handleMessage(Message msg){
			if(msg.what == 0){
				Toast.makeText(context, "erro!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, "sucesso!", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	public void btConfirmar(View v){
		final Intent i = new Intent(AdminAddCasa.this, MenuAdmin.class);
		final String namespace = "http://imomirandela.app.com/";
		final String url = "http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
		final String method_name = "AdicionarCasa";
		final String soap_action = "http://imomirandela.app.com/AdicionarCasa/";
		
		
		System.out.println("chegou ao adicionar casa ");
		
		new Thread(new Runnable(){
			
			@Override
			public void run(){
				
				SoapObject request = new SoapObject(namespace, method_name);
				
				PropertyInfo ref_imo = new PropertyInfo();
				ref_imo.type = PropertyInfo.STRING_CLASS;
				ref_imo.setName("ref_imo");
				ref_imo.setValue(ref_imo_edt.getText().toString());
				
				PropertyInfo quartos = new PropertyInfo();
				quartos.type = PropertyInfo.STRING_CLASS;
				quartos.setName("quartos");
				quartos.setValue(quartos_edt.getText().toString());
				
				PropertyInfo ano = new PropertyInfo();
				ano.type = PropertyInfo.STRING_CLASS;
				ano.setName("ano");
				ano.setValue(ano_edt.getText().toString());
				
				PropertyInfo preco = new PropertyInfo();
				preco.type = PropertyInfo.STRING_CLASS;
				preco.setName("preco");
				preco.setValue(preco_edt.getText().toString());
				
				request.addProperty(ref_imo);
				request.addProperty(quartos);
				request.addProperty(ano);
				request.addProperty(preco);
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				
				envelope.setOutputSoapObject(request);
				
				HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
				
				try{
					androidHttpTransport.call(soap_action, envelope);
					
					SoapObject response = (SoapObject) envelope.bodyIn;
					
					
					System.out.println(ref_imo.getValue() + " ref_imo");
					System.out.println(quartos.getValue() + " quartos");
					System.out.println(ano.getValue() + " ano");
					System.out.println(preco.getValue() + " preco");
					System.out.println(response.getProperty(0).toString() + " ++++++++++++++++++++++++++++++++++++++");
					
					aux = response.getProperty(0).toString();
					
				}catch(IOException e){
					e.printStackTrace();
					
				}catch(XmlPullParserException e){
					e.printStackTrace();
				}
				
				
				if(aux.equalsIgnoreCase("casa inserido com sucesso")){
					System.out.println("aux 0" + aux);
					h.sendEmptyMessage(1);
					startActivity(i);
				}else{
					h.sendEmptyMessage(0);
					System.out.println("erro registo");
				}
			}
			
		}).start();
		try{
			Thread.sleep(500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}