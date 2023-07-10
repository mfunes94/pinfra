package com.primfacesClasses;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class SelectOneMenuView {
	
	private List<SelectItem> options;
	private String labeled;
	
    public SelectOneMenuView() {
        options = new ArrayList<>();
        options.add(new SelectItem("Licenciatura en Tecnologías de la Información"));
        options.add(new SelectItem("Licenciatura en Jazz y Música Creativa"));
        options.add(new SelectItem("Ingeniería Biomédica"));
        options.add(new SelectItem("Ingeniería Agroambiental"));
    }
    public String getLabeled() {
        return labeled;
    }

    public void setLabeled(String labeled) {
        this.labeled = labeled;
    }

    public List<SelectItem> getOptions() {
        return options;
    }

    public void onOptionChange(ValueChangeEvent event) {
        labeled = (String) event.getNewValue();
    }
}
