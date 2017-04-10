package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class DepartmentDescrTRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "DEPARTMENT_DESCR_T";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final DepartmentDescrTRef _DepartmentDescrTRefFactory = new DepartmentDescrTRef();

  public static ORADataFactory getORADataFactory()
  { return _DepartmentDescrTRefFactory; }
  /* constructor */
  public DepartmentDescrTRef()
  {
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _ref;
  }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    DepartmentDescrTRef r = new DepartmentDescrTRef();
    r._ref = (REF) d;
    return r;
  }

  public static DepartmentDescrTRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (DepartmentDescrTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to DepartmentDescrTRef: "+exn.toString()); }
  }

  public DepartmentDescrT getValue() throws SQLException
  {
     return (DepartmentDescrT) DepartmentDescrT.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(DepartmentDescrT c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
