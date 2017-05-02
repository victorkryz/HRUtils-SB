package victor.kryz.hrutils.generated.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class HrUtilsDepartmentsEntryT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.DEPARTMENTS_ENTRY_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2002,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[2];
  static
  {
    _factory[0] = DepartmentDescrT.getORADataFactory();
  }
  protected static final HrUtilsDepartmentsEntryT _HrUtilsDepartmentsEntryTFactory = new HrUtilsDepartmentsEntryT();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsDepartmentsEntryTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[2], _sqlType, _factory); }
  public HrUtilsDepartmentsEntryT()
  { _init_struct(true); }
  public HrUtilsDepartmentsEntryT(DepartmentDescrT department, java.math.BigDecimal managerId) throws SQLException
  { _init_struct(true);
    setDepartment(department);
    setManagerId(managerId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(HrUtilsDepartmentsEntryT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new HrUtilsDepartmentsEntryT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public DepartmentDescrT getDepartment() throws SQLException
  { return (DepartmentDescrT) _struct.getAttribute(0); }

  public void setDepartment(DepartmentDescrT department) throws SQLException
  { _struct.setAttribute(0, department); }


  public java.math.BigDecimal getManagerId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setManagerId(java.math.BigDecimal managerId) throws SQLException
  { _struct.setAttribute(1, managerId); }

}
