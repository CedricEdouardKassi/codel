package jsf;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="Language")
@SessionScoped
public class Language implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String language;
	private Locale l;
	
	@PostConstruct
	public void init(){
		l = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Locale getL() {
		return l;
	}

	public void setL(Locale l) {
		this.l = l;
	}

	public String change(){
		switch (language) {
			case "en":
				l = Locale.ENGLISH;
			break;
			case "fr":
				l = Locale.FRENCH;
			break;
		}
		FacesContext.getCurrentInstance().getViewRoot().setLocale(l);
		return "index";
	}
}
