package ua.com.foxminded.university.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.university.consumer.dto.TeacherDTO;
import ua.com.foxminded.university.consumer.exception.TeacherNotFoundException;
import ua.com.foxminded.university.persistance.repository.TeacherRepository;
import ua.com.foxminded.university.consumer.exception.TeacherAlreadyExistException;
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

    public TeacherDTO findById(Long id) throws TeacherNotFoundException {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @Transactional
    public TeacherDTO create(TeacherDTO dto) throws TeacherAlreadyExistException {
        Long id = dto.getId();
        if (id != null && repository.findById(id).isPresent()) {
            throw new TeacherAlreadyExistException(id);
        }
        return Optional.of(dto)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public TeacherDTO update(TeacherDTO student) throws TeacherNotFoundException {
        return repository.findById(student.getId())
                .map(entity -> mapper.toEntity(student))
                .map(repository::saveAndFlush)
                .map(mapper::toDto)
                .orElseThrow(() -> new TeacherNotFoundException(student.getId()));
    }

    public boolean delete(Long id) throws TeacherNotFoundException {
        return repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    repository.flush();
                    return true;
                })
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }


}
