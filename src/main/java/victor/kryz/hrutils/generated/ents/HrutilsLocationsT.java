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

public class HrutilsLocationsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.LOCATIONS_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final HrutilsLocationsT _HrutilsLocationsTFactory = new HrutilsLocationsT();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsLocationsTFactory; }
  /* constructors */
  public HrutilsLocationsT()
  {
    this((HrUtilsLocationsEntryT[])null);
  }

  public HrutilsLocationsT(HrUtilsLocationsEntryT[] a)
  {
    _array = new MutableArray(2002, a, HrUtilsLocationsEntryT.getORADataFactory());
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
    HrutilsLocationsT a = new HrutilsLocationsT();
    a._array = new MutableArray(2002, (ARRAY) d, HrUtilsLocationsEntryT.getORADataFactory());
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
  public HrUtilsLocationsEntryT[] getArray() throws SQLException
  {
    return (HrUtilsLocationsEntryT[]) _array.getObjectArray(
      new HrUtilsLocationsEntryT[_array.length()]);
  }

  public HrUtilsLocationsEntryT[] getArray(long index, int count) throws SQLException
  {
    return (HrUtilsLocationsEntryT[]) _array.getObjectArray(index,
      new HrUtilsLocationsEntryT[_array.sliceLength(index, count)]);
  }

  public void setArray(HrUtilsLocationsEntryT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(HrUtilsLocationsEntryT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public HrUtilsLocationsEntryT getElement(long index) throws SQLException
  {
    return (HrUtilsLocationsEntryT) _array.getObjectElement(index);
  }

  public void setElement(HrUtilsLocationsEntryT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
