package com.hjt.learningdemo.impl;

import com.hjt.learningdemo.model.AyUser;
import com.hjt.learningdemo.repository.AyUserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import service.AyUserService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class AyUserServiceImpl implements AyUserService {

    @Resource
    private AyUserRepository ayUserRepository;

    @Override
    public AyUser findById(String id) {
        AyUser ayuser = new AyUser();
        ayuser.setId(id);
        Example<AyUser> userExample = Example.of(ayuser);
        return ayUserRepository.findOne(userExample).orElse(null);
    }

    @Override
    public List<AyUser> findAll() {
        return ayUserRepository.findAll();
    }

    @Override
    public AyUser save(AyUser ayUser) {
        return ayUserRepository.save(ayUser);
    }

    @Override
    public void delete(String id) {
        ayUserRepository.deleteById(id);
    }

    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll(pageable);
    }

    @Override
    public List<AyUser> findByName(String name) {
        return ayUserRepository.findByName(name);
    }

    @Override
    public List<AyUser> findByNameLike(String name) {
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(Collection<String> ids) {
        return ayUserRepository.findByIdIn(ids);
    }
}
