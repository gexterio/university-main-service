package ua.com.foxminded.university.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.university.consumer.dto.StudentDTO;
import ua.com.foxminded.university.consumer.service.StudentService;


@RestController
@RequestMapping("/api/v1/students")
public class StudentRestController {

    private final StudentService service;

    @Autowired
    public StudentRestController(StudentService service) {
        this.service = service;
    }

    @Operation(summary = "Get operation for all Students",
            description = "Get operation for all Students as pageable list",
            parameters = {
                    @Parameter(name = "pageable", description = "pageable object")},
            responses = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}, description = "Students received successfully")
            })
    @GetMapping()
    public Page<StudentDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(summary = "Get operation for Student by id",
            description = "Get operation for Student by id",
            parameters = {
                    @Parameter(name = "id", description = "id of the Student", example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student received successfully", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Student not found", content = {@Content(mediaType = "application/json")})
            })
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Add operation for Student by id",
            description = "Adding Student into database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.
                    RequestBody(description = "Student for adding"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Student added successfully", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400", description = "Student don't added. Student not valid or already exists", content = {@Content(mediaType = "application/json")})
            })
    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody @Validated StudentDTO student) {
        return new ResponseEntity<>(service.create(student), HttpStatus.CREATED);
    }

    @Operation(summary = "Update operation for Student by id",
            description = "Updating Student in database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.
                    RequestBody(description = "Student for updating"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Student updated successfully", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Student don't Updated. Student not valid or not found", content = {@Content(mediaType = "application/json")})
            })
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@RequestBody @Validated StudentDTO student) {
        return new ResponseEntity<>(service.update(student), HttpStatus.OK);
    }

    @Operation(summary = "Delete operation for Student by id",
            description = "Delete operation for Student from database by id",
            parameters = {
                    @Parameter(name = "id", description = "id of the Student", example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Student deleted successfully", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Student can't be deleted. Student found", content = {@Content(mediaType = "application/json")})
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
    }
}

