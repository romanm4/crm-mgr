package com.crm.mgr.dto;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class LeadDto implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String title;
    @Email
    private String email;
    private String phone;
    private String industry;
    private BigDecimal budget;
    private String company;
    private String linkedInProfile;
    private String backgroundInfo;
    private BigDecimal rating;
    private Date proposalDueDate;
    private Date dateOfInitialLead;
    private UUID salesRepId;
    private UUID leadStatusId;

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

    public UUID getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(UUID salesRepId) {
        this.salesRepId = salesRepId;
    }

    public UUID getLeadStatusId() {
        return leadStatusId;
    }

    public void setLeadStatusId(UUID leadStatusId) {
        this.leadStatusId = leadStatusId;
    }

    @Override
    public String toString() {
        return "LeadDto{" +
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
                ", salesRepId=" + salesRepId +
                ", leadStatusId=" + leadStatusId +
                '}';
    }
}
