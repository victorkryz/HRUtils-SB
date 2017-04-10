package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class HrUtilsRegionsEntryTRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "HR_UTILS.ROWTYPE_SQL19";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final HrUtilsRegionsEntryTRef _HrUtilsRowtypeSql19RefFactory = new HrUtilsRegionsEntryTRef();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsRowtypeSql19RefFactory; }
  /* constructor */
  public HrUtilsRegionsEntryTRef()
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
    HrUtilsRegionsEntryTRef r = new HrUtilsRegionsEntryTRef();
    r._ref = (REF) d;
    return r;
  }

  public static HrUtilsRegionsEntryTRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (HrUtilsRegionsEntryTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to HrUtilsRowtypeSql19Ref: "+exn.toString()); }
  }

  public HrUtilsRegionsEntryT getValue() throws SQLException
  {
     return (HrUtilsRegionsEntryT) HrUtilsRegionsEntryT.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(HrUtilsRegionsEntryT c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
