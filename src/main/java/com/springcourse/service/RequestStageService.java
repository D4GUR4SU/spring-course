package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.RequestStage;
import com.springcourse.enums.RequestState;
import com.springcourse.repository.RequestRepository;
import com.springcourse.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());
		
		RequestStage createdStage = requestStageRepository.save(stage);
		
		Long requestId = createdStage.getRequest().getId();
		RequestState state = stage.getState();
		requestRepository.updateStatus(requestId, state);
		
		return stage;
	}
	
	public RequestStage getById(Long id) {		
		Optional<RequestStage> result = requestStageRepository.findById(id);
		
		return result.get();
	}
	
	public List<RequestStage> listAllByRequestId(Long requestId) {		
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(requestId);
		
		return stages;
	}
}
