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

public class StringListT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "STRING_LIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final StringListT _StringListTFactory = new StringListT();

  public static ORADataFactory getORADataFactory()
  { return _StringListTFactory; }
  /* constructors */
  public StringListT()
  {
    this((String[])null);
  }

  public StringListT(String[] a)
  {
    _array = new MutableArray(12, a, null);
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
    StringListT a = new StringListT();
    a._array = new MutableArray(12, (ARRAY) d, null);
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
  public String[] getArray() throws SQLException
  {
    return (String[]) _array.getObjectArray();
  }

  public String[] getArray(long index, int count) throws SQLException
  {
    return (String[]) _array.getObjectArray(index, count);
  }

  public void setArray(String[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(String[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public String getElement(long index) throws SQLException
  {
    return (String) _array.getObjectElement(index);
  }

  public void setElement(String a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
