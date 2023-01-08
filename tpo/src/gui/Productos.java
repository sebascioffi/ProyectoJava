package gui;

import tpo.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Collections;

public class Productos extends JFrame {

	private JPanel contentPane;
	private JTextField CampoCod;
	private JTextField CampoDesc;
	private JTextField CampoStock;
	private JTextField CampoStockMinimo;
	private JTextField CampoPrecio;
	private JLabel lblVenta;
	private JLabel lblCodigoVenta;
	private JTextField CampoCodigoVenta;
	private JLabel lblMedioPago;
	private JTextField CampoMedioPago;
	private JLabel lblCuotas;
	private JTextField CampoCuotas;
	private JLabel lblBaja;
	private JLabel lblRegistrarProducto;
	private JLabel lblCodigoDeProducto;
	private JTextField CampoEliminar;
	private JButton buttonEliminar;
	private JButton buttonProductosMinimos;
	private JButton buttonRanking;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Productos() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		ArrayList<Venta> listaVentas = new ArrayList<Venta>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo de Producto");
		lblCodigo.setBounds(10, 35, 143, 14);
		contentPane.add(lblCodigo);
		
		CampoCod = new JTextField();
		CampoCod.setBounds(163, 29, 86, 20);
		contentPane.add(CampoCod);
		CampoCod.setColumns(10);
		
