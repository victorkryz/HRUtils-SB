package victor.kryz.hrutils.generated.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class EmployeeDescrT extends EmployeeShortDescrT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "EMPLOYEE_DESCR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected static int[] _sqlType =  { 2,12,12,12,12,2,91,12,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[11];
  protected static final EmployeeDescrT _EmployeeDescrTFactory = new EmployeeDescrT();

  public static ORADataFactory getORADataFactory()
  { return _EmployeeDescrTFactory; }
  static
  { _map.put("EMPLOYEE_DESCR_T", _EmployeeDescrTFactory); }

  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[11], _sqlType, _factory); }
  public EmployeeDescrT()
  { _init_struct(true); }
  public EmployeeDescrT(java.math.BigDecimal emplId, String firstName, String lastName, String email, String phone, java.math.BigDecimal depId, java.sql.Timestamp hireDate, String jobId, java.math.BigDecimal commission, java.math.BigDecimal salary, java.math.BigDecimal mngrId) throws SQLException
  { _init_struct(true);
    setEmplId(emplId);
    setFirstName(firstName);
    setLastName(lastName);
    setEmail(email);
    setPhone(phone);
    setDepId(depId);
    setHireDate(hireDate);
    setJobId(jobId);
    setCommission(commission);
    setSalary(salary);
    setMngrId(mngrId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(EmployeeDescrT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new EmployeeDescrT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  protected ORAData createExact(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }

  /* accessor methods */
  public java.sql.Timestamp getHireDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(6); }

  public void setHireDate(java.sql.Timestamp hireDate) throws SQLException
  { _struct.setAttribute(6, hireDate); }


  public String getJobId() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setJobId(String jobId) throws SQLException
  { _struct.setAttribute(7, jobId); }


  public java.math.BigDecimal getCommission() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setCommission(java.math.BigDecimal commission) throws SQLException
  { _struct.setAttribute(8, commission); }


  public java.math.BigDecimal getSalary() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setSalary(java.math.BigDecimal salary) throws SQLException
  { _struct.setAttribute(9, salary); }


  public java.math.BigDecimal getMngrId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setMngrId(java.math.BigDecimal mngrId) throws SQLException
  { _struct.setAttribute(10, mngrId); }

}
