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

public class RegionsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.REGIONS_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final RegionsT _HrutilsRegionsTFactory = new RegionsT();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsRegionsTFactory; }
  /* constructors */
  public RegionsT()
  {
    this((RegionsEntryT[])null);
  }

  public RegionsT(RegionsEntryT[] a)
  {
    _array = new MutableArray(2002, a, RegionsEntryT.getORADataFactory());
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _array.toDatum(c, _SQL_NAME);
  }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    RegionsT a = new RegionsT();
    a._array = new MutableArray(2002, (ARRAY) d, RegionsEntryT.getORADataFactory());
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
  public RegionsEntryT[] getArray() throws SQLException
  {
    return (RegionsEntryT[]) _array.getObjectArray(
      new RegionsEntryT[_array.length()]);
  }

  public RegionsEntryT[] getArray(long index, int count) throws SQLException
  {
    return (RegionsEntryT[]) _array.getObjectArray(index,
      new RegionsEntryT[_array.sliceLength(index, count)]);
  }

  public void setArray(RegionsEntryT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(RegionsEntryT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public RegionsEntryT getElement(long index) throws SQLException
  {
    return (RegionsEntryT) _array.getObjectElement(index);
  }

  public void setElement(RegionsEntryT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
