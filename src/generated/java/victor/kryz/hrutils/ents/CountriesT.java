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

public class CountriesT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.COUNTRIES_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final CountriesT _HrutilsCountriesTFactory = new CountriesT();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsCountriesTFactory; }
  /* constructors */
  public CountriesT()
  {
    this((CountriesEntryT[])null);
  }

  public CountriesT(CountriesEntryT[] a)
  {
    _array = new MutableArray(2002, a, CountriesEntryT.getORADataFactory());
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
    CountriesT a = new CountriesT();
    a._array = new MutableArray(2002, (ARRAY) d, CountriesEntryT.getORADataFactory());
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
  public CountriesEntryT[] getArray() throws SQLException
  {
    return (CountriesEntryT[]) _array.getObjectArray(
      new CountriesEntryT[_array.length()]);
  }

  public CountriesEntryT[] getArray(long index, int count) throws SQLException
  {
    return (CountriesEntryT[]) _array.getObjectArray(index,
      new CountriesEntryT[_array.sliceLength(index, count)]);
  }

  public void setArray(CountriesEntryT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(CountriesEntryT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public CountriesEntryT getElement(long index) throws SQLException
  {
    return (CountriesEntryT) _array.getObjectElement(index);
  }

  public void setElement(CountriesEntryT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
