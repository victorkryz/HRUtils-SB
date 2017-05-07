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

public class HrutilsDepartmentsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.DEPARTMENTS_T";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final HrutilsDepartmentsT _HrutilsDepartmentsTFactory = new HrutilsDepartmentsT();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsDepartmentsTFactory; }
  /* constructors */
  public HrutilsDepartmentsT()
  {
    this((HrUtilsDepartmentsEntryT[])null);
  }

  public HrutilsDepartmentsT(HrUtilsDepartmentsEntryT[] a)
  {
    _array = new MutableArray(2002, a, HrUtilsDepartmentsEntryT.getORADataFactory());
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
    HrutilsDepartmentsT a = new HrutilsDepartmentsT();
    a._array = new MutableArray(2002, (ARRAY) d, HrUtilsDepartmentsEntryT.getORADataFactory());
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
  public HrUtilsDepartmentsEntryT[] getArray() throws SQLException
  {
    return (HrUtilsDepartmentsEntryT[]) _array.getObjectArray(
      new HrUtilsDepartmentsEntryT[_array.length()]);
  }

  public HrUtilsDepartmentsEntryT[] getArray(long index, int count) throws SQLException
  {
    return (HrUtilsDepartmentsEntryT[]) _array.getObjectArray(index,
      new HrUtilsDepartmentsEntryT[_array.sliceLength(index, count)]);
  }

  public void setArray(HrUtilsDepartmentsEntryT[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(HrUtilsDepartmentsEntryT[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public HrUtilsDepartmentsEntryT getElement(long index) throws SQLException
  {
    return (HrUtilsDepartmentsEntryT) _array.getObjectElement(index);
  }

  public void setElement(HrUtilsDepartmentsEntryT a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
