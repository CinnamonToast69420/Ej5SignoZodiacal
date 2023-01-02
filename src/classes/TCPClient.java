package classes;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;

public class TCPClient {
	public int puerto;
	public String ip; 
	private Socket socketCliente;
	private InputStream is;
	private OutputStream os;
	private ObjectInputStream objIS;
	private ObjectOutputStream objOS;
	private Object mensaje;
	
	public TCPClient(String ip, int puerto) 
	{
		this.ip = ip;
		this.puerto = puerto;
	
	}
	
	public void conectar() 
	{
		System.out.println("(Cliente) Estableciendo conexión...");
		try {
			socketCliente = new Socket(ip, puerto);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("(Cliente) Conexión establecida.");
		
	}
	
	public void enviar() 
	{
		System.out.println("(cliente) Abriendo flujos de datos");
		System.out.println("(Cliente) Enviando mensaje...");
		try {
			os = socketCliente.getOutputStream();
		
		is = socketCliente.getInputStream();
		objIS = new ObjectInputStream(is); 
		objOS = new ObjectOutputStream(os);
		objOS.writeObject("1997-04-01");
		mensaje =objIS.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("(Cliente) Mensaje recibido: " + String.valueOf(mensaje));
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TCPClient cliente = new TCPClient("127.0.0.1", 49171);
		cliente.conectar();
		cliente.enviar();
	

}

}