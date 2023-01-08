package tpo;

import java.util.ArrayList;

public class Producto {
	
	protected int codigo;
	protected String descripcion;
	protected int precio_unitario;
	protected int stock;
	protected int stock_minimo;
	
	public Producto(int codigo,String descripcion,int precio_unitario,int stock,int stock_minimo) {
		this.codigo=codigo;
		this.descripcion=descripcion;
		this.precio_unitario=precio_unitario;
		this.stock=stock;
		this.stock_minimo=stock_minimo;
	}
	
	
	public void setCodigo(int codigo) {
		this.codigo=codigo;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion=descripcion;
	}
	
	public void setPrecio_unitario(int precio_unitario) {
		this.precio_unitario=precio_unitario;
	}
	
	public void setStock(int stock) {
		this.stock=stock;
	}
	
	public void setStock_minimo(int stock_minimo) {
		this.stock_minimo=stock_minimo;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public int getPrecioUnitario() {
		return this.precio_unitario;
	}
	
	public int getStock() {
		return this.stock;
	}
	

	public int getStockMinimo() {
		return this.stock_minimo;
	}
	
	public void dismstock() {
		this.stock--;
	}
	
}
