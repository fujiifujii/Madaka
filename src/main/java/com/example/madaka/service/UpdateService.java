package com.example.madaka.service;

import org.springframework.stereotype.Service;

import com.example.madaka.mapper.UpdateMapper;
import com.example.madaka.repository.RegisterRepository;

@Service
public class UpdateService {

	private final UpdateMapper updateMapper;

	public UpdateService(UpdateMapper updateMapper) {
		this.updateMapper = updateMapper;
	}

	public RegisterRepository findByLateId(String lateId) {
		return updateMapper.selectByLateId(lateId);
	}
}
