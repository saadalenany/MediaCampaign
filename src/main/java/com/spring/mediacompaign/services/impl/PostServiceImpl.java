package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.PostEntity;
import com.spring.mediacompaign.dao.models.PostModel;
import com.spring.mediacompaign.dao.repos.PostRepository;
import com.spring.mediacompaign.mappers.PostMapper;
import com.spring.mediacompaign.services.api.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Override
    public PostModel save(PostModel postModel) {
        PostEntity postEntity = postRepository.save(postMapper.toEntity(postModel));
        return postMapper.toModel(postEntity);
    }

    @Override
    public PostModel update(PostModel postModel) {
        PostEntity postEntity = postRepository.save(postMapper.toEntity(postModel));
        return postMapper.toModel(postEntity);
    }

    @Override
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
    public List<PostModel> list() {
        List<PostEntity> all = postRepository.findAll();
        return postMapper.toModels(all);
    }
}
