package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.jpub.runtime.MutableArray;

public class HrutilsRegionsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.REGIONS_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final HrutilsRegionsT _HrutilsRegionsTFactory = new HrutilsRegionsT();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsRegionsTFactory; }
  /* constructors */
  public HrutilsRegionsT()
  {
    this((HrUtilsRegionsEntryT[])null);
  }

  public HrutilsRegionsT(HrUtilsRegionsEntryT[] a)
  {
    _array = new MutableArray(2002, a, HrUtilsRegionsEntryT.getORADataFactory());
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
    HrutilsRegionsT a = new HrutilsRegionsT();
    a._array = new MutableArray(2002, (ARRAY) d, HrUtilsRegionsEntryT.getORADataFactory());
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
  public HrUtilsRegionsEntryT[] getArray() throws SQLException
  {
    return (HrUtilsRegionsEntryT[]) _array.getObjectArray(
      new HrUtilsRegionsEntryT[_array.length()]);
  }

  public HrUtilsRegionsEntryT[] getArray(long index, int count) throws SQLException
  {
    return (HrUtilsRegionsEntryT[]) _array.getObjectArray(index,
      new HrUtilsRegionsEntryT[_array.sliceLength(index, count)]);
  }

  public void setArray(HrUtilsRegionsEntryT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(HrUtilsRegionsEntryT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public HrUtilsRegionsEntryT getElement(long index) throws SQLException
  {
    return (HrUtilsRegionsEntryT) _array.getObjectElement(index);
  }

  public void setElement(HrUtilsRegionsEntryT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
