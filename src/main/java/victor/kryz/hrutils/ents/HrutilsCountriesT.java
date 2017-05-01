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

public class HrutilsCountriesT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.COUNTRIES_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final HrutilsCountriesT _HrutilsCountriesTFactory = new HrutilsCountriesT();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsCountriesTFactory; }
  /* constructors */
  public HrutilsCountriesT()
  {
    this((HrUtilsCountriesEntryT[])null);
  }

  public HrutilsCountriesT(HrUtilsCountriesEntryT[] a)
  {
    _array = new MutableArray(2002, a, HrUtilsCountriesEntryT.getORADataFactory());
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
    HrutilsCountriesT a = new HrutilsCountriesT();
    a._array = new MutableArray(2002, (ARRAY) d, HrUtilsCountriesEntryT.getORADataFactory());
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
  public HrUtilsCountriesEntryT[] getArray() throws SQLException
  {
    return (HrUtilsCountriesEntryT[]) _array.getObjectArray(
      new HrUtilsCountriesEntryT[_array.length()]);
  }

  public HrUtilsCountriesEntryT[] getArray(long index, int count) throws SQLException
  {
    return (HrUtilsCountriesEntryT[]) _array.getObjectArray(index,
      new HrUtilsCountriesEntryT[_array.sliceLength(index, count)]);
  }

  public void setArray(HrUtilsCountriesEntryT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(HrUtilsCountriesEntryT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public HrUtilsCountriesEntryT getElement(long index) throws SQLException
  {
    return (HrUtilsCountriesEntryT) _array.getObjectElement(index);
  }

  public void setElement(HrUtilsCountriesEntryT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
