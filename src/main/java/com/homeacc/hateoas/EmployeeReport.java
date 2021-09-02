package com.homeacc.hateoas;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name="employee-report")
@Data
public class EmployeeReport extends RepresentationModel<EmployeeReport> implements Serializable {

    private static final long serialVersionUID = 1L;

    //You can add field as needed
}
