package com.projeto.upload.controller;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

import com.projeto.upload.model.User;

//http://jsfiddle.net/CU3vb/3/    compare objects
//https://stackoverflow.com/questions/1068834/object-comparison-in-javascript
//https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Guide/Fun%C3%A7%C3%B5es
//https://stackoverflow.com/questions/14772926/primefaces-wizard-how-to-return-js-function-tab-for-onnext
 
@ManagedBean
@ViewScoped
public class UserWizardBean implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private User user = new User();
     
    private boolean skip = false;
     
    public User getUser() {
    	int s = "ddfdf".length();
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
     
    public void save() {        
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getFirstname());
        FacesContext.getCurrentInstance().addMessage(null,msg);
    }
     
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
    
    
}
