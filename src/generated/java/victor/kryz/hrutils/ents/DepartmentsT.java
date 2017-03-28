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

public class DepartmentsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR.HRUTILS_DEPARTMENTS_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final DepartmentsT _HrutilsDepartmentsTFactory = new DepartmentsT();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsDepartmentsTFactory; }
  /* constructors */
  public DepartmentsT()
  {
    this((DepartmentsEntryT[])null);
  }

  public DepartmentsT(DepartmentsEntryT[] a)
  {
    _array = new MutableArray(2002, a, DepartmentsEntryT.getORADataFactory());
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
    DepartmentsT a = new DepartmentsT();
    a._array = new MutableArray(2002, (ARRAY) d, DepartmentsEntryT.getORADataFactory());
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
  public DepartmentsEntryT[] getArray() throws SQLException
  {
    return (DepartmentsEntryT[]) _array.getObjectArray(
      new DepartmentsEntryT[_array.length()]);
  }

  public DepartmentsEntryT[] getArray(long index, int count) throws SQLException
  {
    return (DepartmentsEntryT[]) _array.getObjectArray(index,
      new DepartmentsEntryT[_array.sliceLength(index, count)]);
  }

  public void setArray(DepartmentsEntryT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(DepartmentsEntryT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public DepartmentsEntryT getElement(long index) throws SQLException
  {
    return (DepartmentsEntryT) _array.getObjectElement(index);
  }

  public void setElement(DepartmentsEntryT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
