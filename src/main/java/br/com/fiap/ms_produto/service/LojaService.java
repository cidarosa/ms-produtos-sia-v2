package br.com.fiap.ms_produto.service;

import br.com.fiap.ms_produto.dto.LojaDTO;
import br.com.fiap.ms_produto.entities.Loja;
import br.com.fiap.ms_produto.repositories.LojaRepository;
import br.com.fiap.ms_produto.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LojaService {

    @Autowired
    private LojaRepository repository;

    @Transactional(readOnly = true)
    public List<LojaDTO> findAllL() {

        return repository.findAll().stream().map(LojaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public LojaDTO findById(Long id) {

        Loja entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. Id: " + id)
        );

        return new LojaDTO(entity);
    }

    @Transactional
    public LojaDTO save(LojaDTO dto) {

        Loja entity = new Loja();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new LojaDTO(entity);
    }

    @Transactional
    public LojaDTO update(Long id, LojaDTO dto) {

        try {
            Loja entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new LojaDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }
    }

    @Transactional
    public void delete(Long id){

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }
        repository.deleteById(id);
    }

    private void copyDtoToEntity(LojaDTO dto, Loja entity) {

        entity.setNome(dto.getNome());
    }

}
