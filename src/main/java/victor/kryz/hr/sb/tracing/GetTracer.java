/**
 * HRUtils-SB
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.tracing;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import victor.kryz.hr.sb.ents.DepartmentStatisticT;
import victor.kryz.hr.sb.ents.EmployeeBriefEntryT;
import victor.kryz.hr.sb.tracing.specific.DepartmentStatisticT_Tracer;
import victor.kryz.hr.sb.tracing.specific.EmployeeBriefEntryT_Tracer;
import victor.kryz.hr.sb.tracing.specific.HrUtilsCountriesEntryT_Tracer;
import victor.kryz.hr.sb.tracing.specific.HrUtilsDepartmentsEntryT_Tracer;
import victor.kryz.hr.sb.tracing.specific.HrUtilsJobHistoryEntryT_Tracer;
import victor.kryz.hr.sb.tracing.specific.HrUtilsLocationsEntryT_Tracer;
import victor.kryz.hr.sb.tracing.specific.HrUtilsRegionsEntryT_Tracer;
import victor.kryz.hrutils.generated.ents.HrUtilsCountriesEntryT;
import victor.kryz.hrutils.generated.ents.HrUtilsDepartmentsEntryT;
import victor.kryz.hrutils.generated.ents.HrUtilsJobHistoryEntryT;
import victor.kryz.hrutils.generated.ents.HrUtilsLocationsEntryT;
import victor.kryz.hrutils.generated.ents.HrUtilsRegionsEntryT;

public class GetTracer {
	
	private static Cache<String, ObjectTracer<?>> spCache =
			CacheBuilder.newBuilder()
		    	.maximumSize(20)
		    	.build();
	
	/*
	public static <T> ObjectTracer<T> getByClass(Class<T> tracerClass) throws ExecutionException  {
		throw new RuntimeException("Tracer by class " + tracerClass.getName() +" not found");
	}
	*/

	@SuppressWarnings("unchecked")
	public static <T> ObjectTracer<T> getForClass(Class<T> objectClassFor) throws ExecutionException 
	{
		ObjectTracer<T> tracer = null;
		
		if ( objectClassFor == DepartmentStatisticT.class )
			tracer =  (ObjectTracer<T>)spCache.get(DepartmentStatisticT.class.getName(), 
									new Callable<ObjectTracer<DepartmentStatisticT>>() {
											 @Override
											    public ObjectTracer<DepartmentStatisticT> call()  {
											      return new DepartmentStatisticT_Tracer();
											 	}
				  							});
		else if (objectClassFor == EmployeeBriefEntryT.class)
			tracer =  (ObjectTracer<T>)spCache.get(EmployeeBriefEntryT.class.getName(), 
					new Callable<ObjectTracer<EmployeeBriefEntryT>>() {
							 @Override
							    public ObjectTracer<EmployeeBriefEntryT> call()  {
							      return new EmployeeBriefEntryT_Tracer();
							 	}
  							});
		else if (objectClassFor == HrUtilsCountriesEntryT.class)
			tracer =  (ObjectTracer<T>)spCache.get(HrUtilsCountriesEntryT.class.getName(), 
					new Callable<ObjectTracer<HrUtilsCountriesEntryT>>() {
							 @Override
							    public ObjectTracer<HrUtilsCountriesEntryT> call()  {
							      return new HrUtilsCountriesEntryT_Tracer();
							 	}
  							});
		else if (objectClassFor == HrUtilsDepartmentsEntryT.class)
			tracer =  (ObjectTracer<T>)spCache.get(HrUtilsDepartmentsEntryT.class.getName(), 
					new Callable<ObjectTracer<HrUtilsDepartmentsEntryT>>() {
							 @Override
							    public ObjectTracer<HrUtilsDepartmentsEntryT> call()  {
							      return new HrUtilsDepartmentsEntryT_Tracer();
							 	}
  							});
		else if (objectClassFor == HrUtilsLocationsEntryT.class)
			tracer =  (ObjectTracer<T>)spCache.get(HrUtilsLocationsEntryT.class.getName(), 
					new Callable<ObjectTracer<HrUtilsLocationsEntryT>>() {
							 @Override
							    public ObjectTracer<HrUtilsLocationsEntryT> call()  {
							      return new HrUtilsLocationsEntryT_Tracer();
							 	}
  							});
		else if (objectClassFor == HrUtilsRegionsEntryT.class)
			tracer =  (ObjectTracer<T>)spCache.get(HrUtilsRegionsEntryT.class.getName(), 
					new Callable<ObjectTracer<HrUtilsRegionsEntryT>>() {
							 @Override
							    public ObjectTracer<HrUtilsRegionsEntryT> call()  {
							      return new HrUtilsRegionsEntryT_Tracer();
							 	}
  							});
		else if (objectClassFor == HrUtilsJobHistoryEntryT.class)
			tracer =  (ObjectTracer<T>)spCache.get(HrUtilsJobHistoryEntryT.class.getName(), 
					new Callable<ObjectTracer<HrUtilsJobHistoryEntryT>>() {
							 @Override
							    public ObjectTracer<HrUtilsJobHistoryEntryT> call()  {
							      return new HrUtilsJobHistoryEntryT_Tracer();
							 	}
  							});
		else
			throw new RuntimeException("Tracer for class " + objectClassFor.getName() +" not found");
		
		return tracer;
	}
}
