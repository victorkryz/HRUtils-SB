package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class HrUtilsEmployeeComposite20Ref implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "HR_UTILS.EMPLOYEE_COMPOSITE20";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final HrUtilsEmployeeComposite20Ref _HrUtilsEmployeeComposite20RefFactory = new HrUtilsEmployeeComposite20Ref();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsEmployeeComposite20RefFactory; }
  /* constructor */
  public HrUtilsEmployeeComposite20Ref()
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
    HrUtilsEmployeeComposite20Ref r = new HrUtilsEmployeeComposite20Ref();
    r._ref = (REF) d;
    return r;
  }

  public static HrUtilsEmployeeComposite20Ref cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (HrUtilsEmployeeComposite20Ref) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to HrUtilsEmployeeComposite20Ref: "+exn.toString()); }
  }

  public HrUtilsEmployeeComposite20 getValue() throws SQLException
  {
     return (HrUtilsEmployeeComposite20) HrUtilsEmployeeComposite20.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(HrUtilsEmployeeComposite20 c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
