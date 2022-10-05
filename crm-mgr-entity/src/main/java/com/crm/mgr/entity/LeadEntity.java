package com.crm.mgr.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="lead")
public class LeadEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(columnDefinition = "uuid", name = "id", updatable = false, nullable = false)
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="title")
	private String title;

	@Column(name="email")
	private String email;

	@Column(name="phone")
	private String phone;

	@Column(name="industry")
	private String industry;

	@Column(name="budget")
	private BigDecimal budget;

	@Column(name="company")
	private String company;

	@Column(name="linkedin_profile")
	private String linkedInProfile;

	@Lob
	@Column(name="background_info")
	private String backgroundInfo;

	@Column(name="rating")
	private BigDecimal rating;

	@Temporal(TemporalType.DATE)
	@Column(name="proposal_due_date")
	private Date proposalDueDate;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_initial_lead")
	private Date dateOfInitialLead;

	@ManyToOne
	@JoinColumn(name="sales_rep_id")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name="status_id")
	private LeadStatusEntity leadStatus;

	@OneToMany(mappedBy="lead", fetch = FetchType.EAGER)
	private List<TaskEntity> tasks;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressEntity address;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLinkedInProfile() {
		return linkedInProfile;
	}

	public void setLinkedInProfile(String linkedInProfile) {
		this.linkedInProfile = linkedInProfile;
	}

	public String getBackgroundInfo() {
		return backgroundInfo;
	}

	public void setBackgroundInfo(String backgroundInfo) {
		this.backgroundInfo = backgroundInfo;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public Date getProposalDueDate() {
		return proposalDueDate;
	}

	public void setProposalDueDate(Date proposalDueDate) {
		this.proposalDueDate = proposalDueDate;
	}

	public Date getDateOfInitialLead() {
		return dateOfInitialLead;
	}

	public void setDateOfInitialLead(Date dateOfInitialLead) {
		this.dateOfInitialLead = dateOfInitialLead;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public LeadStatusEntity getLeadStatus() {
		return leadStatus;
	}

	public void setLeadStatus(LeadStatusEntity leadStatus) {
		this.leadStatus = leadStatus;
	}

	public List<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskEntity> tasks) {
		this.tasks = tasks;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "LeadEntity{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", middleName='" + middleName + '\'' +
				", title='" + title + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", industry='" + industry + '\'' +
				", budget=" + budget +
				", company='" + company + '\'' +
				", linkedInProfile='" + linkedInProfile + '\'' +
				", backgroundInfo='" + backgroundInfo + '\'' +
				", rating=" + rating +
				", proposalDueDate=" + proposalDueDate +
				", dateOfInitialLead=" + dateOfInitialLead +
				'}';
	}
}