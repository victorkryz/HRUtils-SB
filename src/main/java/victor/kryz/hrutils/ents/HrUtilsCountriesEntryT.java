package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class HrUtilsCountriesEntryT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.COUNTRIES_ENTRY_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 1,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[2];
  protected static final HrUtilsCountriesEntryT _HrUtilsCountriesEntryTFactory = new HrUtilsCountriesEntryT();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsCountriesEntryTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[2], _sqlType, _factory); }
  public HrUtilsCountriesEntryT()
  { _init_struct(true); }
  public HrUtilsCountriesEntryT(String id, String name) throws SQLException
  { _init_struct(true);
    setId(id);
    setName(name);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(HrUtilsCountriesEntryT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new HrUtilsCountriesEntryT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getId() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setId(String id) throws SQLException
  { _struct.setAttribute(0, id); }


  public String getName() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setName(String name) throws SQLException
  { _struct.setAttribute(1, name); }

}
