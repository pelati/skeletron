package com.jami.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Cache
@Value
@Entity
@Builder
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor(force = true)
@XmlAccessorType(XmlAccessType.FIELD)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Zadeva implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @XmlElement(name = "ID")
    private Long id;

    @Index
    @XmlElement(name = "cName")
    private String name;
    @XmlElement(name = "izvStevilka")
    private String stevilka;
    @XmlElement(name = "dolznik")
    private String dolznik;
    @XmlElement(name = "dNaslov")
    private String naslov;
    @XmlElement(name = "dKraj")
    private String kraj;
    @XmlElement(name = "upnik")
    private String upnik;

    @XmlElement(name = "opomba")
    private String opomba;
    
    @XmlElement(name = "vred1")
    private String vrednost1;
    @XmlElement(name = "opVred1")
    private String opVrednost1;
    @XmlElement(name = "vred2")
    private String vrednost2;
    @XmlElement(name = "opVred2")
    private String opVrednost2;
    @XmlElement(name = "vred3")
    private String vrednost3;
    @XmlElement(name = "opVred3")
    private String opVrednost3;
    @XmlElement(name = "vred4")
    private String vrednost4;
    @XmlElement(name = "opVred4")
    private String opVrednost4;
    @XmlElement(name = "vred5")
    private String vrednost5;
    @XmlElement(name = "opVred5")
    private String opVrednost5;
    @XmlElement(name = "vred6")
    private String vrednost6;
    @XmlElement(name = "opVred6")
    private String opVrednost6;
    @XmlElement(name = "vred7")
    private String vrednost7;
    @XmlElement(name = "opVred7")
    private String opVrednost7;
    @XmlElement(name = "vred8")
    private String vrednost8;
    @XmlElement(name = "opVred8")
    private String opVrednost8;
    @XmlElement(name = "vred9")
    private String vrednost9;
    @XmlElement(name = "opVred9")
    private String opVrednost9;
    @XmlElement(name = "vred10")
    private String vrednost10;
    @XmlElement(name = "opVred10")
    private String opVrednost10;

}