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
public class Challenge implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @XmlElement(name = "n")
    private String name;
    @XmlElement(name = "d")
    private String description;

    @XmlElement(name = "pV")
    private Integer pointsValue;

    @XmlElement(name = "dF")
    private Date dateFrom;
    @XmlElement(name = "dT")
    private Date dateTo;

    /**
     * "0" (null) - pay streak
     * "1" - visit store
     * "2" - spend XX amount on the selected date
     */
    @XmlElement(name = "t")
    private String type;

    /**
     * How many "burgers" has the user already bought - count down from XY
     */
    @XmlElement(name = "c")
    private Integer counter;

    /**
     * Visit this store - daily challenge
     */
    @XmlElement(name = "sI")
    private Integer storeId;

    /**
     * Spend "price" amount on a daily challenge
     */
    @XmlElement(name = "p")
    private Integer price;
}