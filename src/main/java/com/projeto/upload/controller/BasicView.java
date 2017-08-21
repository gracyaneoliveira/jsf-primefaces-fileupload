package com.projeto.upload.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import com.projeto.upload.model.Car;
import com.projeto.upload.service.CarService;
 
@ManagedBean
@ViewScoped
public class BasicView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Car> cars;
	
	private List<Boolean> list;
     
    @ManagedProperty("#{carService}")
    private CarService service;
 
    @PostConstruct
    public void init() {
        cars = service.createCars(10);
        list = Arrays.asList(true, true, true, false, true, true);
    }
    
    public void teste(){
    	System.out.println("teste");
    }
     
    public List<Car> getCars() {
        return cars;
    }
 
    public void setService(CarService service) {
        this.service = service;
    }
    
    public List<Boolean> getList() {
    	return list;
	}
    
    public void onToggle(ToggleEvent e) {
    	System.out.println((Integer) e.getData());
    	list.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
	}
}