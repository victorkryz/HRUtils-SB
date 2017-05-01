package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class EmployeeShortDescrT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "EMPLOYEE_SHORT_DESCR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,12,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  protected static final EmployeeShortDescrT _EmployeeShortDescrTFactory = new EmployeeShortDescrT();

  public static ORADataFactory getORADataFactory()
  { return _EmployeeShortDescrTFactory; }

  protected static java.util.Hashtable _map = new java.util.Hashtable();
  protected static boolean _initialized = false;
  protected static synchronized void init()
  { if (!_initialized)
    { _initialized=true;
      _map.put("EMPLOYEE_SHORT_DESCR_T",victor.kryz.hrutils.ents.EmployeeShortDescrT.getORADataFactory());
      _map.put("EMPLOYEE_DESCR_T",victor.kryz.hrutils.ents.EmployeeDescrT.getORADataFactory());
  } }

  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public EmployeeShortDescrT()
  { _init_struct(true); }
  public EmployeeShortDescrT(java.math.BigDecimal emplId, String firstName, String lastName, String email, String phone, java.math.BigDecimal depId) throws SQLException
  { _init_struct(true);
    setEmplId(emplId);
    setFirstName(firstName);
    setLastName(lastName);
    setEmail(email);
    setPhone(phone);
    setDepId(depId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(EmployeeShortDescrT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) return createFromFactory("EmployeeShortDescrT", d, sqlType);
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  protected ORAData createExact(Datum d, int sqlType) throws SQLException
  {
    EmployeeShortDescrT o = new EmployeeShortDescrT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  protected ORAData createFromFactory(String s, Datum d, int sqlType) throws SQLException
  {
    String sql = ((STRUCT) d).getSQLTypeName();
    init();
    EmployeeShortDescrT factory = (EmployeeShortDescrT)_map.get(sql);
    if (factory == null) {
       int p;
       if ((p=sql.indexOf(".")) >= 0) {
          factory = (EmployeeShortDescrT)_map.get(sql.substring(p+1));
          if (factory!=null) _map.put(sql,factory); }
       if (factory == null) throw new SQLException
          ("Unable to convert a "+sql+" to a "+s+" or a subclass of "+s);
    }
    return factory.createExact(d,sqlType);
  }

  /* accessor methods */
  public java.math.BigDecimal getEmplId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setEmplId(java.math.BigDecimal emplId) throws SQLException
  { _struct.setAttribute(0, emplId); }


  public String getFirstName() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setFirstName(String firstName) throws SQLException
  { _struct.setAttribute(1, firstName); }


  public String getLastName() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setLastName(String lastName) throws SQLException
  { _struct.setAttribute(2, lastName); }


  public String getEmail() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setEmail(String email) throws SQLException
  { _struct.setAttribute(3, email); }


  public String getPhone() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setPhone(String phone) throws SQLException
  { _struct.setAttribute(4, phone); }


  public java.math.BigDecimal getDepId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setDepId(java.math.BigDecimal depId) throws SQLException
  { _struct.setAttribute(5, depId); }

}
