package com.comcast.lcs.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anil.baggio
 */
@XmlRootElement
public class InputBean {

    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

}
