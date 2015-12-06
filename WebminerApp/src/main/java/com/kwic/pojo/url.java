package com.kwic.pojo;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class url {
private String url;
private String alphabetized;
private Timestamp dateadded;
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getAlphabetized() {
	return alphabetized;
}
public void setAlphabetized(String alphabetized) {
	this.alphabetized = alphabetized;
}

public Timestamp getDateadded() {
	return dateadded;
}
public void setDateadded(Timestamp dateadded) {
	this.dateadded = dateadded;
}
@Override
public String toString() {
	return "url [url=" + url + ", alphabetized=" + alphabetized + ", payment="
			+ dateadded + "]";
}

}
