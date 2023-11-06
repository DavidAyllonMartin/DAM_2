package org.infantaelena.ies.psp.UD2.ejemplos.ejemplo8;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.lang.Thread.State;

import java.awt.event.*;

public class EjemploGUI extends JFrame implements ActionListener{
	JButton boton1, boton2;
		
	JLabel etiqueta1, etiqueta2;
	
	static JTextArea textArea1;
	static JTextArea textArea2;
		
	public EjemploGUI () {
		//Creo la ventana
		setTitle("SALUDO-DESPEDIDA");
//		super("SALUDO-DESPEDIDA");
		setLayout (null);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//creo los botones
		boton1 = new JButton("Hola");
		boton1.setBounds(100, 50, 200, 30);
		boton1.addActionListener(this);
		add(boton1);
		//boton Reanudar1
		boton2 = new JButton("Adiós");
		boton2.setBounds(100, 100, 200, 30);
		boton2.addActionListener(this);
		add(boton2);
		//boton2.setEnabled(false);
				
		//mensajes
		textArea1 = new JTextArea("");
		textArea1.setBounds(150, 150, 100, 30);
		add(textArea1);
		
		//Texto de los hilos
		etiqueta1 = new JLabel("");
		etiqueta1.setBounds(150, 200, 300, 30);
		add(etiqueta1);

	} 
		
	public void actionPerformed(ActionEvent e) {
		//Se hace clic en el botón comenzar proceso
		if (e.getSource()==boton1) {
			
			etiqueta1.setText("Has pulsado el botón HOLA");
			textArea1.setText("Hola");
		}
		if (e.getSource()==boton2) {
			etiqueta1.setText("Has pulsado el botón ADIÓS");
			textArea1.setText("Adiós");
		}
	}

	public static void main(String[] args) {
	// TODO Auto-generated method stub
		EjemploGUI ventana = new EjemploGUI();
		ventana.setVisible(true);
	}

}
