package com.springcourse.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.springcourse.enums.RequestState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "request")
public class Request implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false)
	private String subject;
	
	@Column(columnDefinition = "text")
	private String description;

	@Column(name = "creation_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;
	
	@Column(length = 12, nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestState state;
	
	private List<RequestStage> stages;
	
}
