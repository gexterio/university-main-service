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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.consumer.service.TeacherService;
import ua.com.foxminded.university.consumer.service.TransactionService;
import ua.com.foxminded.university.presentation.annotation.IsAdminRole;

@RestController
@RequestMapping("/api/v1/teachers")
@Validated
@IsAdminRole
public class TeacherRestController {

    private final TeacherService service;
    private final TransactionService transactionService;

    @Autowired
    public TeacherRestController(TeacherService service, TransactionService transactionService) {
        this.service = service;
        this.transactionService = transactionService;
    }

    @Operation(summary = "Get operation for all Teachers",
            description = "Get operation for all Teachers as pageable list",
            parameters = {
                    @Parameter(name = "pageable", description = "pageable object")},
            responses = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")}, description = "Teacher received successfully")
            })
    @GetMapping()
    public Page<TeacherDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(summary = "Get operation for Teacher by id",
            description = "Get operation for Teacher by id",
            parameters = {
                    @Parameter(name = "id", description = "id of the Teacher", example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Teacher received successfully", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Teacher not found", content = {@Content(mediaType = "application/json")})
            })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @teacherPersonalInfoSecurityChecker.checkTeacherId(authentication,#id)")
    public ResponseEntity<TeacherDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Add operation for Teacher by id",
            description = "Adding Student into database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.
                    RequestBody(description = "Teacher for adding"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Teacher added successfully", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400", description = "Teacher don't added. Student not valid or already exists", content = {@Content(mediaType = "application/json")})
            })
    @PostMapping
    public ResponseEntity<TeacherDTO> create(@RequestBody @Validated TeacherDTO teacher) {
        return new ResponseEntity<>(service.create(teacher), HttpStatus.CREATED);
    }

    @Operation(summary = "Update operation for Teacher by id",
            description = "Updating Teacher in database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.
                    RequestBody(description = "Teacher for updating"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Teacher updated successfully", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Teacher don't Updated. Teacher not found", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400", description = "Teacher don't Updated. Teacher not valid", content = {@Content(mediaType = "application/json")})
            })
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> update(@RequestBody @Validated TeacherDTO teacher) {
        return new ResponseEntity<>(service.update(teacher), HttpStatus.OK);
    }

    @Operation(summary = "Teacher operation for Student by id",
            description = "Teacher operation for Student from database by id",
            parameters = {
                    @Parameter(name = "id", description = "id of the Teacher", example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Teacher deleted successfully", content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Teacher can't be deleted. Student found", content = {@Content(mediaType = "application/json")})
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/transactions")
    @PreAuthorize("hasRole('ADMIN') or @teacherPersonalInfoSecurityChecker.checkTeacherId(authentication,#id)")
    public ResponseEntity<Object> getTransactions(@PathVariable("id") Long id) {
        return new ResponseEntity<>(transactionService.getTransactionsForTeacher(id), HttpStatus.OK);
    }
}

