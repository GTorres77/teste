package my.gtorres.imoMirandela;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuImoMirandela extends AppCompatActivity{
	private String userID;
	
	private static final String ns = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(my.gtorres.imoMirandela.R.layout.activity_menu_imo_mirandela);
		Bundle dados = getIntent().getExtras();
		this.userID = dados.getString("userID");
		System.out.println("USER IS+++++++++" + userID);
	}
	
	public void VerGaleria(View v){
		Intent i = new Intent(MenuImoMirandela.this, GaleriaCasas.class);
		i.putExtra("userID", userID);
		startActivity(i);
	}
	
	public void MarcacaoVisita(View v){
		Intent i = new Intent(MenuImoMirandela.this, MarcacaoVisita.class);
		i.putExtra("userID", userID);
		startActivity(i);
	}
	
	public void VerTabelaPrecos(View v){
		final Intent i = new Intent(MenuImoMirandela.this, MenuTabelaPreco.class);
		
		final String namespace = "http://imomirandela.app.com/";
		final String url = "http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
		final String method_name = "VerTabelaPreco";
		final String soap_action = "http://imomirandela.app.com/VerTabelaPreco/";
		
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					SoapObject request = new SoapObject(namespace, method_name);
					SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.setOutputSoapObject(request);
					HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
					
					androidHttpTransport.call(soap_action, envelope);
					SoapObject response = (SoapObject) envelope.bodyIn;
					
					int numEl = response.getPropertyCount();
					String aux = "";
					for(int c = 0; c < numEl; c++){
						SoapObject resultado = (SoapObject) response.getProperty(c);
						aux += " ano : " + resultado.getProperty(0).toString() + "\n";
						aux += " ref_imo : " + resultado.getProperty(1).toString() + "\n";
						aux += " quartos : " + resultado.getProperty(3).toString() + "\n";
						aux += " preço : " + resultado.getProperty(2).toString() + "€\n\n";
					}
					i.putExtra("aux1", aux.toString());
					i.putExtra("userID", userID);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		thread.start();
		try{
			Thread.sleep(500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		startActivity(i);
	}
	
	private boolean conexao(){
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		return cm.getActiveNetworkInfo() != null;
	}
	
	private void verOnline(){
		final Intent i = new Intent(MenuImoMirandela.this, MenuVerMarcacoes.class);
		final String namespace = "http://imomirandela.app.com/";
		final String url = "http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
		final String method_name = "VerMarcacoes";
		final String soap_action = "http://imomirandela.app.com/VerMarcacoes/";
		
		new Thread(new Runnable(){
			
			@Override
			public void run(){
				SoapObject request = new SoapObject(namespace, method_name);
				
				PropertyInfo userid = new PropertyInfo();
				userid.type = PropertyInfo.STRING_CLASS;
				userid.setName("uid");
				userid.setValue(userID);
				request.addProperty(userid);
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(request);
				HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
				
				try{
					androidHttpTransport.call(soap_action, envelope);
					SoapObject response = (SoapObject) envelope.bodyIn;
					
					int numEl = response.getPropertyCount();
					String aux = "";
					
					for(int c = 0; c < numEl; c++){
						SoapObject resultado = (SoapObject) response.getProperty(c);
						aux += " data : " + resultado.getProperty(1).toString() + "\n";
						aux += " hora  : " + resultado.getProperty(2).toString() + "\n";
						aux += " ref_imo : " + resultado.getProperty(3).toString() + "\n " + "\n " + "\n ";
					}
					
					i.putExtra("aux1", aux.toString());
					i.putExtra("userID", userID);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				startActivity(i);
			}
			
		}).start();
		
		try{
			Thread.sleep(500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	private void verXml(){
		
		final Intent i = new Intent(MenuImoMirandela.this, MenuVerMarcacoes.class);
		
		int eventType = 0;
		XmlPullParser parser = null;
		File file = null;
		FileInputStream fis = null;
		XmlPullParserFactory factory;
		
		String marcacoes = "";
		
		try{
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();
			file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "/marcacao.xml");
			
			try{
				fis = new FileInputStream(file);
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			parser.setInput(new InputStreamReader(fis));
			eventType = parser.getEventType();
			
		}catch(XmlPullParserException e){
			e.printStackTrace();
		}
		
		try{
			
			while(parser.next() != XmlPullParser.END_TAG || parser.getName().equals("marcacao")){
				if(parser.getName() == null){
					break;
				}
				if(parser.getEventType() != XmlPullParser.START_TAG){
					continue;
				}
				String name = parser.getName();
				
				if(name.equals("ref_imo")){
					marcacoes += readTAG(parser, "ref_imo") + "\n";
				}else if(name.equals("hora")){
					marcacoes += readTAG(parser, "hora")+ "\n";
				}else if(name.equals("data")){
					marcacoes += readTAG(parser, "data")+ "\n\n";
				}else{
					//skip(parser);
				}
			}
		}catch(XmlPullParserException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		i.putExtra("aux1", marcacoes);
		startActivity(i);
	}
	
	private String readTAG(XmlPullParser parser, String tag) throws IOException, XmlPullParserException{
		parser.require(XmlPullParser.START_TAG, ns, tag);
		String text = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, tag);
		return text;
	}
	
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException{
		String result = "";
		if(parser.next() == XmlPullParser.TEXT){
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}
	
	public void VerMarcacoes(View v){
		
		if(conexao()){
			verOnline();
		}else{
			verXml();
		}
	}
}