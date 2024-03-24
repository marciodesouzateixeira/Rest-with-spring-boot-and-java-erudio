package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.mapper.custom.BookMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.services.BookServices;
import br.com.erudio.unittests.mapper.mocks.MockBooks;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BooksServicesTest {
	
	MockBooks input;
	
	@InjectMocks
	private BookServices service;
	
	@Mock
	BookRepository repository;
	
	@Mock
	BookMapper mapper;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBooks();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/books/v1>;rel=\"collection\"]"));
		assertEquals("Some Author 1", result.getAuthor());
		assertEquals("Some Title 1", result.getTitle());
		assertEquals(10D, result.getPrice());
		assertNotNull(result.getLaunchDate());
	}
	
	@Test
	void testFindAll() {
		List<Book> mockList = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(mockList);
		
		var result = service.findAll();
		
		assertNotNull(result);
		assertEquals(14, result.size());
		
		var item1 = result.get(1);
		
		assertNotNull(item1);
		assertNotNull(item1.getKey());
		assertNotNull(item1.getLinks());
		
		System.out.println(item1.toString());
		assertTrue(item1.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author 1", item1.getAuthor());
		assertEquals("Some Title 1", item1.getTitle());
		assertEquals(10D, item1.getPrice());
		assertNotNull(item1.getLaunchDate());	


		var item4 = result.get(4);
		
		assertNotNull(item4);
		assertNotNull(item4.getKey());
		assertNotNull(item4.getLinks());
		
		System.out.println(item4.toString());
		assertTrue(item4.toString().contains("links: [</api/books/v1/4>;rel=\"self\"]"));
		assertEquals("Some Author 4", item4.getAuthor());
		assertEquals("Some Title 4", item4.getTitle());
		assertEquals(40D, item4.getPrice());
		assertNotNull(item4.getLaunchDate());	
		
		var item7 = result.get(7);
		
		assertNotNull(item7);
		assertNotNull(item7.getKey());
		assertNotNull(item7.getLinks());
		
		System.out.println(item7.toString());
		assertTrue(item7.toString().contains("links: [</api/books/v1/7>;rel=\"self\"]"));
		assertEquals("Some Author 7", item7.getAuthor());
		assertEquals("Some Title 7", item7.getTitle());
		assertEquals(70D, item7.getPrice());
		assertNotNull(item7.getLaunchDate());	
		
	}

	@Test
	void testCreate() {
		Book persisted = input.mockEntity(1);
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		
		when(repository.save(any(Book.class))).thenReturn(persisted);
		
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author 1", result.getAuthor());
		assertEquals("Some Title 1", result.getTitle());
		assertEquals(10D, result.getPrice());
		assertNotNull(result.getLaunchDate());
	}

	@Test
	void testCreateWithNullBooks() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author 1", result.getAuthor());
		assertEquals("Some Title 1", result.getTitle());
		assertEquals(10D, result.getPrice());
		assertNotNull(result.getLaunchDate());
	}
	
	@Test
	void testUpdateWithNullBooks() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}	

	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	}

}
