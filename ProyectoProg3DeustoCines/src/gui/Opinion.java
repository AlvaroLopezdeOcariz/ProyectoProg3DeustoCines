package gui;

public class Opinion {
	private String autor;
	private String texto;
	
	
	
	public Opinion(String autor, String texto) {
		super();
		this.autor = autor;
		this.texto = texto;
	}



	public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public String getTexto() {
		return texto;
	}



	public void setText(String texto) {
		this.texto = texto;
	}
	
	
	
}
