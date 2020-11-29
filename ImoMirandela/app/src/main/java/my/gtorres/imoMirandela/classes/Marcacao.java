package my.gtorres.imoMirandela.classes;

public class Marcacao{
	
	private String idu;
	private String ref_imo;
	private String data;
	private String hora;
	
	public void setClienteID(String idu){
		this.idu = idu;
	}
	
	public void setRef_Imo(String ref_imo){
		this.ref_imo = ref_imo;
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	public void setHora(String hora){
		this.hora = hora;
	}
	
	//get
	
	public String getClienteID(){
		return this.idu;
		
	}
	
	public String getRef_Imo(){
		return this.ref_imo;
		
	}
	
	public String getData(){
		return this.data;
	}
	
	public String getHora(){
		return this.hora;
	}
}
