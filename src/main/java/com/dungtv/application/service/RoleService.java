package com.dungtv.application.service;

import com.dungtv.application.domain.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService
{
    Role getRoleByCode(final String code);
}