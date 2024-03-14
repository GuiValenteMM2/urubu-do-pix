package com.urubu.pix;



import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PixController {
    
    private final PixRepository repository;
    private final PixModelAssembler assembler;


    public PixController(PixRepository repository, PixModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
 
    @GetMapping("/users/{id}")
    public EntityModel<User> findOne(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> findAll() {

        List<EntityModel<User>> users = repository.findAll().stream()
        .map(assembler::toModel).collect(Collectors.toList());
        
        return CollectionModel.of(users, linkTo(methodOn(PixController.class).findAll()).withSelfRel());
    }

    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody User user) {

        EntityModel<User> entityModel = assembler.toModel(repository.save(user));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }
}
