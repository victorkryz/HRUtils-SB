package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class HrUtilsLocationsEntryTRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "HR_UTILS.LOCATIONS_ENTRY_T";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final HrUtilsLocationsEntryTRef _HrUtilsLocationsEntryTRefFactory = new HrUtilsLocationsEntryTRef();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsLocationsEntryTRefFactory; }
  /* constructor */
  public HrUtilsLocationsEntryTRef()
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
    HrUtilsLocationsEntryTRef r = new HrUtilsLocationsEntryTRef();
    r._ref = (REF) d;
    return r;
  }

  public static HrUtilsLocationsEntryTRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (HrUtilsLocationsEntryTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to HrUtilsLocationsEntryTRef: "+exn.toString()); }
  }

  public HrUtilsLocationsEntryT getValue() throws SQLException
  {
     return (HrUtilsLocationsEntryT) HrUtilsLocationsEntryT.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(HrUtilsLocationsEntryT c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
