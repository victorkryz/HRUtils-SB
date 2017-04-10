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

public class HrutilsEmployeeCompositeSe implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.EMPLOYEE_COMPOSITE_SE";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final HrutilsEmployeeCompositeSe _HrutilsEmployeeCompositeSeFactory = new HrutilsEmployeeCompositeSe();

  public static ORADataFactory getORADataFactory()
  { return _HrutilsEmployeeCompositeSeFactory; }
  /* constructors */
  public HrutilsEmployeeCompositeSe()
  {
    this((HrUtilsEmployeeComposite20[])null);
  }

  public HrutilsEmployeeCompositeSe(HrUtilsEmployeeComposite20[] a)
  {
    _array = new MutableArray(2002, a, HrUtilsEmployeeComposite20.getORADataFactory());
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
    HrutilsEmployeeCompositeSe a = new HrutilsEmployeeCompositeSe();
    a._array = new MutableArray(2002, (ARRAY) d, HrUtilsEmployeeComposite20.getORADataFactory());
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
  public HrUtilsEmployeeComposite20[] getArray() throws SQLException
  {
    return (HrUtilsEmployeeComposite20[]) _array.getObjectArray(
      new HrUtilsEmployeeComposite20[_array.length()]);
  }

  public HrUtilsEmployeeComposite20[] getArray(long index, int count) throws SQLException
  {
    return (HrUtilsEmployeeComposite20[]) _array.getObjectArray(index,
      new HrUtilsEmployeeComposite20[_array.sliceLength(index, count)]);
  }

  public void setArray(HrUtilsEmployeeComposite20[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(HrUtilsEmployeeComposite20[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public HrUtilsEmployeeComposite20 getElement(long index) throws SQLException
  {
    return (HrUtilsEmployeeComposite20) _array.getObjectElement(index);
  }

  public void setElement(HrUtilsEmployeeComposite20 a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
