package com.jami.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor(force = true)
@XmlAccessorType(XmlAccessType.FIELD)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ZadevaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "zadeve")
    private List<Zadeva> zadeve;

    @XmlElement(name = "ReturnType")
    private Integer returnType;
    @XmlElement(name = "ErrorMessage")
    private String errorMessage;

}