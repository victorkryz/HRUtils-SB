package victor.kryz.hr.sb.ents;

import java.math.BigDecimal;

public class EmployeeBriefEntryT {
	
	BigDecimal emplId; 
	String firstName;
	String lastName;
	BigDecimal depId;
	
	public EmployeeBriefEntryT() {
	}
	
	public EmployeeBriefEntryT(BigDecimal emplId, String strFirstName, String strLastName, BigDecimal _depId) {
		super();
		this.emplId = emplId;
		this.firstName = strFirstName;
		this.lastName = strLastName;
		this.depId = _depId;
	}

	public BigDecimal getEmplId() {
		return emplId;
	}

	public void setEmplId(BigDecimal emplId) {
		this.emplId = emplId;
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

	public BigDecimal getDepId() {
		return depId;
	}

	public void setDepId(BigDecimal depId) {
		this.depId = depId;
	}
}
