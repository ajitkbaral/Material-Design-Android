package com.pagodalabs.materialdesign.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

public class Profession implements Serializable, Parcelable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer categoryId;
	private String jobName;
    private String urlImage;
	
	public Profession() {
	}

    public Profession(Parcel input) {
        categoryId = input.readInt();
        jobName = input.readString();
        urlImage = input.readString();

    }


    public Profession(Integer categoryId, String jobName, String urlImage) {
		
		this.categoryId = categoryId;
		this.jobName = jobName;
        this.urlImage = urlImage;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getJobName() {
		return jobName;
	}
	
	public void setJobType(String jobName) {
		this.jobName = jobName;
	}
        
        public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}


	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", jobName=" + jobName
				+ ", urlImage=" + urlImage + "]";
	}


    @Override
    public int describeContents() {
        Log.d("DESCRIBE CONTENT", "describe content");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(categoryId);
        dest.writeString(jobName);
        dest.writeString(urlImage);

    }

    public static final Creator<Profession> CREATOR = new ClassLoaderCreator<Profession>() {

        @Override
        public Profession createFromParcel(Parcel source) {
            return new Profession(source);
        }

        @Override
        public Profession[] newArray(int size) {
            return new Profession[size];
        }

        @Override
        public Profession createFromParcel(Parcel source, ClassLoader loader) {

            return null;
        }
    };
}
