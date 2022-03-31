package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Codigo del parque en cuestion.
 * 
 * @author Inigo y Pablo
 *
 */

public class Parque implements IParque{


	private int contadorPersonasTotales;
	private Hashtable<String, Integer> puertas;
	public static int MAX_PERSONAS = 50;
	
	/**
	 * Constructor de la clase.
	 */
	public Parque() {	// TODO
		contadorPersonasTotales = 0;
		puertas = new Hashtable<String, Integer>();
	}


	@Override
	public synchronized void entrarAlParque(String puerta){

		// Si no hay entradas por esa puerta, inicializamos
		if(puertas.get(puerta) == null)
			puertas.put(puerta, 0);
		comprobarAntesDeEntrar();
		// Aumentamos el contador total y el individual
		// imprimir Mensaje de entrada
		System.out.println("Entrada al parque por la puerta "+ puerta);
		//Actializar contadores
		Integer entradas = puertas.get(puerta);
		contadorPersonasTotales ++;
		//entradas++;
		puertas.put(puerta, entradas+1);
		//Comprobar el invariante
		checkInvariante();

		imprimirInfo(puerta, "Entrada");
		
		this.notifyAll();
	}
	
	/**
	 * Metodo que imprime la informacion de los movimientos que se realizan en el parque.
	 * 
	 * @param puerta
	 * @param movimiento
	 */
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: puertas.keySet()){
			System.out.println("----> Por puerta " + p + " " + puertas.get(p));
		}
		System.out.println(" ");
	}
	
	/**
	 * Metodo que realiza la suma en cada puerta.
	 * 
	 * @return sumaContadoresPuerta
	 */
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = puertas.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	/**
	 * Invariantes.
	 */
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte.";
		assert contadorPersonasTotales <= MAX_PERSONAS : "INV: El parque supera la capacidad maxima de personas.";
		assert contadorPersonasTotales >= 0 : "INV: El parque supera la capacidad de personas negativas.";
	}
	
	/**
	 * Metodo que comprueba las personas antes de entrar al parque y pone el hilo en espera.
	 */
	protected void comprobarAntesDeEntrar() {
		
		while (contadorPersonasTotales >= MAX_PERSONAS) {
			try {
				this.wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo que comprueba las personas antes de salir del parque y pone el hilo en espera.
	 */
	protected void comprobarAntesDeSalir(){	
		
		while (contadorPersonasTotales <= 0) {
			try {
				this.wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public synchronized  void salirDelParque(String puerta) {
		if(puertas.get(puerta) == null)
			puertas.put(puerta, 0);
		comprobarAntesDeSalir();
		//Actializar contadores
		Integer entradas = puertas.get(puerta);
		contadorPersonasTotales --;
		//entradas--;
		puertas.put(puerta, entradas-1);
		checkInvariante();
		imprimirInfo(puerta, "Salida");
		this.notifyAll();	
	}
}