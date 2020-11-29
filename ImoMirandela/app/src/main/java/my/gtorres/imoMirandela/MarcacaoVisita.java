package my.gtorres.imoMirandela;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class MarcacaoVisita extends AppCompatActivity{
	private TextView tv_data;
	private String userID;
	private String aux = "";
	private int dia, mes, ano;
	private String itemRef_imo;
	private String itemHora;
	private Spinner hora;
	private Spinner ref_imo;
	
	private ArrayList<String> casas;
	
	private DatePickerDialog.OnDateSetListener mDateSetListener;
	
	Context context = this;
	
	Handler h = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what == 0){
				Toast.makeText(context, "erro!", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(context, "sucesso!", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(my.gtorres.imoMirandela.R.layout.activity_marcacao_visita);
		//receber da activity login o id do utilizador que efectou o login
		Bundle dados = getIntent().getExtras();
		this.userID = dados.getString("userID");
		System.out.print("userIDasdasdsad" + userID);
		tv_data = (TextView) this.findViewById(my.gtorres.imoMirandela.R.id.tv_data);
		
		data();
		
		ref_imo = (Spinner) findViewById(my.gtorres.imoMirandela.R.id.spinnerRef_imo);
		
		casas = getCasas();
		ArrayAdapter adapt = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, casas);
		
		//ArrayAdapter adapter = ArrayAdapter.createFromResource(this, casas, android.R.layout.simple_spinner_dropdown_item);
		ref_imo.setAdapter(adapt);
		
		AdapterView.OnItemSelectedListener escolhaSpin = new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
				itemRef_imo = ref_imo.getSelectedItem().toString();
				System.out.println("item selecionadoooo " + itemRef_imo);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent){
			}
		};
		ref_imo.setOnItemSelectedListener(escolhaSpin);
		//spiner hora
		hora = (Spinner) findViewById(my.gtorres.imoMirandela.R.id.spinnerHora);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, my.gtorres.imoMirandela.R.array.AHora, android.R.layout.simple_spinner_dropdown_item);
		hora.setAdapter(adapter2);
		AdapterView.OnItemSelectedListener escolhaSpin2 = new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
				itemHora = hora.getSelectedItem().toString();
				System.out.println("item selecionadoooo " + itemHora);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent){
			}
		};
		hora.setOnItemSelectedListener(escolhaSpin2);
	}
	
	private void data(){
		tv_data.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				
				DatePickerDialog dialog = new DatePickerDialog(
						MarcacaoVisita.this,
						android.R.style.Theme_Holo_Light_Dialog_MinWidth,
						mDateSetListener,
						year, month, day);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				dialog.show();
			}
		});
		
		mDateSetListener = new DatePickerDialog.OnDateSetListener(){
			@Override
			public void onDateSet(DatePicker datePicker, int year, int month, int day){
				month = month + 1;
				
				String date = day + "/" + month + "/" + year;
				tv_data.setText(date);
			}
		};
	}
	
	private ArrayList<String> getCasas(){
		
		final String namespace = "http://imomirandela.app.com/";
		final String url = "http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
		final String method_name = "VerRefs";
		final String soap_action = "http://imomirandela.app.com/VerRefs/";
		
		final ArrayList<String> casas = new ArrayList<>();
		
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
					
					for(int c = 0; c < numEl; c++){
						casas.add(response.getProperty(c).toString());
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		thread.start();
		try{
			Thread.sleep(500);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return casas;
	}
	
	private boolean conexao() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		return cm.getActiveNetworkInfo() != null;
	}
	
	private void marcarOnline(){
		final String namespace = "http://imomirandela.app.com/";
		final String url = "http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
		final String method_name = "MarcarVisita";
		final String soap_action = "http://imomirandela.app.com/MarcarVisita/";
		
		Thread thread = new Thread(new Runnable(){
			
			@Override
			public void run(){
				SoapObject request = new SoapObject(namespace, method_name);
				
				PropertyInfo ref_imo = new PropertyInfo();
				ref_imo.type = PropertyInfo.STRING_CLASS;
				ref_imo.setName("ref_imo");
				ref_imo.setValue(itemRef_imo);
				
				PropertyInfo data = new PropertyInfo();
				data.type = PropertyInfo.STRING_CLASS;
				data.setName("data");
				data.setValue(tv_data.getText().toString());
				
				PropertyInfo hora = new PropertyInfo();
				hora.type = PropertyInfo.STRING_CLASS;
				hora.setName("hora");
				hora.setValue(itemHora.replace("h", ""));
				
				PropertyInfo userid = new PropertyInfo();
				userid.type = PropertyInfo.STRING_CLASS;
				userid.setName("idu");
				userid.setValue(userID);
				
				request.addProperty(ref_imo);
				request.addProperty(data);
				request.addProperty(hora);
				request.addProperty(userid);
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.setOutputSoapObject(request);
				HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
				try{
					androidHttpTransport.call(soap_action, envelope);
					SoapObject response = (SoapObject) envelope.bodyIn;
					System.out.println(itemRef_imo + " ref_imo");
					System.out.println(itemHora + " hora");
					System.out.println(tv_data.getText().toString() + " data");
					System.out.println(userid.getValue() + " userID");
					System.out.println(response.getProperty(0).toString() + " ++++++++++++++++++++++++++++++++++++++");
					aux = response.getProperty(0).toString();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(aux.equals("visita marcada com sucesso")){
					h.sendEmptyMessage(1);
					Intent i = new Intent(MarcacaoVisita.this, MenuImoMirandela.class);
					i.putExtra("userID", userID);
					startActivity(i);
				}else{
					h.sendEmptyMessage(0);
				}
				try{
					escreverxml();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		thread.start();
		try{
			Thread.sleep(1500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void efetuarMarcação(View v){
		
		if(conexao()){
			marcarOnline();
		} else {
			escreverxml();
		}
	}
	
	
	private void escreverxml(){
		FileOutputStream fileos = null;
		File fileMarcacoes = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "/marcacao.xml");
		
		if(fileMarcacoes.exists()){
			try{
				fileos = new FileOutputStream(fileMarcacoes, true);
				fileos.write("<marcacao>".getBytes());
				fileos.write("<ref_imo>".getBytes());
				fileos.write((itemRef_imo.getBytes()));
				fileos.write("</ref_imo>".getBytes());
				fileos.write("<hora>".getBytes());
				fileos.write((itemHora.getBytes()));
				fileos.write("</hora>".getBytes());
				fileos.write("<data>".getBytes());
				fileos.write((tv_data.getText().toString().getBytes()));
				fileos.write("</data>".getBytes());
				fileos.write("</marcacao>".getBytes());
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}else{
			try{
				fileMarcacoes.createNewFile();
				fileos = new FileOutputStream(fileMarcacoes, true);
				fileos.write("<?xml	version='1.0'	econding='iso-8859-1'	standalone='yes'	?>".getBytes());
				fileos.write("<marcacao>".getBytes());
				fileos.write("<ref_imo>".getBytes());
				fileos.write((itemRef_imo.getBytes()));
				fileos.write("</ref_imo>".getBytes());
				fileos.write("<hora>".getBytes());
				fileos.write((itemHora.getBytes()));
				fileos.write("</hora>".getBytes());
				fileos.write("<data>".getBytes());
				fileos.write((tv_data.getText().toString().getBytes()));
				fileos.write("</data>".getBytes());
				fileos.write("</marcacao>".getBytes());
				
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		try{
			fileos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Toast.makeText(getApplicationContext(), "Marcação guardada no modo ofline ", Toast.LENGTH_LONG).show();
		Intent i = new Intent(MarcacaoVisita.this, MenuImoMirandela.class);
		i.putExtra("userID", userID);
		startActivity(i);
	}
}