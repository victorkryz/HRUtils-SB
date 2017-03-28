package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class LocationsEntryT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR.HR_UTILS_LOCATIONS_ENTRY_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,1,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  protected static final LocationsEntryT _HrUtilsLocationsEntryTFactory = new LocationsEntryT();

  public static ORADataFactory getORADataFactory()
  { return _HrUtilsLocationsEntryTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public LocationsEntryT()
  { _init_struct(true); }
  public LocationsEntryT(java.math.BigDecimal id, String countryId, String streetAddress, String postalCode, String city, String stateProvince) throws SQLException
  { _init_struct(true);
    setId(id);
    setCountryId(countryId);
    setStreetAddress(streetAddress);
    setPostalCode(postalCode);
    setCity(city);
    setStateProvince(stateProvince);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(LocationsEntryT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new LocationsEntryT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setId(java.math.BigDecimal id) throws SQLException
  { _struct.setAttribute(0, id); }


  public String getCountryId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setCountryId(String countryId) throws SQLException
  { _struct.setAttribute(1, countryId); }


  public String getStreetAddress() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setStreetAddress(String streetAddress) throws SQLException
  { _struct.setAttribute(2, streetAddress); }


  public String getPostalCode() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setPostalCode(String postalCode) throws SQLException
  { _struct.setAttribute(3, postalCode); }


  public String getCity() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setCity(String city) throws SQLException
  { _struct.setAttribute(4, city); }


  public String getStateProvince() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setStateProvince(String stateProvince) throws SQLException
  { _struct.setAttribute(5, stateProvince); }

}