		JButton buttonProducto = new JButton("Enviar");
		buttonProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto objProd=new Producto(Integer.parseInt(CampoCod.getText()),CampoDesc.getText(),Integer.parseInt(CampoPrecio.getText()),Integer.parseInt(CampoStock.getText()),Integer.parseInt(CampoStockMinimo.getText()));
				listaProductos.add(objProd);
				JOptionPane.showMessageDialog(null, "Producto guardado");
			}
		});
		buttonProducto.setBounds(10, 160, 89, 23);
		contentPane.add(buttonProducto);
		
		JLabel lblDesc = new JLabel("Descripcion");
		lblDesc.setBounds(10, 85, 143, 14);
		contentPane.add(lblDesc);
		
		CampoDesc = new JTextField();
		CampoDesc.setColumns(10);
		CampoDesc.setBounds(163, 82, 86, 20);
		contentPane.add(CampoDesc);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 110, 143, 14);
		contentPane.add(lblStock);
		
		CampoStock = new JTextField();
		CampoStock.setColumns(10);
		CampoStock.setBounds(163, 107, 86, 20);
		contentPane.add(CampoStock);
		
		JLabel lblStockMinimo = new JLabel("Stock minimo");
		lblStockMinimo.setBounds(10, 135, 143, 14);
		contentPane.add(lblStockMinimo);
		
		CampoStockMinimo = new JTextField();
		CampoStockMinimo.setColumns(10);
		CampoStockMinimo.setBounds(163, 132, 86, 20);
		contentPane.add(CampoStockMinimo);
		
		JLabel lblPrecio = new JLabel("Precio Unitario");
		lblPrecio.setBounds(10, 60, 143, 14);
		contentPane.add(lblPrecio);
		
		CampoPrecio = new JTextField();
		CampoPrecio.setColumns(10);
		CampoPrecio.setBounds(163, 57, 86, 20);
		contentPane.add(CampoPrecio);
		
		lblVenta = new JLabel("Registrar venta");
		lblVenta.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblVenta.setBounds(10, 211, 86, 14);
		contentPane.add(lblVenta);
		
		lblCodigoVenta = new JLabel("Codigo de Producto");
		lblCodigoVenta.setBounds(10, 230, 143, 14);
		contentPane.add(lblCodigoVenta);
		
		CampoCodigoVenta = new JTextField();
		CampoCodigoVenta.setColumns(10);
		CampoCodigoVenta.setBounds(163, 227, 86, 20);
		contentPane.add(CampoCodigoVenta);
	
		JButton buttonVenta = new JButton("Enviar");
		buttonVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double total_pago;
				for(int j = 0; j< listaProductos.size(); j++) {
					if (listaProductos.get(j).getCodigo()==Integer.parseInt(CampoCodigoVenta.getText())) {
						Venta objVen=new Venta(Integer.parseInt(CampoCodigoVenta.getText()),listaProductos.get(j).getDescripcion(),listaProductos.get(j).getPrecioUnitario(),listaProductos.get(j).getStock(),listaProductos.get(j).getStockMinimo(),CampoMedioPago.getText());
						listaProductos.get(j).dismstock();
						boolean existe=map.containsKey(Integer.parseInt(CampoCodigoVenta.getText()));
						if (existe) {
							map.put(Integer.parseInt(CampoCodigoVenta.getText()), map.get(Integer.parseInt(CampoCodigoVenta.getText()))+1);
						}else {
							map.put(Integer.parseInt(CampoCodigoVenta.getText()), 1);
						}
						int cant_cuotas;
						if (CampoMedioPago.getText().equals("efectivo")|| CampoMedioPago.getText().equals("debito")) {
							cant_cuotas=1;
						}else {
							cant_cuotas=Integer.parseInt(CampoCuotas.getText());
						}
						total_pago=objVen.pago(cant_cuotas);
						if (listaProductos.get(j).getStock()<listaProductos.get(j).getStockMinimo()) {
							JOptionPane.showMessageDialog(null, "No hay stock suficiente, no se puede cargar la venta.");
						}else {
							JOptionPane.showMessageDialog(null, "Total a pagar: "+total_pago+"\nStock Actual: "+listaProductos.get(j).getStock()+"\nStock Mínimo: "+listaProductos.get(j).getStockMinimo());
							listaVentas.add(objVen);
						}
						
					}
				
			
				}}});
		buttonVenta.setBounds(10, 303, 89, 23);
		contentPane.add(buttonVenta);
		
		lblMedioPago = new JLabel("Medio de Pago (efectivo,debito o credito)");
		lblMedioPago.setBounds(10, 255, 239, 14);
		contentPane.add(lblMedioPago);
		
		CampoMedioPago = new JTextField();
		CampoMedioPago.setColumns(10);
		CampoMedioPago.setBounds(259, 252, 86, 20);
		contentPane.add(CampoMedioPago);
		
		lblCuotas = new JLabel("2,3 o 6 cuotas (sólo en caso de crédito)");
		lblCuotas.setBounds(10, 278, 239, 20);
		contentPane.add(lblCuotas);
		
		CampoCuotas = new JTextField();
		CampoCuotas.setColumns(10);
		CampoCuotas.setBounds(259, 278, 86, 20);
		contentPane.add(CampoCuotas);
		
		lblBaja = new JLabel("Baja de Productos");
		lblBaja.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblBaja.setBounds(310, 11, 203, 14);
		contentPane.add(lblBaja);
		
		lblRegistrarProducto = new JLabel("Registrar Producto");
		lblRegistrarProducto.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblRegistrarProducto.setBounds(10, 11, 239, 14);
		contentPane.add(lblRegistrarProducto);
		
		lblCodigoDeProducto = new JLabel("Codigo de Producto a eliminar");
		lblCodigoDeProducto.setBounds(310, 35, 193, 14);
		contentPane.add(lblCodigoDeProducto);
		
		CampoEliminar = new JTextField();
		CampoEliminar.setColumns(10);
		CampoEliminar.setBounds(513, 32, 86, 20);
		contentPane.add(CampoEliminar);
		ArrayList<Integer> listaStockMinimo = new ArrayList<Integer>();
		buttonEliminar = new JButton("Enviar");
		buttonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ejecucion.eliminarElementoLista(CampoEliminar.getText(),listaProductos);
				for(int j = 0; j< listaStockMinimo.size(); j++) {
					if (listaStockMinimo.get(j)==Integer.parseInt(CampoEliminar.getText())) {
						listaStockMinimo.remove(j);
					}
				}
				JOptionPane.showMessageDialog(null, "Se eliminó el producto "+CampoEliminar.getText());
			}
		});
		buttonEliminar.setBounds(310, 60, 89, 23);
		contentPane.add(buttonEliminar);
		
		
		
		
		buttonProductosMinimos = new JButton("Ver Productos con Stock Minimo");
		buttonProductosMinimos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int j = 0; j< listaProductos.size(); j++) {
					if (listaProductos.get(j).getStock()==listaProductos.get(j).getStockMinimo()) {
						listaStockMinimo.add(listaProductos.get(j).getCodigo());
					}
				}
				JOptionPane.showMessageDialog(null,listaStockMinimo);
			}
		});
		buttonProductosMinimos.setBounds(362, 303, 267, 23);
		contentPane.add(buttonProductosMinimos);
		
		buttonRanking = new JButton("Ranking más vendidos (producto=cant ventas)");
		buttonRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> ranking = new ArrayList<Integer>();
				final Map<Integer, Integer> ordenado = map.entrySet()
		                .stream()
		                .sorted((Map.Entry.<Integer, Integer>comparingByValue().reversed()))
		                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
				
				JOptionPane.showMessageDialog(null,ordenado);
			}
		});
		buttonRanking.setBounds(310, 337, 319, 23);
		contentPane.add(buttonRanking);
	}
}