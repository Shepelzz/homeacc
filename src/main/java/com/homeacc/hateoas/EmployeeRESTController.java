package com.homeacc.hateoas;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeRESTController {

    @GetMapping(value = "/employees")
    public EmployeeListVO getAllEmployees()
    {
        EmployeeListVO employeesList  = new EmployeeListVO();

        for (EmployeeVO employee : EmployeeDB.getEmployeeList())
        {
            //Adding self link employee 'singular' resource
            Link link = linkTo(EmployeeRESTController.class)
                .slash(employee.getEmployeeId())
                .withSelfRel();

            //Add link to singular resource
            employee.add(link);

            //Adding method link employee 'singular' resource
            ResponseEntity<EmployeeReport> methodLinkBuilder = methodOn(EmployeeRESTController.class).getReportByEmployeeById(employee.getEmployeeId());
            Link reportLink = linkTo(methodLinkBuilder)
                .withRel("employee-report");

            //Add link to singular resource
            employee.add(reportLink);

            employeesList.getEmployees().add(employee);
        }

        //Adding self link employee collection resource
        Link selfLink = linkTo(methodOn(EmployeeRESTController.class).getAllEmployees())
            .withSelfRel();

        //Add link to collection resource
        employeesList.add(selfLink);

        return employeesList;
    }

    @RequestMapping(value = "/employees/{id}")
    public ResponseEntity<EmployeeVO> getEmployeeById (@PathVariable("id") int id)
    {
        if (id <= 3) {
            EmployeeVO employee = EmployeeDB.getEmployeeList().get(id-1);

            Links newLinks = employee.getLinks().merge(Links.MergeMode.REPLACE_BY_REL,
                linkTo(methodOn(EmployeeRESTController.class).getEmployeeById(id)).withSelfRel());

            employee.add(newLinks);
            return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<EmployeeVO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/employees/{id}/report")
    public ResponseEntity<EmployeeReport> getReportByEmployeeById (@PathVariable("id") int id)
    {
        //Do some operation and return report
        return null;
    }
}
