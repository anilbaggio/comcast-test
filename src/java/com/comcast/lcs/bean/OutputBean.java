/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comcast.lcs.bean;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anil.baggio
 */
@XmlRootElement
public class OutputBean {

    private String message;
    private List<Value> value;

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
