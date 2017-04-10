package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class HrUtilsJobHistoryEntryTRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "HR_UTILS.JOB_HISTORY_ENTRY_T";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final HrUtilsJobHistoryEntryTRef _HrUtilsJobHistoryEntryTRefFactory = new HrUtilsJobHistoryEntryTRef();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsJobHistoryEntryTRefFactory; }
  /* constructor */
  public HrUtilsJobHistoryEntryTRef()
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
    HrUtilsJobHistoryEntryTRef r = new HrUtilsJobHistoryEntryTRef();
    r._ref = (REF) d;
    return r;
  }

  public static HrUtilsJobHistoryEntryTRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (HrUtilsJobHistoryEntryTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to HrUtilsJobHistoryEntryTRef: "+exn.toString()); }
  }

  public HrUtilsJobHistoryEntryT getValue() throws SQLException
  {
     return (HrUtilsJobHistoryEntryT) HrUtilsJobHistoryEntryT.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(HrUtilsJobHistoryEntryT c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
