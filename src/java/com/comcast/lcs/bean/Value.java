package com.comcast.lcs.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anil.baggio
 */
@XmlRootElement
public class Value {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
