package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

public class Parque implements IParque{


	// TODO 
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> puertas;
	
	
	public Parque() {	// TODO
		contadorPersonasTotales = 0;
		puertas = new Hashtable<String, Integer>();
		// TODO
	}


	@Override
	public void entrarAlParque(String puerta){		// TODO
		
		// Si no hay entradas por esa puerta, inicializamos
		if(puertas.get(puerta) == null)
			puertas.put(puerta, 0);
		
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
		
	}
	
	// 
	// TODO Método salirDelParque
	//
	
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: puertas.keySet()){
			System.out.println("----> Por puerta " + p + " " + puertas.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = puertas.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		// TODO 
		// TODO
	}
	
	/**
	 * Método que pone un hilo en espera.
	 */
	private void waitForCheck() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void comprobarAntesDeEntrar(){	// TODO
		//
		// TODO
		//
	}

	protected void comprobarAntesDeSalir(){	
		
		while (contadorPersonasTotales < 1)
			waitForCheck();
	}


	@Override
	public void salirDelParque(String puerta) {
		
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