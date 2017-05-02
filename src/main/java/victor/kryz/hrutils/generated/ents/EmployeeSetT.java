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

public class EmployeeSetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "EMPLOYEE_SET_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final EmployeeSetT _EmployeeSetTFactory = new EmployeeSetT();

  public static ORADataFactory getORADataFactory()
  { return _EmployeeSetTFactory; }
  /* constructors */
  public EmployeeSetT()
  {
    this((EmployeeDescrT[])null);
  }

  public EmployeeSetT(EmployeeDescrT[] a)
  {
    _array = new MutableArray(2002, a, EmployeeDescrT.getORADataFactory());
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
    EmployeeSetT a = new EmployeeSetT();
    a._array = new MutableArray(2002, (ARRAY) d, EmployeeDescrT.getORADataFactory());
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
  public EmployeeDescrT[] getArray() throws SQLException
  {
    return (EmployeeDescrT[]) _array.getObjectArray(
      new EmployeeDescrT[_array.length()]);
  }

  public EmployeeDescrT[] getArray(long index, int count) throws SQLException
  {
    return (EmployeeDescrT[]) _array.getObjectArray(index,
      new EmployeeDescrT[_array.sliceLength(index, count)]);
  }

  public void setArray(EmployeeDescrT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(EmployeeDescrT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public EmployeeDescrT getElement(long index) throws SQLException
  {
    return (EmployeeDescrT) _array.getObjectElement(index);
  }

  public void setElement(EmployeeDescrT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
