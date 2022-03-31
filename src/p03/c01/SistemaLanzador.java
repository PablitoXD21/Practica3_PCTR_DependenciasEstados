package src.p03.c01;

/**
 * Codigo del sistema lanzador.
 * 
 * @author Inigo y Pablo
 *
 */
public class SistemaLanzador {
	
	/**
	 * Metodo main del proyecto.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		IParque parque = new Parque();
		char letra_puerta = 'A';
		
		System.out.println("¡Parque abierto!");
		
		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			String puerta = ""+((char) (letra_puerta++));
			
			// Creación de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread (entradas).start();
			
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread (salidas).start();
			
		}
	}

}