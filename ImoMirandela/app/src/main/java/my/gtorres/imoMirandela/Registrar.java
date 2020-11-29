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

public class Registrar extends AppCompatActivity{
	
	private String aux;
	private EditText edtNome;
	private EditText edtEmail;
	private EditText edtPass;
	private EditText edtTel;
	
	Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar);
		edtNome = (EditText) this.findViewById(R.id.edtNome);
		edtEmail = (EditText) this.findViewById(R.id.edtEmail);
		edtPass = (EditText) this.findViewById(R.id.edtPass);
		edtTel = (EditText) this.findViewById(R.id.edtTel);
		
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
	
	public void btOk(View v){
		final Intent i = new Intent(Registrar.this, Login.class);
		
		final String namespace = "http://imomirandela.app.com/";
		final String url = "http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
		final String method_name = "RegistrarUtilizador";
		final String soap_action = "http://imomirandela.app.com/RegistrarUtilizador/";
		
		System.out.println("chegou ao registrar ");
		
		new Thread(new Runnable(){
			
			@Override
			public void run(){
				
				SoapObject request = new SoapObject(namespace, method_name);
				
				PropertyInfo nome = new PropertyInfo();
				nome.type = PropertyInfo.STRING_CLASS;
				nome.setName("nome");
				nome.setValue(edtNome.getText().toString());
				
				PropertyInfo pass = new PropertyInfo();
				pass.type = PropertyInfo.STRING_CLASS;
				pass.setName("pass");
				pass.setValue(edtPass.getText().toString());
				
				PropertyInfo email = new PropertyInfo();
				email.type = PropertyInfo.STRING_CLASS;
				email.setName("email");
				email.setValue(edtEmail.getText().toString());
				
				PropertyInfo telefone = new PropertyInfo();
				telefone.type = PropertyInfo.STRING_CLASS;
				telefone.setName("telefone");
				telefone.setValue(edtTel.getText().toString());
				
				request.addProperty(nome);
				request.addProperty(pass);
				request.addProperty(email);
				request.addProperty(telefone);
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				
				envelope.setOutputSoapObject(request);
				HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
				
				try{
					androidHttpTransport.call(soap_action, envelope);
					
					SoapObject response = (SoapObject) envelope.bodyIn;
					
					System.out.println(nome.getValue() + " nome");
					System.out.println(email.getValue() + " email");
					System.out.println(pass.getValue() + " pass");
					System.out.println(telefone.getValue() + " tel");
					System.out.println(response.getProperty(0).toString() + " ++++++++++++++++++++++++++++++++++++++");
					
					aux = response.getProperty(0).toString();
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(aux.equalsIgnoreCase("cliente inserido com sucesso")){
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