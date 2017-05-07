declare
  -- check server version :
 SUITABLE_MIN_VERSION constant int := 12;
 SUITABLE_MIN_RELEASE constant int := 1;
 E_UNSUITABLE_SERVER_VERSION constant int := -20100;
 
     function to_ver_str(p_ver in pls_integer, p_release in pls_integer) 
                                                       return varchar2 as
      v_str varchar2(20) := p_ver || '.' || p_release;                                       
     begin
      return v_str;
     end to_ver_str;
begin
    if ( (DBMS_DB_VERSION.VERSION < SUITABLE_MIN_VERSION) or 
         ((DBMS_DB_VERSION.VERSION = SUITABLE_MIN_VERSION) and (DBMS_DB_VERSION.RELEASE < SUITABLE_MIN_RELEASE)) ) then
       raise_application_error(E_UNSUITABLE_SERVER_VERSION, 
                               'Oracle server version (' || to_ver_str(DBMS_DB_VERSION.VERSION, DBMS_DB_VERSION.VERSION) || 
                               ') lower than minimal suitable (' || to_ver_str(SUITABLE_MIN_VERSION, SUITABLE_MIN_RELEASE) || ')');
    end if;
end;