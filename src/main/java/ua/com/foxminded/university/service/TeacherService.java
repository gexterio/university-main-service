package ua.com.foxminded.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.persistance.repository.TeacherRepository;
import ua.com.foxminded.university.util.modelmapper.TeacherMapper;

import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository repository;
    private final TeacherMapper mapper;

    @Autowired
    public TeacherService(TeacherRepository repository, TeacherMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<TeacherDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    public Optional<TeacherDTO> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Transactional
    public TeacherDTO create(TeacherDTO dto) {
        return Optional.of(dto)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<TeacherDTO> update(Long id, TeacherDTO student) {
        return repository.findById(id)
                .map(entity -> mapper.toEntity(student))
                .map(repository::saveAndFlush)
                .map(mapper::toDto);
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
