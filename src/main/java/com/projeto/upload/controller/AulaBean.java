package com.projeto.upload.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.projeto.upload.model.Status;

@ManagedBean
@ViewScoped
public class AulaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String diaAula;
	private String hora = "00:00";
	
	public AulaBean(){
		
	}
	
	public void defineHora(){
//		System.out.println("Event : "+ (SelectOneMenu(e.getSource()).getValue());
		if(diaAula.equals(Status.SEGUNDA.getValue())){
			setHora("08:00");
		}else if (diaAula.equals(Status.TERCA.getValue())){
			setHora("09:00");
		}else if (diaAula.equals(Status.QUARTA.getValue())){
			setHora("10:00");
		}else if (diaAula.isEmpty()){
			setHora("00:00");
		}
	}
	
	public Status [] getStatus(){
		return Status.values();
	}

	public String getDiaAula() {
		return diaAula;
	}
	
	public void setDiaAula(String diaAula) {
		this.diaAula = diaAula;
	}

	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
}
