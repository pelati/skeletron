package com.jami.rest;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Tolerate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author jonp
 */
@Value
@Builder(toBuilder = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "sC")
    private int statusCode;

    @XmlElement(name = "b")
    private T body;

    @Tolerate
    public Response() {
        this.statusCode = 200;
        this.body = null;
    }
}
