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
	private Status statusEnum;
	private String controle;
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public AulaBean(){
		
	}
	
	public void defineHora(){
		System.out.println("# Value >> : "+ statusEnum.getValue());
//		System.out.println("Event : "+ (SelectOneMenu(e.getSource()).getValue());
		if(statusEnum.equals(Status.SEGUNDA)){
			setHora("08:00");
		}else if (statusEnum.equals(Status.TERCA)){
			setHora("09:00");
		}else if (statusEnum.equals(Status.QUARTA)){
			setHora("10:00");
		}else {
			setHora("00:00");
		}
		statusEnum = null;
	}
	
	public void save(){
		System.out.println("Teste");
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

	public Status getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(Status statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getControle() {
		return controle;
	}

	public void setControle(String controle) {
		this.controle = controle;
	}
	
}
