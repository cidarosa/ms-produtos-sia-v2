package br.com.fiap.ms_produto.controller;

import br.com.fiap.ms_produto.dto.LojaDTO;
import br.com.fiap.ms_produto.service.LojaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaService service;

    @GetMapping
    public ResponseEntity<List<LojaDTO>> getAll(){
        List<LojaDTO> list = service.findAllL();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LojaDTO> getById (@PathVariable Long id){

        LojaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<LojaDTO> save(@RequestBody @Valid LojaDTO dto){

        dto = service.save(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LojaDTO> update(@PathVariable Long id,
                                          @RequestBody @Valid LojaDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
