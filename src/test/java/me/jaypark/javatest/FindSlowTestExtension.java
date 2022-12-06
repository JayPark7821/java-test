package me.jaypark.javatest;


import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

	private long THRESHOLD;

	public FindSlowTestExtension(long THRESHOLD) {
		this.THRESHOLD = THRESHOLD;
	}

	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {

		String testClassName = context.getRequiredTestClass().getName();
		Method requiredTestMethod = context.getRequiredTestMethod();
		FastTest annotation = requiredTestMethod.getAnnotation(FastTest.class);

		ExtensionContext.Store store = context.getStore(
			ExtensionContext.Namespace.create(testClassName, requiredTestMethod));

		long start_time = store.remove("START_TIME", long.class);
		long duration = System.currentTimeMillis() - start_time;
		if (duration > THRESHOLD && annotation != null) {
			System.out.printf("Pleas consider mark method [%s] with @SlowTest. \n", requiredTestMethod);
		}
	}

	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {

		String testClassName = context.getRequiredTestClass().getName();
		String testMethodName = context.getRequiredTestMethod().getName();
		ExtensionContext.Store store = context.getStore(
			ExtensionContext.Namespace.create(testClassName, testMethodName));

		store.put("START_TIME", System.currentTimeMillis());


	}
}
