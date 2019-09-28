package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.TextEntity;
import com.spring.mediacompaign.dao.models.TextModel;
import com.spring.mediacompaign.dao.repos.TextRepository;
import com.spring.mediacompaign.mappers.TextMapper;
import com.spring.mediacompaign.services.api.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private TextMapper textMapper;

    @Override
    public TextModel save(TextModel textModel) {
        TextEntity saved = textRepository.save(textMapper.toEntity(textModel));
        return textMapper.toModel(saved);
    }

    @Override
    public TextModel update(TextModel textModel) {
        TextEntity saved = textRepository.save(textMapper.toEntity(textModel));
        return textMapper.toModel(saved);
    }

    @Override
    public TextModel getById(String id) {
        TextEntity textEntity = textRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No Text found with this id [%s]", id)));
        return textMapper.toModel(textEntity);
    }

    @Override
    public Boolean delete(String id) {
        textRepository.deleteById(id);
        return !textRepository.findById(id).isPresent();
    }

    @Override
    public List<TextModel> list() {
        List<TextEntity> all = textRepository.findAll();
        return textMapper.toModels(all);
    }
}
