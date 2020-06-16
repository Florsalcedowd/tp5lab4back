package com.instrumento.main.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.instrumento.main.repository.BaseRepository;
import com.instrumento.main.specifications.SearchSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.instrumento.main.entities.Instrumento;
import com.instrumento.main.repository.InstrumentoRepository;

@Service
public class InstrumentoService extends BaseServiceImpl<Instrumento, Integer> {

    public InstrumentoService(BaseRepository<Instrumento, Integer> baseRepository) {
        super(baseRepository);
    }

    public List<Instrumento> filterAll(String filter) throws Exception {
        try {
            SearchSpecification<Instrumento> spec = new SearchSpecification<Instrumento>();
            Specification<Instrumento> filterName = spec.findByProperty("instrumento", filter);

            List<Instrumento> entities = baseRepository.findAll(Specification.where(filterName));

            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
