package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;

	public List<PersonVO> findAll() {
		logger.info("Finding all people");
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class); 
	}
	
	public PersonVO findById(Long id) {
		
		logger.info("Finding person id = " + id);
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id") );
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");

		var entity = DozerMapper.parseObject(person, Person.class);
		 
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person V2!");
		
		var entity = mapper.convertVoToEntity(person);
		
		return mapper.convertEntityToVo(repository.save(entity));
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));		
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one erson!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		repository.delete(entity);
	}
}
