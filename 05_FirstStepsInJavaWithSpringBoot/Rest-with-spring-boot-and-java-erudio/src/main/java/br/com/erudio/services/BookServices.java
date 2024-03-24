package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.BookController;
import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.BookMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;

@Service
public class BookServices {
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	BookMapper mapper;	
	
	public List<BookVO> findAll() {
		logger.info("Finding all book");
		
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}	
	
	public BookVO findById(Long id) {
		logger.info("Finding book id = " + id);
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id") );
		
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		//vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		vo.add(linkTo(methodOn(BookController.class).findAll()).withRel("collection"));
		//vo.add(linkTo(methodOn(BookController.class).update(vo)).withRel("update-book"));
		
		return vo;
	}

	public BookVO create(BookVO book) {
		if (book == null) throw new RequiredObjectIsNullException();
		logger.info("Creating one book!");

		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);

		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public BookVO update(BookVO book) {
		if (book == null) throw new RequiredObjectIsNullException();
		logger.info("Updating one book!");
		
		var entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));		
		
		entity.setAuthor(book.getAuthor());
		entity.setTitle(book.getTitle());
		entity.setPrice(book.getPrice());
		entity.setLaunchDate(book.getLaunchDate());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;		
	}
	
	public void delete(Long id) {
		logger.info("Deleting one book!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		repository.delete(entity);
	}	
}
