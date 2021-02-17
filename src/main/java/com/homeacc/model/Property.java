package com.homeacc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Property extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private Country country;

    private String city;
    private String street;
    private String houseNo;
    private Integer flat;

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    private List<Counter> counters;

    public String getFullName() {
        return StringUtils.join(country.getName(), ", ",
            city, ", ",
            street, ", ",
            houseNo, ", кв. ",
            flat);
    }

}
