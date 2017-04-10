package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class HrUtilsCountriesEntryTRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "HR_UTILS.COUNTRIES_ENTRY_T";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final HrUtilsCountriesEntryTRef _HrUtilsCountriesEntryTRefFactory = new HrUtilsCountriesEntryTRef();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsCountriesEntryTRefFactory; }
  /* constructor */
  public HrUtilsCountriesEntryTRef()
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
    HrUtilsCountriesEntryTRef r = new HrUtilsCountriesEntryTRef();
    r._ref = (REF) d;
    return r;
  }

  public static HrUtilsCountriesEntryTRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (HrUtilsCountriesEntryTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to HrUtilsCountriesEntryTRef: "+exn.toString()); }
  }

  public HrUtilsCountriesEntryT getValue() throws SQLException
  {
     return (HrUtilsCountriesEntryT) HrUtilsCountriesEntryT.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(HrUtilsCountriesEntryT c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
