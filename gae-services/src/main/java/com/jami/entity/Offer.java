package com.jami.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

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
public class Offer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @XmlElement(name = "pN")
    private String providerName;
    @XmlElement(name = "iU")
    private String imageUrl;
    @XmlElement(name = "n")
    private String name;

    @XmlElement(name = "d")
    private String description;

    @XmlElement(name = "dF")
    private Date dateFrom;
    @XmlElement(name = "dT")
    private Date dateTo;

    @XmlElement(name = "dP")
    private Integer discountPercent;

    /**
     * How many points to activate an offer
     */
    @XmlElement(name = "pC")
    private Integer pointsCost;

    /**
     * "0" (null) - one time offer
     * "1" - collect "cap" items for one free
     */
    @XmlElement(name = "t")
    private String type;

    /**
     * How many "burgers" has the user already bought - count down from 10
     */
    @XmlElement(name = "c")
    private Integer counter;
}