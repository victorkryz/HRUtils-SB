package victor.kryz.hrutils.ents;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class RegionsEntryT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "HR_UTILS.REGIONS_ENTRY_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[2];
  protected static final RegionsEntryT _Entry = new RegionsEntryT();

  public static ORADataFactory getORADataFactory()
  { return _Entry; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[2], _sqlType, _factory); }
  public RegionsEntryT()
  { _init_struct(true); }
  public RegionsEntryT(java.math.BigDecimal regionId, String regionName) throws SQLException
  { _init_struct(true);
    setRegionId(regionId);
    setRegionName(regionName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(RegionsEntryT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new RegionsEntryT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getRegionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setRegionId(java.math.BigDecimal regionId) throws SQLException
  { _struct.setAttribute(0, regionId); }


  public String getRegionName() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setRegionName(String regionName) throws SQLException
  { _struct.setAttribute(1, regionName); }

}
