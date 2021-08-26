package com.dungtv.application.service.impl;

import com.dungtv.application.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import com.dungtv.application.repository.RoleRepository;
import org.springframework.stereotype.Service;
import com.dungtv.application.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public Role getRoleByCode(final String code) {
        return this.roleRepository.getRoleByCode(code);
    }
}