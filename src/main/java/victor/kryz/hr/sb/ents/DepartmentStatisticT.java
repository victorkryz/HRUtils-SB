/**
 * HRUtils-SB
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.ents;

import java.math.BigDecimal;

import victor.kryz.hrutils.generated.ents.DepartmentDescrT;

public class DepartmentStatisticT {
	
	DepartmentDescrT deptDescr;
	Long numOfEmpls;
	BigDecimal salTotal;
	BigDecimal salMax;
	BigDecimal salMin;
	BigDecimal salAvg;
	
	public DepartmentStatisticT(DepartmentDescrT deptDescr, Long numOfEmpls, BigDecimal salTotal, 
								BigDecimal salMax, BigDecimal salMin, BigDecimal salAvg) {
		super();
		this.deptDescr = deptDescr;
		this.numOfEmpls = numOfEmpls;
		this.salTotal = salTotal;
		this.salMax = salMax;
		this.salMin = salMin;
		this.salAvg = salAvg;
	}

	public DepartmentDescrT getDeptDescr() {
		return deptDescr;
	}

	public void setDeptDescr(DepartmentDescrT deptDescr) {
		this.deptDescr = deptDescr;
	}

	public Long getNumOfEmpls() {
		return numOfEmpls;
	}

	public void setNumOfEmpls(Long numOfEmpls) {
		this.numOfEmpls = numOfEmpls;
	}

	public BigDecimal getSalMax() {
		return salMax;
	}

	public void setSalMax(BigDecimal salMax) {
		this.salMax = salMax;
	}

	public BigDecimal getSalMin() {
		return salMin;
	}

	public void setSalMin(BigDecimal salMin) {
		this.salMin = salMin;
	}

	public BigDecimal getSalAvg() {
		return salAvg;
	}

	public void setSalAvg(BigDecimal salAvg) {
		this.salAvg = salAvg;
	}

	public BigDecimal getSalTotal() {
		return salTotal;
	}

	public void setSalTotal(BigDecimal salTotal) {
		this.salTotal = salTotal;
	}
	

}
