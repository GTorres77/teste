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
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Login extends AppCompatActivity{
	
	
	private EditText username;
	private EditText pass;
	private String aux = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(my.gtorres.imoMirandela.R.layout.activity_login);
		
		username = (EditText) this.findViewById(my.gtorres.imoMirandela.R.id.nome_edt);
		pass = (EditText) this.findViewById(R.id.edtPassword);
		
	}
	
	
	public void btOk(View v){
		
		final String namespace = "http://imomirandela.app.com/";
		final String url = "http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
		final String method_name = "Login";
		final String soap_action = "http://imomirandela.app.com/Login/";
		
		System.out.println("MANO CHEGAMOS AQUI? BTOK?");
		
		new Thread(new Runnable(){
			
			@Override
			public void run(){
				SoapObject request = new SoapObject(namespace, method_name);
				PropertyInfo userName = new PropertyInfo();
				userName.type = PropertyInfo.STRING_CLASS;
				userName.setName("nome");
				userName.setValue(username.getText().toString());
				PropertyInfo userpass = new PropertyInfo();
				userpass.type = PropertyInfo.STRING_CLASS;
				userpass.setName("pass");
				userpass.setValue(pass.getText().toString());
				System.out.println("LOLOOLOL");
				request.addProperty(userName);
				request.addProperty(userpass);
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(request);
				HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
				try{
					androidHttpTransport.call(soap_action, envelope);
					SoapObject response = (SoapObject) envelope.bodyIn;
					System.out.println(username.getText().toString() + " username");
					System.out.println(pass.getText().toString() + " pass");
					System.out.println(response.getProperty(0).toString() + " ++++++++++++++++++++++++++++++++++++++");
					aux = response.getProperty(0).toString();
				}catch(IOException e){
					e.printStackTrace();
				}catch(XmlPullParserException e){
					e.printStackTrace();
				}
				
				if(username.getText().toString().equalsIgnoreCase("admin") || pass.getText().toString().equalsIgnoreCase("admin")){
					
					System.out.println("HERE: ");
					//Toast.makeText(getApplicationContext(), "ola admin", Toast.LENGTH_LONG).show();
					Intent i = new Intent(Login.this, MenuAdmin.class);
					startActivity(i);
				}else if(aux.contentEquals("-1")) //se retornar -1 erro
				{
					//Toast.makeText(getApplicationContext(), "nome do utilizador ou pass invalido ", Toast.LENGTH_LONG).show();
				}else{
					Intent i = new Intent(Login.this, MenuImoMirandela.class);
					i.putExtra("userID", aux);
					//Toast.makeText(getApplicationContext(), "bem vindo utilizador " + aux, Toast.LENGTH_LONG).show();
					startActivity(i);
				}
			}
		}).start();
		
		try{
			Thread.sleep(500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void btCancel(View v){
		Intent i = new Intent(Login.this, MenuLoginRegist.class);
		startActivity(i);
	}
}