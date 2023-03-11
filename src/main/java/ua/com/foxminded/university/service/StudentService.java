package ua.com.foxminded.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.consumer.dto.StudentDTO;
import ua.com.foxminded.university.persistance.repository.StudentRepository;
import ua.com.foxminded.university.util.modelmapper.StudentMapper;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Autowired
    public StudentService(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<StudentDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    public StudentDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("student with id = " + id + " not found"));
    }

    @Transactional
    public StudentDTO create(StudentDTO dto) {
        return Optional.of(dto)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public StudentDTO update(StudentDTO student) {
        return repository.findById(student.getId())
                .map(entity -> mapper.toEntity(student))
                .map(repository::saveAndFlush)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("student with id = " + student.getId() + " not found"));
    }

    public boolean delete(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    repository.flush();
                    return true;
                })
                .orElse(false);
    }
}
