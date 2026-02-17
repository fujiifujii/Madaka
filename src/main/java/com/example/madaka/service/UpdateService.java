package com.example.madaka.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.madaka.form.UpdateForm;
import com.example.madaka.mapper.UpdateMapper;
import com.example.madaka.repository.RegisterRepository;

@Service
public class UpdateService {
	private static final DateTimeFormatter SLASH_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static final DateTimeFormatter HYPHEN_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private final UpdateMapper updateMapper;

	public UpdateService(UpdateMapper updateMapper) {
		this.updateMapper = updateMapper;
	}

	public RegisterRepository findByLateId(String lateId) {
		return updateMapper.selectByLateId(lateId);
	}

	public void update(UpdateForm form) {
		RegisterRepository repository = new RegisterRepository();
		LocalDate registerDate = parseDate(form.getDate());
		LocalTime startTime = form.getStartTime() != null ? form.getStartTime() : LocalTime.of(9, 0);
		repository.setLateId(form.getLateId());
		repository.setEmpId(form.getEmpId());
		repository.setLateDatetime(LocalDateTime.of(registerDate, startTime));
		repository.setLateReason(form.getLateReason());
		repository.setTrainDelayMin(form.getTrainDelayMinutes());
		repository.setTrainId(form.getTrainId());
		repository.setStartTime(startTime);
		repository.setUpdateDatetime(LocalDateTime.now());
		repository.setNote(form.getNote());

		updateMapper.updateByLateId(repository);
	}

	private LocalDate parseDate(String dateText) {
		if (dateText == null || dateText.isBlank()) {
			return LocalDate.now();
		}
		try {
			return LocalDate.parse(dateText, SLASH_DATE_FORMATTER);
		} catch (Exception e) {
			return LocalDate.parse(dateText, HYPHEN_DATE_FORMATTER);
		}
	}
}
