package com.instrumento.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ServicioGenerico<E, R extends JpaRepository<E, Integer>> implements IservicioGenerico<E> {
	
	@Autowired
	private R repository;
	
	@Override
	public List<E> findAll() throws Exception {
		try {
			List<E> entities = repository.findAll();
			return entities;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
		
	
	
	}

	@Override
	public E findById(int id) throws Exception {
		try {

			// se usa para atrapar un null
			Optional<E> varOptional = repository.findById(id);

			E entity = varOptional.get();

			return entity;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
		
	}

	@Override
	public E save(E entityForm) throws Exception {
		try {

			entityForm = repository.save(entityForm);

			return entityForm;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
	}

	@Override
	public E update(int id, E entityForm) throws Exception {

		try {
			Optional<E> entityOptional = repository.findById(id);

			E entity = entityOptional.get();

			entity = repository.save(entityForm);

			return entity;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}

	}
	
	@Override
	public int countPages(int size) throws Exception {
		try {
			Pageable pageable = PageRequest.of(0, size);
			return repository.findAll(pageable).getTotalPages();			

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public int countItems() throws Exception {
		try {
			return repository.findAll().size();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<E> findAll(int page, int size) throws Exception {
		
		try {
			Pageable pageable = PageRequest.of(page, size);
			return repository.findAll(pageable).getContent();			

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	@Override
	public boolean delete(int id) throws Exception {
		
		try {
			
			if (repository.existsById(id)) {
				
				repository.deleteById(id);
				return true;
				
			} else {

				throw new Exception();
				
			}
		} catch (Exception e) {

			throw new Exception(e.getMessage());
			
		}
	}
}
