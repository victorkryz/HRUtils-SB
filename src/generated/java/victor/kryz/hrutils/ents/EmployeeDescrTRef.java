package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class EmployeeDescrTRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "EMPLOYEE_DESCR_T";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final EmployeeDescrTRef _EmployeeDescrTRefFactory = new EmployeeDescrTRef();

  public static ORADataFactory getORADataFactory()
  { return _EmployeeDescrTRefFactory; }
  /* constructor */
  public EmployeeDescrTRef()
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
    EmployeeDescrTRef r = new EmployeeDescrTRef();
    r._ref = (REF) d;
    return r;
  }

  public static EmployeeDescrTRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (EmployeeDescrTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to EmployeeDescrTRef: "+exn.toString()); }
  }

  public EmployeeDescrT getValue() throws SQLException
  {
     return (EmployeeDescrT) EmployeeDescrT.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(EmployeeDescrT c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
