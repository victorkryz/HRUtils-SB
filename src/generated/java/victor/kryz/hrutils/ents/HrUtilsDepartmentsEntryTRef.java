package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class HrUtilsDepartmentsEntryTRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "HR_UTILS.DEPARTMENTS_ENTRY_T";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final HrUtilsDepartmentsEntryTRef _HrUtilsDepartmentsEntryTRefFactory = new HrUtilsDepartmentsEntryTRef();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsDepartmentsEntryTRefFactory; }
  /* constructor */
  public HrUtilsDepartmentsEntryTRef()
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
    HrUtilsDepartmentsEntryTRef r = new HrUtilsDepartmentsEntryTRef();
    r._ref = (REF) d;
    return r;
  }

  public static HrUtilsDepartmentsEntryTRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (HrUtilsDepartmentsEntryTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to HrUtilsDepartmentsEntryTRef: "+exn.toString()); }
  }

  public HrUtilsDepartmentsEntryT getValue() throws SQLException
  {
     return (HrUtilsDepartmentsEntryT) HrUtilsDepartmentsEntryT.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(HrUtilsDepartmentsEntryT c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
