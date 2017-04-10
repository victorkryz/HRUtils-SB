package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class EmployeeShortDescrTRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "EMPLOYEE_SHORT_DESCR_T";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final EmployeeShortDescrTRef _EmployeeShortDescrTRefFactory = new EmployeeShortDescrTRef();

  public static ORADataFactory getORADataFactory()
  { return _EmployeeShortDescrTRefFactory; }
  /* constructor */
  public EmployeeShortDescrTRef()
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
    EmployeeShortDescrTRef r = new EmployeeShortDescrTRef();
    r._ref = (REF) d;
    return r;
  }

  public static EmployeeShortDescrTRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (EmployeeShortDescrTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to EmployeeShortDescrTRef: "+exn.toString()); }
  }

  public EmployeeShortDescrT getValue() throws SQLException
  {
     return (EmployeeShortDescrT) EmployeeShortDescrT.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(EmployeeShortDescrT c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
