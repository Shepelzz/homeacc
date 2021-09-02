package com.homeacc.hateoas.bbb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @GetMapping(path = "/{customerId}", produces = { "application/hal+json" })
    public EntityModel<Customer> getCustomerById(@PathVariable String customerId) {
        Customer customer = getEmployeeList().stream()
            .filter(c -> c.getCustomerId().equalsIgnoreCase(customerId))
            .findFirst().orElse(null);

        Link selfLink = linkTo(methodOn(CustomerController.class)
            .getCustomerById(customerId)).withSelfRel();

        customer.add(selfLink);

        return EntityModel.of(customer);


//        CollectionModel<EntityModel<Customer>> collectionModel = assembler
//            .toCollectionModel(repository.findByManagerId(id));
//
//        Links newLinks = collectionModel.getLinks().merge(Links.MergeMode.REPLACE_BY_REL,
//            linkTo(methodOn(EmployeeController.class).findEmployees(id)).withSelfRel());
//
//        return ResponseEntity.ok(CollectionModel.of(collectionModel.getContent(), newLinks));

    }

//    @GetMapping("/students/{id}")
//    public EntityModel<Student> retrieveStudent(@PathVariable long id) {
//        Optional<Student> student = studentRepository.findById(id);
//
//        if (!student.isPresent())
//            throw new StudentNotFoundException("id-" + id);
//
//        EntityModel<Student> resource = EntityModel.of(student.get());
//
//        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllStudents());
//
//        resource.add(linkTo.withRel("all-students"));
//
//        return resource;
//    }

    private static List<Customer> getEmployeeList()
    {
        List<Customer> list = new ArrayList<>();

        Customer on = Customer.builder().customerId("1").companyName("1c").customerName("1n").build();
        Customer tw = Customer.builder().customerId("1").companyName("1c").customerName("1n").build();
        Customer thr = Customer.builder().customerId("1").companyName("1c").customerName("1n").build();

        list.add(on);
        list.add(tw);
        list.add(thr);

        return list;
    }
}
