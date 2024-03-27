package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin
@RestController
@RequestMapping("/api/person")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
	@GetMapping(
			value = "/v1", 
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all people", description="Finds all people.", 
		tags= {"People"},
		responses= {
			@ApiResponse(description = "Success", responseCode = "200", 
				content= {
					@Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
					)
				}
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content),
		})
	public List<PersonVO> findAll() {
		return service.findAll();
	}	
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(
			value="/v1/{id}",
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a person", description="Finds a person.", 
	tags= {"People"},
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
	public PersonVO findById(@PathVariable(value = "id") Long id)	{
		return service.findById(id);
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
	@PostMapping(
			value = "/v1", 
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Add a new Person", 
			description="Add a new Person by passing in a JSON, XML or YML representation of the person", 
	tags= {"People"},
	responses= {
		@ApiResponse(description = "Success", responseCode = "200", 
			content= @Content(schema = @Schema(implementation = PersonVO.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public ResponseEntity<PersonVO> create(@RequestBody PersonVO person)
	{
		PersonVO createdPerson = service.create(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
	}
	
	@PostMapping(
			value = "/v2", 
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Creates a person V2", description="Creates a person V2.", 
	tags= {"People"},
	responses= {
		@ApiResponse(description = "Success", responseCode = "200", 
			content= @Content(schema = @Schema(implementation = PersonVO.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public ResponseEntity<PersonVOV2> createV2(@RequestBody PersonVOV2 person)
	{
		PersonVOV2 createdPerson = service.createV2(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
	}	
	
	@PutMapping(
			value = "/v1",
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a person", description="Updates a person.", 
	tags= {"People"},
	responses= {
		@ApiResponse(description = "Updated", responseCode = "200", 
			content= @Content(schema = @Schema(implementation = PersonVO.class))
		),
		@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
		@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
		@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
		@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public PersonVO update(@RequestBody PersonVO person)
	{
		return service.update(person);
	}

	@DeleteMapping(
			value="v1/{id}")
	@Operation(summary = "Deletes a person", description="Delete a person.", 
	tags= {"People"},
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
