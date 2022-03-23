package src.p03.c01;

/**
 * Permite sacar a los usuarios que hay en el parque
 * 
 * @author Inigo y Pablo
 *
 */

public class ActividadSalidaPuerta{
	
	private static final int NUMSALIDAS = 20;
	private String puerta;
	private IParque parque;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param puerta
	 * @param parque
	 */
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		
		this.puerta = puerta;
		this.parque = parque;
	}
}