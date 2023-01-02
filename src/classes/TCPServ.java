package classes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class TCPServ {
	private ServerSocket miSS;
	private Socket serverSockt;
	private InputStream is;
	private OutputStream os;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Object oAR;
	private LocalDate fechaRecibida;
	private String signos;
	public TCPServ(int port) throws IOException 
	{
		this.miSS = new ServerSocket(port);
	}
	
	
	public void encenderServidor() 
	{
		try {
		System.out.println("(servidor) Esperando conexiones...");
		this.serverSockt = miSS.accept();
		System.out.println("(servidor) Conexión establecida");
		
		
		} catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void conectar() 
	{
		try {
			System.out.println("(Servidor) Abriendo canales de comunicación...");
			os = this.serverSockt.getOutputStream();
			is = this.serverSockt.getInputStream();
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
			System.out.println("(servidor) Canales de comunicación abiertos.");
			System.out.println("(Servidor) Leyendo fecha...");
			oAR = ois.readObject();
			System.out.println("(Servidor) fecha recibida.");
			
			
		} catch (IOException |ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.fechaRecibida =LocalDate.parse(String.valueOf(oAR));
	}
	
	public void enviar() 
	{
		String frase;
		String chino = leerSignoChino(fechaRecibida);
		String euro = leerSignoEuropeo(fechaRecibida);
		frase = ("Tu signo zodiacal chino es: "+ chino + " y el europeo es: "+euro);
		System.out.println("Enviando singnos Zodiacales...");
		try {
			oos.writeObject(frase);
			System.out.println("Mensaje Enviado al Cliente!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String leerSignoChino(LocalDate fecha) 
	{
		String horoscopo = "";
        int año = fecha.getYear();
        int resto = año % 12;
        switch (resto) {
            case 0: horoscopo = "Mono"; break;
            case 1: horoscopo = "Gallo"; break;
            case 2: horoscopo = "Perro"; break;
            case 3: horoscopo = "Cerdo"; break;
            case 4: horoscopo = "Rata"; break;
            case 5: horoscopo = "Buey"; break;
            case 6: horoscopo = "Tigre"; break;
            case 7: horoscopo = "Conejo"; break;
            case 8: horoscopo = "Dragon"; break;
            case 9: horoscopo = "Serpiente"; break;
            case 10: horoscopo = "Caballo"; break;
            case 11: horoscopo = "Cabra"; break;
        }
 
     return horoscopo;
    }   
 
	public String leerSignoEuropeo(LocalDate fecha) 
	{
		int mes = fecha.get(ChronoField.MONTH_OF_YEAR);
		int dia = fecha.get(ChronoField.DAY_OF_MONTH);
		 if (mes == 1) {
	            if (dia >= 21) {
	                //acuario
	                return "acuario";
	            } else {
	                //capricornio
	                return "capricornio";
	            }
	        }
	        if (mes == 2) {
	            if (dia >= 19) {
	                return "piscis";
	            } else {
	                return "acuario";
	            }
	        }
	        if (mes == 3) {
	            if (dia >= 20) {
	                //acuario
	                return "aries";
	            } else {
	                //capricornio
	                return "piscis";
	            }
	        }
	        if (mes == 4) {
	            if (dia >= 20) {
	                return "tauro";
	            } else {
	                return "Aries";
	            }
	        }
	        if (mes == 5) {
	            if (dia >= 21) {
	                //acuario
	                return "geminis";
	            } else {
	                //capricornio
	                return "tauro";
	            }
	        }
	        if (mes == 6) {
	            if (dia >= 20) {
	                return "cancer";
	            } else {
	                return "geminis";
	            }
	        }
	        if (mes == 7) {
	            if (dia >= 22) {
	                //acuario
	                return "leo";
	            } else {
	                //capricornio
	                return "cancer";
	            }
	        }
	        if (mes == 8) {
	            if (dia >= 21) {
	                return "virgo";
	            } else {
	                return "leo";
	            }
	        }
	        if (mes == 9) {
	            if (dia >= 22) {
	                //acuario
	                return "libra";
	            } else {
	                //capricornio
	                return "virgo";
	            }
	        }
	        if (mes == 10) {
	            if (dia >= 22) {
	                return "escorpion";
	            } else {
	                return "libra";
	            }
	        }
	        if (mes == 11) {
	            if (dia >= 21) {
	                //acuario
	                return "sagitario";
	            } else {
	                //capricornio
	                return "escorpion";
	            }
	        }
	        if (mes == 12) {
	            if (dia >= 21) {
	                return "capricornio";
	            } else {
	                return "sagitario";
	            }
	        }
	        return "fecha no valida papu :v";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try {
				TCPServ server = new TCPServ(49171);
				server.encenderServidor();
				server.conectar();
				server.enviar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}

}
