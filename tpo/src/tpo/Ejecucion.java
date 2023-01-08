package tpo;
import gui.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejecucion {

	public static void main(String[] args) {
		presentarVentana();	
		}
	
	private static void presentarVentana() {
		Productos miVentana=new Productos();
		miVentana.setVisible(true);
	}
	
	public static void eliminarElementoLista(String codigo, ArrayList<Producto>listaProductos) {
		for(int j = 0; j< listaProductos.size(); j++) {
			if(listaProductos.get(j).getCodigo() == Integer.parseInt(codigo)) {
				listaProductos.remove(j);
			}
		}
	}
	

}
