package my.gtorres.imoMirandela;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Calendar;

public class AdminMenuTDporData extends AppCompatActivity{
	
	private TextView data_tv;
	private String userID = "";
	
	private DatePickerDialog.OnDateSetListener mDateSetListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(my.gtorres.imoMirandela.R.layout.activity_admin_menu_tdpor_data);
		data_tv = (TextView) this.findViewById(my.gtorres.imoMirandela.R.id.data_tv);
		data();
	}
	
	private void data(){
		data_tv.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				
				DatePickerDialog dialog = new DatePickerDialog(
						AdminMenuTDporData.this,
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
				data_tv.setText(date);
			}
		};
	}
	
	public void BTconfirmar(View v){
		
		final Intent i = new Intent(AdminMenuTDporData.this, AdminVerMarcacoes.class);
		
		final String namespace = "http://imomirandela.app.com/";
		final String url = "http://193.137.107.134:8080/WsimoMirandela/WsImoMirandela";
		final String method_name = "VerTodasMarcacoesPorData";
		final String soap_action = "http://imomirandela.app.com/VerTodasMarcacoesPorData/";
		
		new Thread(new Runnable(){
			@Override
			public void run(){
				SoapObject request = new SoapObject(namespace, method_name);
				
				PropertyInfo data = new PropertyInfo();
				data.type = PropertyInfo.STRING_CLASS;
				data.setName("data");
				data.setValue(data_tv.getText().toString());
				
				request.addProperty(data);
				System.out.println("data IS+++++++++ " + data.getValue());
				
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
						aux += " idu : " + resultado.getProperty(0).toString() + "\n";
						aux += " data : " + resultado.getProperty(1).toString() + "\n";
						aux += " hora  : " + resultado.getProperty(2).toString() + "\n";
						aux += " ref_imo : " + resultado.getProperty(3).toString() + "\n " + "\n " + "\n ";
						System.out.println("ciclo");
					}
					i.putExtra("aux1", aux.toString());
					i.putExtra("userID", userID);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
		}).start();
		
		try{
			Thread.sleep(500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		startActivity(i);
	}
}