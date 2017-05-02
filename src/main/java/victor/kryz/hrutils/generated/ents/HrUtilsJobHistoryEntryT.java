package victor.kryz.hrutils.generated.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class HrUtilsJobHistoryEntryT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.JOB_HISTORY_ENTRY_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 12,12,91,91,2002 };
  protected static ORADataFactory[] _factory = new ORADataFactory[5];
  static
  {
    _factory[4] = DepartmentDescrT.getORADataFactory();
  }
  protected static final HrUtilsJobHistoryEntryT _HrUtilsJobHistoryEntryTFactory = new HrUtilsJobHistoryEntryT();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsJobHistoryEntryTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[5], _sqlType, _factory); }
  public HrUtilsJobHistoryEntryT()
  { _init_struct(true); }
  public HrUtilsJobHistoryEntryT(String jobId, String jobTitle, java.sql.Timestamp startDate, java.sql.Timestamp endDate, DepartmentDescrT department) throws SQLException
  { _init_struct(true);
    setJobId(jobId);
    setJobTitle(jobTitle);
    setStartDate(startDate);
    setEndDate(endDate);
    setDepartment(department);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(HrUtilsJobHistoryEntryT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new HrUtilsJobHistoryEntryT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getJobId() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setJobId(String jobId) throws SQLException
  { _struct.setAttribute(0, jobId); }


  public String getJobTitle() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setJobTitle(String jobTitle) throws SQLException
  { _struct.setAttribute(1, jobTitle); }


  public java.sql.Timestamp getStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setStartDate(java.sql.Timestamp startDate) throws SQLException
  { _struct.setAttribute(2, startDate); }


  public java.sql.Timestamp getEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setEndDate(java.sql.Timestamp endDate) throws SQLException
  { _struct.setAttribute(3, endDate); }


  public DepartmentDescrT getDepartment() throws SQLException
  { return (DepartmentDescrT) _struct.getAttribute(4); }

  public void setDepartment(DepartmentDescrT department) throws SQLException
  { _struct.setAttribute(4, department); }

}
