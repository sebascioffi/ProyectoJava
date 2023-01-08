package tpo;


public class Venta extends Producto {
	private String medio_pago;
	
	public Venta(int codigo,String descripcion,int precio_unitario,int stock,int stock_minimo,String medio_pago) {
		super(codigo,descripcion,precio_unitario,stock,stock_minimo);
		this.medio_pago=medio_pago;
	}
	
	public double pago(int cuotas) {
		double precio;
		precio=0;
		if (this.medio_pago.equals("debito")){
			precio=this.precio_unitario;
		}
		if (this.medio_pago.equals("efectivo")) {
			precio=this.precio_unitario-this.precio_unitario*0.10;
		}
		if (this.medio_pago.equals("credito")) {
			if (cuotas==2){
				precio=this.precio_unitario*1.06;
			}
			if (cuotas==3){
				precio=this.precio_unitario*1.12;
			}
			if (cuotas==6){
				precio=this.precio_unitario*1.20;
			}
			
		}
		return precio;
		
	}
	
	
	
	

}
