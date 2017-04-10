package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class HrUtilsEmployeeComposite20 implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.EMPLOYEE_COMPOSITE20";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 12,2002,2002,12,2,12,12,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[9];
  static
  {
    _factory[1] = EmployeeDescrT.getORADataFactory();
    _factory[2] = DepartmentDescrT.getORADataFactory();
  }
  protected static final HrUtilsEmployeeComposite20 _HrUtilsEmployeeComposite20Factory = new HrUtilsEmployeeComposite20();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsEmployeeComposite20Factory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory); }
  public HrUtilsEmployeeComposite20()
  { _init_struct(true); }
  public HrUtilsEmployeeComposite20(String emplDecoratedName, EmployeeDescrT emplDescr, DepartmentDescrT depDescr, String jobTitle, java.math.BigDecimal isManager, String countryName, String stateProvince, java.math.BigDecimal regionId, String regionName) throws SQLException
  { _init_struct(true);
    setEmplDecoratedName(emplDecoratedName);
    setEmplDescr(emplDescr);
    setDepDescr(depDescr);
    setJobTitle(jobTitle);
    setIsManager(isManager);
    setCountryName(countryName);
    setStateProvince(stateProvince);
    setRegionId(regionId);
    setRegionName(regionName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(HrUtilsEmployeeComposite20 o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new HrUtilsEmployeeComposite20();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getEmplDecoratedName() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setEmplDecoratedName(String emplDecoratedName) throws SQLException
  { _struct.setAttribute(0, emplDecoratedName); }


  public EmployeeDescrT getEmplDescr() throws SQLException
  { return (EmployeeDescrT) _struct.getAttribute(1); }

  public void setEmplDescr(EmployeeDescrT emplDescr) throws SQLException
  { _struct.setAttribute(1, emplDescr); }


  public DepartmentDescrT getDepDescr() throws SQLException
  { return (DepartmentDescrT) _struct.getAttribute(2); }

  public void setDepDescr(DepartmentDescrT depDescr) throws SQLException
  { _struct.setAttribute(2, depDescr); }


  public String getJobTitle() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setJobTitle(String jobTitle) throws SQLException
  { _struct.setAttribute(3, jobTitle); }


  public java.math.BigDecimal getIsManager() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setIsManager(java.math.BigDecimal isManager) throws SQLException
  { _struct.setAttribute(4, isManager); }


  public String getCountryName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setCountryName(String countryName) throws SQLException
  { _struct.setAttribute(5, countryName); }


  public String getStateProvince() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setStateProvince(String stateProvince) throws SQLException
  { _struct.setAttribute(6, stateProvince); }


  public java.math.BigDecimal getRegionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setRegionId(java.math.BigDecimal regionId) throws SQLException
  { _struct.setAttribute(7, regionId); }


  public String getRegionName() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setRegionName(String regionName) throws SQLException
  { _struct.setAttribute(8, regionName); }

}
