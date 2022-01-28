package com.backend.springboot.controller;


import com.backend.springboot.dto.DeskDTO;
import com.backend.springboot.dto.DeskSizeDTO;
import com.backend.springboot.dtoTransformation.DeskMapper;
import com.backend.springboot.model.Desk;
import com.backend.springboot.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/desks")
public class DeskController {

    @Autowired
    private DeskService deskService;

    @Autowired
    private DeskMapper mapper;

    //add desk
    @PostMapping(value = "/")
//    @PreAuthorize("hasRole('ROLE_ADMIN')") //svi
    public ResponseEntity<DeskDTO> addOrUpdate(@RequestBody Desk desk) {
        Desk newDesk = deskService.save(desk);

        return new ResponseEntity<>(mapper.toDeskDTO(newDesk), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DeskDTO> updateSize(@PathVariable Integer id, @RequestBody DeskSizeDTO size) {

        Desk desk = deskService.findOne(id);
        desk.setWidth(size.getWidth());
        desk.setHeight(size.getHeight());

        desk = deskService.save(desk);

        return new ResponseEntity<>(mapper.toDeskDTO(desk), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeskDTO> deleteDesk (@PathVariable Integer id) {
        Desk desk = deskService.findOne(id);
        if(desk != null){
            deskService.delete(desk);

            return new ResponseEntity<>(mapper.toDeskDTO(desk), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/")
    public void deleteAll() {
        deskService.deleteAll();
    }


    @GetMapping(value = "/")
//    @PreAuthorize("hasAnyRole('ROLE_WAITER','ROLE_ADMIN')")
    public ResponseEntity<List<DeskDTO>> getDesks() {

        List<Desk> desks = deskService.findAll();
        List<DeskDTO> dtos = desks.stream()
                .map(mapper::toDeskDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
