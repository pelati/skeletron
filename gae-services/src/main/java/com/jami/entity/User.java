package com.jami.entity;

import java.io.Serializable;
import java.util.List;

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
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @XmlElement(name = "fN")
    private String firstName;
    @XmlElement(name = "lN")
    private String lastName;

    @XmlElement(name = "p")
    private Integer points;

    @XmlElement(name = "os")
    private List<Offer> offers;

    @XmlElement(name = "cs")
    private List<Challenge> challenges;
}
