package victor.kryz.hrutils.generated.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class DepartmentDescrT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "DEPARTMENT_DESCR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[2];
  protected static final DepartmentDescrT _DepartmentDescrTFactory = new DepartmentDescrT();

  public static ORADataFactory getORADataFactory()
  { return _DepartmentDescrTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[2], _sqlType, _factory); }
  public DepartmentDescrT()
  { _init_struct(true); }
  public DepartmentDescrT(java.math.BigDecimal depId, String depName) throws SQLException
  { _init_struct(true);
    setDepId(depId);
    setDepName(depName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(DepartmentDescrT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new DepartmentDescrT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getDepId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setDepId(java.math.BigDecimal depId) throws SQLException
  { _struct.setAttribute(0, depId); }


  public String getDepName() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setDepName(String depName) throws SQLException
  { _struct.setAttribute(1, depName); }

}
