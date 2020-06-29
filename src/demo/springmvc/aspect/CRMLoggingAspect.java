package demo.springmvc.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup Logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup point-cut declarations
	@Pointcut("execution(* demo.springmvc.controller.*.*(..))") // for any class, for any method, for any arguments
	private void forControllerPackage() {}
	
	// do the same thing for Service and DAO package
	@Pointcut("execution(* demo.springmvc.dao.*.*(..))") // for any class, for any method, for any arguments
	private void forDaoPackage() {}
	
	@Pointcut("execution(* demo.springmvc.service.*.*(..))") // for any class, for any method, for any arguments
	private void forServicePackage() {}
	
	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	private void forAppFlow() {}
	
	// add Before Advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		// display the method that we are calling
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("====>>>in @Before : Calling the method : "+method);
		
		// display the arguments to the method
		// get the arguments
		Object[] arguments = joinPoint.getArgs();
		
		// loop through the arguments
		for (Object args : arguments) {
			myLogger.info("====>>>Argument : "+args);
		}
	}
	
	// add AfterReturning Advice
	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		
		// display the method we are returning from
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("====>>>in @AfterReturning : Calling the method : "+method);
		
		// display data returned
		myLogger.info("====>>> Result : "+result);
	}
	
	
}
