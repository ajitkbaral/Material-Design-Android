package com.pagodalabs.materialdesign.entities;

import java.io.Serializable;

public class Job implements Serializable{
	   private Integer jobId;
	   private Integer jobSeekerId;
	   private String jobName;
	   private String jobDescription;
	   private Integer estimatedAmount;
	   private String postUrl;

	    public Job() {
	    }

	    public Job(Integer jobId, Integer jobSeekerId, String jobName, String jobDescription, Integer estimatedAmount, String postUrl) {
	        this.jobId = jobId;
	        this.jobSeekerId = jobSeekerId;
	        this.jobName = jobName;
	        this.jobDescription = jobDescription;
	        this.estimatedAmount = estimatedAmount;
	        this.postUrl = postUrl;
	    }

	    public Integer getJobId() {
	        return jobId;
	    }

	    public void setJobId(Integer jobId) {
	        this.jobId = jobId;
	    }

	    public Integer getJobSeekerId() {
	        return jobSeekerId;
	    }

	    public void setJobSeekerId(Integer jobSeekerId) {
	        this.jobSeekerId = jobSeekerId;
	    }

	    public String getJobName() {
	        return jobName;
	    }

	    public void setJobName(String jobName) {
	        this.jobName = jobName;
	    }

	    public String getJobDescription() {
	        return jobDescription;
	    }

	    public void setJobDescription(String jobDescription) {
	        this.jobDescription = jobDescription;
	    }

	    public Integer getEstimatedAmount() {
	        return estimatedAmount;
	    }

	    public void setEstimatedAmount(Integer estimatedAmount) {
	        this.estimatedAmount = estimatedAmount;
	    }

	    public String getPostUrl() {
	        return postUrl;
	    }

	    public void setPostUrl(String postUrl) {
	        this.postUrl = postUrl;
	    }

	    @Override
	    public String toString() {
	        return "Job{" + "jobId=" + jobId + ", jobSeekerId=" + jobSeekerId + ", jobName=" + jobName + ", estimatedAmount=" + estimatedAmount + ", postUrl=" + postUrl + '}';
	    }
	         
	    
	

}
