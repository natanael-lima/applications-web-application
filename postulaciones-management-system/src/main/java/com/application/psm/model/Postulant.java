package com.application.psm.model;

import jakarta.persistence.*;

@Entity
@Table(name="postulant")
public class Postulant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
   
	@Column(name = "age")
	private Integer age;
	@Column(name = "phone", nullable=false)
	private Integer phone;
	@Column(name = "studies")
	private String studies;
	@Column(name = "address")
	private String address;
	
	// Cambiamos el tipo de datos de curriculum a byte[]
    @Lob
    private byte[] curriculum;
    
    @Column(name = "aditional")
	private String aditional;
    
    @ManyToOne
   	@JoinColumn(name = "user_id")
   	private User user;
   	
   	@ManyToOne
      @JoinColumn(name = "job_offer_id")
   	private JobOffer jobOffer;
   	
    
	public Postulant() {
		super();
	}

	public Postulant(User user, JobOffer jobOffer, Integer age, Integer phone, String studies, String address,
			byte[] curriculum, String aditional) {
		super();
		this.user = user;
		this.jobOffer = jobOffer;
		this.age = age;
		this.phone = phone;
		this.studies = studies;
		this.address = address;
		this.curriculum = curriculum;
		this.aditional = aditional;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getStudies() {
		return studies;
	}

	public void setStudies(String studies) {
		this.studies = studies;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(byte[] curriculum) {
		this.curriculum = curriculum;
	}

	public String getAditional() {
		return aditional;
	}

	public void setAditional(String aditional) {
		this.aditional = aditional;
	}
	
	
	
	
	
}
