package com.example.madaka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.madaka.form.LoginForm;
import com.example.madaka.mapper.LoginMapper;
import com.example.madaka.repository.DepartmentMaster;
import com.example.madaka.repository.TeamMaster;
import com.example.madaka.repository.TrainMaster;
import com.example.madaka.response.LoginResponse;

@Service
public class LoginService {

	 private static LoginMapper loginMapper;

	    public LoginService(LoginMapper loginMapper) {
	        this.loginMapper = loginMapper;
	    }

	    public LoginResponse login(LoginForm form) {
	        return loginMapper.login(form);
	    }

	    public List<DepartmentMaster> getDepartmentMaster() {
	        return loginMapper.getDepartmentMaster();
	    }

	    public List<TeamMaster> getTeamMaster() {
	        return loginMapper.getTeamMaster();
	    }

	    public List<TrainMaster> getTrainMaster() {
	        return loginMapper.getTrainMaster();
	    }

}
