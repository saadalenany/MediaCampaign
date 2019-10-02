package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.PostEntity;
import com.spring.mediacompaign.dao.models.PostModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.repos.PostRepository;
import com.spring.mediacompaign.mappers.PostMapper;
import com.spring.mediacompaign.services.api.PostService;
import com.spring.mediacompaign.services.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService, GeneralValidator<PostModel> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Override
    public VersionedModel save(PostModel postModel) {
        final VersionedModel posted = post(postModel);
        if (posted != null) {
            return posted;
        }
        PostEntity postEntity = postRepository.save(postMapper.toEntity(postModel));
        return postMapper.toModel(postEntity);
    }

    @Override
    public VersionedModel update(PostModel postModel) {
        final VersionedModel putted = put(postModel);
        if (putted != null) {
            return putted;
        }
        PostEntity postEntity = postRepository.save(postMapper.toEntity(postModel));
        return postMapper.toModel(postEntity);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public PostModel getById(String id) {
        PostEntity postEntity = postRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No Post found with this id [%s]", id)));
        return postMapper.toModel(postEntity);
    }

    @Override
    public Boolean delete(String id) {
        postRepository.deleteById(id);
        return !postRepository.findById(id).isPresent();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<PostModel> list() {
        List<PostEntity> all = postRepository.findAll();
        return postMapper.toModels(all);
    }
}
