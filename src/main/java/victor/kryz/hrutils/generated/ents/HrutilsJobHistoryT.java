package victor.kryz.hrutils.generated.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.jpub.runtime.MutableArray;

public class HrutilsJobHistoryT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.JOB_HISTORY_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final HrutilsJobHistoryT _HrutilsJobHistoryTFactory = new HrutilsJobHistoryT();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsJobHistoryTFactory; }
  /* constructors */
  public HrutilsJobHistoryT()
  {
    this((HrUtilsJobHistoryEntryT[])null);
  }

  public HrutilsJobHistoryT(HrUtilsJobHistoryEntryT[] a)
  {
    _array = new MutableArray(2002, a, HrUtilsJobHistoryEntryT.getORADataFactory());
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    if (__schemaName!=null) return _array.toDatum(c,__schemaName + "." + _SQL_NAME);
    return _array.toDatum(c, _SQL_NAME);
  }
  private String __schemaName = null;
  public void __setSchemaName(String schemaName) { __schemaName = schemaName; }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    HrutilsJobHistoryT a = new HrutilsJobHistoryT();
    a._array = new MutableArray(2002, (ARRAY) d, HrUtilsJobHistoryEntryT.getORADataFactory());
    return a;
  }

  public int length() throws SQLException
  {
    return _array.length();
  }

  public int getBaseType() throws SQLException
  {
    return _array.getBaseType();
  }

  public String getBaseTypeName() throws SQLException
  {
    return _array.getBaseTypeName();
  }

  public ArrayDescriptor getDescriptor() throws SQLException
  {
    return _array.getDescriptor();
  }

  /* array accessor methods */
  public HrUtilsJobHistoryEntryT[] getArray() throws SQLException
  {
    return (HrUtilsJobHistoryEntryT[]) _array.getObjectArray(
      new HrUtilsJobHistoryEntryT[_array.length()]);
  }

  public HrUtilsJobHistoryEntryT[] getArray(long index, int count) throws SQLException
  {
    return (HrUtilsJobHistoryEntryT[]) _array.getObjectArray(index,
      new HrUtilsJobHistoryEntryT[_array.sliceLength(index, count)]);
  }

  public void setArray(HrUtilsJobHistoryEntryT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(HrUtilsJobHistoryEntryT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public HrUtilsJobHistoryEntryT getElement(long index) throws SQLException
  {
    return (HrUtilsJobHistoryEntryT) _array.getObjectElement(index);
  }

  public void setElement(HrUtilsJobHistoryEntryT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
