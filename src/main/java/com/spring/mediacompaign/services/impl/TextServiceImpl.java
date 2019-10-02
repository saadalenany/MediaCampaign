package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.TextEntity;
import com.spring.mediacompaign.dao.models.TextModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.repos.TextRepository;
import com.spring.mediacompaign.mappers.TextMapper;
import com.spring.mediacompaign.services.api.TextService;
import com.spring.mediacompaign.services.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TextServiceImpl implements TextService, GeneralValidator<TextModel> {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private TextMapper textMapper;

    @Override
    public VersionedModel save(TextModel textModel) {
        final VersionedModel posted = post(textModel);
        if (posted != null) {
            return posted;
        }
        TextEntity saved = textRepository.save(textMapper.toEntity(textModel));
        return textMapper.toModel(saved);
    }

    @Override
    public VersionedModel update(TextModel textModel) {
        final VersionedModel putted = put(textModel);
        if (putted != null) {
            return putted;
        }
        TextEntity saved = textRepository.save(textMapper.toEntity(textModel));
        return textMapper.toModel(saved);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
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
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<TextModel> list() {
        List<TextEntity> all = textRepository.findAll();
        return textMapper.toModels(all);
    }
}
