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

public class LocationsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.LOCATIONS_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final LocationsT _HrutilsLocationsTFactory = new LocationsT();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsLocationsTFactory; }
  /* constructors */
  public LocationsT()
  {
    this((LocationsEntryT[])null);
  }

  public LocationsT(LocationsEntryT[] a)
  {
    _array = new MutableArray(2002, a, LocationsEntryT.getORADataFactory());
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
    LocationsT a = new LocationsT();
    a._array = new MutableArray(2002, (ARRAY) d, LocationsEntryT.getORADataFactory());
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
  public LocationsEntryT[] getArray() throws SQLException
  {
    return (LocationsEntryT[]) _array.getObjectArray(
      new LocationsEntryT[_array.length()]);
  }

  public LocationsEntryT[] getArray(long index, int count) throws SQLException
  {
    return (LocationsEntryT[]) _array.getObjectArray(index,
      new LocationsEntryT[_array.sliceLength(index, count)]);
  }

  public void setArray(LocationsEntryT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(LocationsEntryT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public LocationsEntryT getElement(long index) throws SQLException
  {
    return (LocationsEntryT) _array.getObjectElement(index);
  }

  public void setElement(LocationsEntryT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
