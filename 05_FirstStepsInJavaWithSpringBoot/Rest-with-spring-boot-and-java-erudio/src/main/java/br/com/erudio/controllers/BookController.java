package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.BookServices;
import br.com.erudio.util.ErrorCode;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Endpoints for Managing Book")
public class BookController {

	@Autowired
	private BookServices service;

	@GetMapping(
			value = "/v1", 
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all books", description="Finds all books.", 
		tags= {"Books"},
		responses= {
			@ApiResponse(description = "Success", responseCode = "200", 
				content= {
					@Content(
							mediaType = "applcation/json",
							array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
					)
				}
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content),
		})
	public List<BookVO> findAll() {
		return service.findAll();
	}	
	
	
	@GetMapping(
			value="/v1/{id}",
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a book", description="Finds a book.", 
	tags= {"Books"},
	responses= {
		@ApiResponse(description = "Success", responseCode = "200", 
			content= @Content(schema = @Schema(implementation = PersonVO.class))
		),
		@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public ResponseEntity<?> findById(@PathVariable(value = "id") Long id)	{
		//return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
		BookVO bookVO = service.findById(id);
		
	    if (bookVO != null) {
	        // Se o objeto foi encontrado, retorna-o juntamente com o status OK (200)
	        return ResponseEntity.ok(bookVO);
	    } else {
	        // Se o objeto não foi encontrado, cria e retorna um objeto de erro juntamente com o status NOT FOUND (404)
	        ErrorCode errorObject = new ErrorCode("Book not found", HttpStatus.NOT_FOUND.value());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorObject);
	    }		
	}	
	
	@PostMapping(
			value = "/v1", 
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Add a new book", 
			description="Add a new book by passing in a JSON, XML or YML representation of the person", 
	tags= {"Books"},
	responses= {
		@ApiResponse(description = "Success", responseCode = "200", 
			content= @Content(schema = @Schema(implementation = PersonVO.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public ResponseEntity<BookVO> create(@RequestBody BookVO book)
	{
		BookVO createdBook = service.create(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
	}
	
	@PutMapping(
			value = "/v1",
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a book", description="Updates a book.", 
	tags= {"Books"},
	responses= {
		@ApiResponse(description = "Updated", responseCode = "200", 
			content= @Content(schema = @Schema(implementation = PersonVO.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public BookVO update(@RequestBody BookVO book)
	{
		return service.update(book);
	}

	@DeleteMapping(
			value="v1/{id}")
	@Operation(summary = "Deletes a book", description="Delete a book.", 
	tags= {"Books"},
	responses= {
		@ApiResponse(description = "No Content", responseCode = "204", content= @Content ),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}


