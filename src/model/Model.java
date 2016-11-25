package model;

import java.io.File;
import java.io.Serializable;

public class Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private boolean status;
	private boolean copiado;
	private File origem;
	private String destino;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the copiado
	 */
	public boolean isCopiado() {
		return copiado;
	}
	/**
	 * @param copiado the copiado to set
	 */
	public void setCopiado(boolean copiado) {
		this.copiado = copiado;
	}
	/**
	 * @return the origem
	 */
	public File getOrigem() {
		return origem;
	}
	/**
	 * @param origem the origem to set
	 */
	public void setOrigem(File origem) {
		this.origem = origem;
	}
	/**
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}
	/**
	 * @param destino the destino to set
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
}
