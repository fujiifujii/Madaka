package com.example.madaka.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.madaka.form.LoginForm;
import com.example.madaka.repository.DepartmentMaster;
import com.example.madaka.repository.TeamMaster;
import com.example.madaka.repository.TrainMaster;
import com.example.madaka.response.LoginResponse;


@Mapper
public interface LoginMapper {

	LoginResponse login(LoginForm form);
	List<DepartmentMaster> getDepartmentMaster();
    List<TeamMaster> getTeamMaster();
    List<TrainMaster> getTrainMaster();
}
