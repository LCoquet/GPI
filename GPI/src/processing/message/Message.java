package processing.message;

import data.Prison;


/**
 * @brief Abstract class for the communication of Humans
 * 
 * @author CHABOT Yohan, COQUET Leo, DE SOUSA Julia, GASTEBOIS Emma, HANG Alexandre, POUPET Maria-Lorena
 *
 */
public abstract class Message<T> {

	private T content;
	private Prison prison;
	
	public Message(T content, Prison prison) {
		this.content = content;
		this.prison = prison;
	}
	
	public abstract void send();

	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	
	protected Prison getPrison() {
		return prison;
	}
	
}
