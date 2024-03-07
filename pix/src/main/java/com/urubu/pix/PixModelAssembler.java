package com.urubu.pix;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PixModelAssembler implements  RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        
        return EntityModel.of(user,
        linkTo(methodOn(PixController.class).findOne(user.getId())).withSelfRel(),
        linkTo(methodOn(PixController.class).findAll()).withRel("users")    
        );
    }
}
